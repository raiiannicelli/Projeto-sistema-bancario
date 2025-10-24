# Sistema Bancário - Projeto Final

## Sumário
* [Sobre](#sobre)
* [Funcionalidades](#funcionalidades)
* [Pré-requisitos](#pré-requisitos)
* [Instalação](#instalação)
* [Execução](#execução)
* [Estrutura do Projeto](#estrutura-do-projeto)
* [Prática de execução](#uso)

## Sobre
Este repositório contém o **Sistema Bancário**, desenvolvido em **Java** com interface **Swing** e persistência via **PostgresSql**. O sistema permite gestão de contas bancárias, operações financeiras (depósito, saque, PIX) e geração de relatórios.

## Funcionalidades
* Cadastro e autenticação de contas (Corrente, Poupança, Salário)
* Depósito em conta
* Saque de conta
* Transferência via PIX
* Consulta de saldo
* Geração de relatório de contas

## Pré-requisitos
* Java 17 ou superior
* Maven 3.6+
* Swing
* Git

## Instalação
## Clone este repositório
```
git clone https://github.com/raiiannicelli/Projeto-sistema-bancario
```
## Acesse a pasta do projeto
```
cd Projeto-sistema-bancario
```
## Gere o pacote
```
mvn clean package
```
## Execute com JAR 
```
java -jar target/CRUD-bank-1.0-SNAPSHOT.jar
```
## Execute com ClassPath
```
mvn exec:java
```

## Testando execução
1. Inicie o programa.
2. Cadastre ou selecione uma conta.
3. Realize operações financeiras pelos botões disponíveis.
4. Visualize relatórios e consulte saldo em tempo real.

## Estrutura de Diretórios
```
ProjetoFinal/                      # Pasta do projeto
├── .github/                       # Configurações de workflows e templates
│   └── workflows/                 # Workflows 
│       └── maven.yml              # Pipeline de build/teste com Maven
├── src/
└── main/
│   └── java/
│       └── br/
│           └── com/
│               └── banco/
│                 ├── Main.java                       # Ponto de entrada da aplicação
│                 └── Controle                    # Pacote de controle e conexão BD  
│                     ├── BancoDeDados.java           # Conexão e manipulação do postgres
│                     ├── GerenciadorDeContas.java    # Regras de negócio bancário
│                 └── Modelo                      # Pacote de modelos de dados
│                     ├── ContaBancaria.java          # Classe abstrata de conta
│                     ├── ContaCorrente.java          # Conta do tipo Corrente
│                     ├── ContaPoupanca.java          # Conta do tipo Poupança
│                     ├── ContaSalario.java           # Conta do tipo Salário
│                 └── visao/                      # Pacote de componentes Swing
│                     ├── FormCadConta.java           # Tela de cadastro de contas
│                     ├── FormEntrarConta.java        # Tela de login de contas
│                     ├── FormPix.java                # Tela para transferência PIX
│                     ├── FormRelContas.java          # Tela de relatórios
│                     ├── FormSaldo.java              # Tela de exibição de saldo
│                     ├── FormSacar.java              # Tela de saque
├── target/                        # Artefatos gerados pelo build (JARs, classes compiladas)
├── pom.xml                        # Configuração do projeto Maven
├── README.md                      # Documentação principal do projeto
└── LICENSE                        # Licença do projeto
```
