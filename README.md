﻿<h1 align="center"> PontoCom </h1>

# Índice
* [Índice](#índice)
* [Descrição do Projeto](#descrição-do-projeto)
* [Status do Projeto](#status-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Tecnologias utilizadas](#tecnologias-utilizadas)


# Descrição do Projeto

<p>PontoCom é um Sistema de Ponto,
<br>Este projeto possui funcionalidades para marcar a presença dos funcionarios de uma empresa, e ter controle com geração de folha de presença.</p>

# Status do Projeto

<img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=INCOMPLETO&labelColor=%2339362C&color=%23B89931&style=for-the-badge" />

# Funcionalidades
<img loading="lazy" src="https://github.com/user-attachments/assets/abad1bfa-cbcb-44ae-8ba3-c3d85f84b97a"/>

### TesteAutenticação POST: (/login)
>>esta request fará a autenticação de um usuario com Login e Senha e irá gerar um token, liberando acesso de todo o sistema.



### Post Cadastrar Funcionario POST: (/funcionarios)
>>esta request, recebendo um json, cadastra funcionario e suas informações no sistema.

### Put Atualizar Dados Funcionario PUT: (/funcionarios)
>>esta request atualiza dados pessoais do funcionario.

### Post Bater Ponto POST: (funcionarios/{ID}/entrada)
>>esta request marca a entrada dos funcionarios em uma unica tabela relacional, pelo id do funcionario.

### Put Marcar Intervalo PUT: (funcionarios/{id}/intervalo)
>>esta request marca a saída para o intervalo.(em desenvolvimento)

### Put Marcar Saida PUT: (funcionarios/{id}/saida)
>>esta request marca a saida do funcionario.

### Funcionalidades do RH
![image](https://github.com/user-attachments/assets/a4669d10-b266-4a0b-8322-d1ef3d9ff3b4)

### Request Funcionarios dados GET (rh/{EMAIL}/listar-funcionarios)
>>esta request listará todos os funcionarios e suas informações não sensiveis.

### Request Folha de Ponto GET (rh/{EMAIL}/criar-folha)
>>esta request retornará um arquivo .xlsx (Excel) com todas as informações de registro dos funcionários.<br><br>
>>-> Segue um exemplo do arquivo:<br>
![image](https://github.com/user-attachments/assets/5b89c659-aedf-4592-80a7-b3a5b5848ecc)

### Observações
>>-> request's /rh são necessarias o email de um funcionario do RH no end-point, para ter um controle de acesso.<br><br>
>>-> as folhas de pontos de cada funcionarios são separadas por cada planilha renomeada por "Folha de Ponto do (Nome do Funcionario)"
### Outras funcionalidades em Desenvolvimento...

# Tecnologias utilizadas

<p>linguagem de programação: JDK v21</p>
<p>Framework: Spring</p>
<p>IDE: Intellij IDEA</p>
<p>framework Open Source: Insomnia</p>
<p>Banco de dados: MySQL</p>
<p>Editor de planilhas: Excel</p>
