﻿﻿<h1 align="center"> PontoCom </h1>

# Índice
* [Índice](#índice)
* [Descrição do Projeto](#descrição-do-projeto)
* [Status do Projeto](#status-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Instalação](#Instalação)
* [Tecnologias utilizadas](#tecnologias-utilizadas)

# Descrição do Projeto

<p>PontoCom é um Sistema de Ponto,
<br>Este projeto possui funcionalidades para marcar a presença dos funcionarios de uma empresa, e ter controle com geração de folha de presença.</p>

# Status do Projeto
<img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&labelColor=%2339362C&color=%2328a745&style=for-the-badge" />


# Funcionalidades
![image](https://github.com/user-attachments/assets/5ea23158-3d88-4ea6-a22b-2eba451c45de)

### TesteAutenticação POST: (/login)
>>esta request fará a autenticação de um usuario com Login e Senha e irá gerar um token, liberando acesso de todo o sistema.



### Request Cadastrar Funcionario POST: (/funcionarios)
>>esta request, recebendo um json, cadastra funcionario e suas informações no sistema.
<br>o json deve ser construido nesse formato:<br>
![image](https://github.com/user-attachments/assets/648ac848-761f-4e97-9fc5-b772f7036e90)

### Request Atualizar Dados Funcionario PUT: (/funcionarios)
>>esta request atualiza dados pessoais do funcionario.

### Request Atualizar Senha PUT: (/funcionarios/atualizar-senha/{email})
>>esta request recebe um json com uma senha e atualiza no banco de dados.
> ![image](https://github.com/user-attachments/assets/fde03dbf-8ab2-4287-a198-b38778976181)


### Request Bater Ponto POST: (funcionarios/{ID}/entrada)
>>esta request marca a entrada dos funcionarios em uma unica tabela relacional, pelo id do funcionario.

### Request Marcar Intervalo PUT: (funcionarios/{id}/intervalo)
>>esta request marca a saída para o intervalo.(em desenvolvimento)

### Request Marcar Saida PUT: (funcionarios/{id}/saida)
>>esta request marca a saida do funcionario.

### Funcionalidades do RH
![image](https://github.com/user-attachments/assets/a4669d10-b266-4a0b-8322-d1ef3d9ff3b4)

### Request Funcionarios dados GET (rh/{EMAIL}/listar-funcionarios)
>>esta request listará todos os funcionarios e suas informações não sensiveis.

### Request Folha de Ponto GET (rh/{EMAIL}/criar-folha)
>>esta request retornará um arquivo .xlsx (Excel) com todas as informações de registro dos funcionários e um "resumo" onde mostra dias de falta e de presença e o calculo do sálario proporcional bruto.<br><br>
>>-> Segue um exemplo do arquivo:<br><br> 
>>Folha de Ponto
![image](https://github.com/user-attachments/assets/5b89c659-aedf-4592-80a7-b3a5b5848ecc)<br>
>>Resumo<br>
![image](https://github.com/user-attachments/assets/360654cb-47fc-41aa-89b4-687cc5f348ac)
### Request Validar Atestado Put(rh/{email}/validar-atestado/{ID}/{data})
>>esta request, recebendo o ID do funcionario e a Data referente a Falta, fará a coluna atualizar de FALTA para ATESTADO, funcionalidade util para a folha de pagamento.<br><br>
>> -> Segue um exemplo dessa request:<br><br>
end point: rh/usuario@example.com/validar-atestado/2/2024-07-03<br><br>
Ao fazer a requesição bem sucedida, retornará um "Atestado validado com sucesso."<br>
![image](https://github.com/user-attachments/assets/62f6e982-224f-41fe-b26e-0842698e3cbd)<br><br>
>>no banco de dados, atualizará exatamente a linha onde está presente os parametros passado pelo end-point.<br><br>
antes da requisição:<br>
![Imagem do WhatsApp de 2024-07-25 à(s) 11 35 33_2ed7200a](https://github.com/user-attachments/assets/044f3165-5606-44af-ba61-3214a1bbf9b4)<br>
<br>depois:<br>
![Imagem do WhatsApp de 2024-07-25 à(s) 11 36 48_3c5776af](https://github.com/user-attachments/assets/45147ce2-63e7-4ddd-9ca3-0a856b4a7184)


### Observações
>>-> request's /rh são necessarias o email de um funcionario do RH no end-point, para ter um controle de acesso.<br><br>
>>-> as folhas de pontos de cada funcionarios são separadas por cada planilha renomeada por "Folha de Ponto do (Nome do Funcionario)"
>>-> a request de validar atestado tambem altera a presença do funcionario para ausente, podem ser utilizada para situações onde o

# Instalação

## Requisitos

- **Java JDK**: 21 ou superior
- **Banco de Dados**: MySQL
- **IDE**: IntelliJ IDEA (opcional, para desenvolvimento)

## Configuração do Ambiente

1. **Clone o Repositório**

   Primeiro, clone o repositório do PontoCom para sua máquina local:

   ```bash
   git clone https://github.com/usuario/pontocom.git
   cd pontocom
>>2. Configuração do Banco de Dados<br><br>
>>Crie um banco de dados MySQL para o PontoCom.<br>
Importe o esquema do banco de dados incluído no diretório db.<br><br>
## sql<br>
Copie e execute essa query:<br>
>>CREATE DATABASE pontocom;<br>
USE pontocom;<br>
SOURCE caminho/para/o/arquivo/schema.sql;<br><br>

## Configuração do Aplicativo

Copie o arquivo de configuração application.properties do diretório src/main/resources para o diretório config.

Configure as propriedades do banco de dados no arquivo application.properties:

## properties
Copie e cole com seu login e senha no arquivo.
>>spring.datasource.url=jdbc:mysql://localhost:3306/pontocom<br>
spring.datasource.username=usuario<br>
spring.datasource.password=senha

é uma boa prática usar variaveis de ambiente, principalmente se for subir o projeto em um repositorio publico.

## Compilação e Execução

Compile o projeto usando Maven:

bash
Copie e execute em seu terminal:
>> ./mvnw clean install<br>

## Execute o aplicativo:

bash
Copie e execute em seu terminal:
>>java -jar target/pontocom-1.0.jar

## Criando Acesso

Antes de Acessar o aplicativo, necessita criar um Login para um Administrador ou para o proprio RH.<br>
copie e execute esse código em seu Banco de Dados MySQL:
>> use pontocom;<br>
INSERT INTO usuario (login, senha)<br>
VALUES ('usuario@example.com', '$2a$10$lIKWA3QIJyaq8z6/Rhv2XOPLtADJgdQASItP6RLX.n6fdzabBjKc.');

esta query fara um usuario sem dados de funcionarios, com login "usuario@example.com" e "123456" como senha.<br> Assim, poderá fazer requisições para cadastrar funcionarios.
<br>Recomendo apagar esse usuario após cadastrar um funcionario.
## Acesso ao Aplicativo

Após a execução, acesse o aplicativo através da url: http://localhost:8080.<br>



# Tecnologias utilizadas

<p>linguagem de programação: JDK v21</p>
<p>Framework: Spring</p>
<p>IDE: Intellij IDEA</p>
<p>framework Open Source: Insomnia</p>
<p>Banco de dados: MySQL</p>
<p>Editor de planilhas: Excel</p>


