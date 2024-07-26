package com.myproject.pontocom.controller;

import com.myproject.pontocom.domain.dadosProficionais.Cargo;
import com.myproject.pontocom.domain.funcionario.DadosListagemFuncionarios;
import com.myproject.pontocom.domain.funcionario.Funcionario;
import com.myproject.pontocom.domain.funcionario.FuncionarioRepository;
import com.myproject.pontocom.domain.registroDePresenca.Registro;
import com.myproject.pontocom.domain.registroDePresenca.RegistroRepository;
import com.myproject.pontocom.domain.registroDePresenca.ausencia.MotivoAusencia;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
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
        // arquivo criado
        Workbook workbook = new XSSFWorkbook();

        int qntdFuncionarios = (int) funcionarioRepository.count();

        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        for (int i = 0; i < qntdFuncionarios; i++) {
            Funcionario funcionario = funcionarios.get(i);

            // criação da tabela
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

        int rowNum = 1;
        Sheet sheet = workbook.createSheet("Resumo");
        for (int i = 0; i < qntdFuncionarios; i++) {
            Funcionario funcionario = funcionarios.get(i);
            String[] cabecalhos = { "Funcionario", "Dias Ausentes", "Dias presentes", "Sálario"};
            Row headerRow = sheet.createRow(0);
            for (int j = 0; j < cabecalhos.length; j++) {
                Cell cell = headerRow.createCell(j);
                cell.setCellValue(cabecalhos[j]);
            }

            LocalDate hoje = LocalDate.now();
            int ano = hoje.getYear();
            int mes = hoje.getMonthValue();
            YearMonth yearMonth = YearMonth.of(ano, mes);
            int diasNoMes = yearMonth.lengthOfMonth();
            int diasAusentes = registroRepository.countByIdFuncionarioAndFuncionarioPresenteFalse(funcionario.getId());
            int diasPresentes = registroRepository.countByIdFuncionarioAndFuncionarioPresenteTrue(funcionario.getId());
            double salario = funcionario.getDadosProficionais().getSalario() / diasNoMes * diasPresentes;

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(funcionario.getNome());
            row.createCell(1).setCellValue(diasAusentes);
            row.createCell(2).setCellValue(diasPresentes);
            row.createCell(3).setCellValue(salario);
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

    @PutMapping("/{email}/validar-atestado/{id}/{data}")
    public ResponseEntity<?> validarAtestado(@PathVariable String email, @PathVariable Long id, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        if (!validarRH(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não autorizado.");
        }
        Registro registro = registroRepository.findAllByIdFuncionarioAndData(id, data);

        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado.");
        }

        registro.getPresente().setFuncionarioPresente(false);
        registro.getPresente().setMotivoAusencia(MotivoAusencia.ATESTADO);
        registroRepository.save(registro);

        return ResponseEntity.ok("Atestado validado com sucesso.");
    }


}
