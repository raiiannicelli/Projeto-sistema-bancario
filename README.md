[![Build Status](https://img.shields.io/github/actions/workflow/status/usuario/ProjetoFinal/maven.yml?branch=main)](https://github.com/usuario/ProjetoFinal/actions)
[![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

# Sistema Bancário - Projeto Final

## Sumário

* [Sobre](#sobre)
* [Funcionalidades](#funcionalidades)
* [Tecnologias](#tecnologias)
* [Pré-requisitos](#pré-requisitos)
* [Instalação](#instalação)
* [Execução](#execução)
* [Estrutura do Projeto](#estrutura-do-projeto)
* [Uso](#uso)
* [Contribuição](#contribuição)
* [License](#license)
* [Autor](#autor)

## Sobre

Este repositório contém o **Sistema Bancário**, desenvolvido em **Java** com interface **Swing** e persistência via **H2 Database**. O sistema permite gestão de contas bancárias, operações financeiras (depósito, saque, PIX) e geração de relatórios.

## Funcionalidades

* Cadastro e autenticação de contas (Corrente, Poupança, Salário)
* Depósito em conta
* Saque de conta
* Transferência via PIX
* Consulta de saldo
* Geração de relatório de contas

## Tecnologias

* Java 17+
* Maven
* Swing
* H2 Database

## Pré-requisitos

Antes de começar, verifique se você instalou em sua máquina:

* JDK 17 ou superior
* Maven 3.6+
* Git

## Instalação

```bash
# Clone este repositório
git clone https://github.com/usuario/ProjetoFinal.git
# Acesse a pasta do projeto
cd ProjetoFinal
# Instale as dependências
mvn clean install
```

## Execução

Para rodar a aplicação diretamente via Maven:

```bash
mvn exec:java -Dexec.mainClass="br.com.banco.Main"
```

Ou execute o JAR gerado:

```bash
# Gera o pacote
mvn package
# Executa o JAR
java -jar target/ProjetoFinal-1.0-SNAPSHOT.jar
```

## Estrutura do Projeto

```
ProjetoFinal/
├── .github/                   # Configurações de CI/CD e templates
│   └── workflows/maven.yml    # GitHub Actions
├── src/
│   └── main/
│       └── java/
│           └── br/com/banco/
│               ├── AbstractTableModel.java   # Modelo de relatório
│               ├── BancoDeDados.java         # Conexão H2
│               ├── ContaBancaria.java        # Base de contas
│               ├── ContaCorrente.java        # Conta corrente
│               ├── ContaPoupanca.java        # Conta poupança
│               ├── ContaSalario.java         # Conta salário
│               ├── GerenciadorDeContas.java  # Regras de negócio
│               ├── Main.java                 # Entry point
│               └── visao/                    # Telas Swing
│                   ├── FormCadConta.java
│                   ├── FormEntrarConta.java
│                   ├── FormPix.java
│                   ├── FormRelContas.java
│                   ├── FormSaldo.java
│                   └── FormSacar.java
└── target/                    # Build artifacts
```

## Uso

1. Inicie o programa.
2. Cadastre ou selecione uma conta.
3. Realize operações financeiras pelos botões disponíveis.
4. Visualize relatórios e consulte saldo em tempo real.

## Contribuição

Contribuições são sempre bem-vindas. Siga os passos:

1. Faça um fork do projeto.
2. Crie uma branch: `git checkout -b feature/minha-nova-funcionalidade`
3. Faça suas alterações e commit: `git commit -m 'Adiciona nova funcionalidade'`
4. Faça push: `git push origin feature/minha-nova-funcionalidade`
5. Abra um Pull Request.