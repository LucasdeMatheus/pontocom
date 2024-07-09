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


<img loading="lazy" src="https://github.com/LucasdeMatheus/pontocom/assets/134244848/af8d3168-3867-4ed3-8a6a-778a5bb84221"/>

### TesteAutenticação POST: (/login)
>>esta request fará a autenticação de um usuario com Login e Senha e irá gerar um token, liberando acesso de todo o sistema.

### Request Funcionarios dados GET (/funcionarios)
>>esta request listará todos os funcionarios e suas informações não sensiveis.

### Post Cadastrar Funcionario POST: (/funcionarios)
>>esta request, recebendo um json, cadastra funcionario e suas informações no sistema.

### Put Atualizar Dados Funcionario PUT: (/funcionarios)
>>esta request atualiza dados pessoais do funcionario.

### Post Bater Ponto POST: (funcionarios/{ID}/entrada)
>>esta request marca a entrada dos funcionarios em uma unica tabela relacional, pelo id do funcionario.

### Put Marcar Intervalo PUT: (funcionarios/{id}/intervalo)
>>esta request marca a saída para o intervalo.

### Put Marcar Saida PUT: (funcionarios/{id}/saida)
>>esta request marca a saida do funcionario

### Outras funcionalidades em Desenvolvimento...

# Tecnologias utilizadas

<p>linguagem de programação: JDK v21</p>
<p>Framework: Spring</p>
<p>IDE: Intellij IDEA</p>
<p>Banco de dados: MySQL</p>
