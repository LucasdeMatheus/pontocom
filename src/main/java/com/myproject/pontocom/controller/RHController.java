package com.myproject.pontocom.controller;

import com.myproject.pontocom.domain.dadosProficionais.Cargo;
import com.myproject.pontocom.domain.funcionario.DadosListagemFuncionarios;
import com.myproject.pontocom.domain.funcionario.Funcionario;
import com.myproject.pontocom.domain.funcionario.FuncionarioRepository;
import com.myproject.pontocom.domain.registroDePresenca.Registro;
import com.myproject.pontocom.domain.registroDePresenca.RegistroRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rh")
@SecurityRequirement(name = "bearer-key")
public class RHController {

    public boolean validarRH(String email){
        Funcionario funcionario = funcionarioRepository.findByContatoEmail(email);
        if (funcionario.getDadosProficionais().getCargo() == Cargo.RH){
            return true;
        }else{
            return false;
        }
    }

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping("/{email}/listar-funcionarios")
    public ResponseEntity<Page<DadosListagemFuncionarios>> listar(Pageable paginacao, @PathVariable String email){
        if (!validarRH(email)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var page = funcionarioRepository.findAll(paginacao).map(DadosListagemFuncionarios::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{email}/criar-folha")
    public ResponseEntity<byte[]> criarFolha(@PathVariable String email) {
        if (!validarRH(email)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Workbook workbook = new XSSFWorkbook();
        int qntdFuncionarios = (int) funcionarioRepository.count();
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        for (int i = 0; i < qntdFuncionarios; i++) {
            Funcionario funcionario = funcionarios.get(i);
            Sheet sheet = workbook.createSheet("Folha de Ponto do " + funcionario.getNome());

            // Cabeçalhos da planilha
            String[] cabecalhos = { "Data", "Horário de Entrada", "Horário de Saída", "Funcionário Presente", "Motivo da Ausência"};
            Row headerRow = sheet.createRow(0);
            for (int j = 0; j < cabecalhos.length; j++) {
                Cell cell = headerRow.createCell(j);
                cell.setCellValue(cabecalhos[j]);
            }

            // Preenchimento dos dados
            List<Registro> registros = registroRepository.findAllByIdFuncionario(funcionario.getId());
            registros.forEach(System.out::println);

            int rowNum = 1;
            for (Registro registro : registros) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(registro.getData().toString());
                row.createCell(1).setCellValue(registro.getHorarioDeEntrada() != null ? registro.getHorarioDeEntrada().toString() : "");
                row.createCell(2).setCellValue(registro.getHorarioDeSaida() != null ? registro.getHorarioDeSaida().toString() : "");
                row.createCell(3).setCellValue(registro.getPresente().getFuncionarioPresente() != null ? registro.getPresente().getFuncionarioPresente().toString() : "");
                row.createCell(4).setCellValue(registro.getPresente().getMotivoAusencia() != null ? registro.getPresente().getMotivoAusencia().toString() : "");
            }
        }

        // Escrever o arquivo para um ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Configurar o cabeçalho da resposta
         HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "ponto_funcionarios.xlsx");

        // Retornar o arquivo como resposta
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
}
