# Sistema Bancário - Projeto Final

## Descrição
Sistema bancário desenvolvido em Java com interface gráfica Swing e banco de dados H2 para persistência de dados.

## Funcionalidades
- Cadastro de contas bancárias
- Depósito
- Saque
- Transferência PIX
- Consulta de saldo
- Relatório de contas
- **Atualização automática da tela de conta após operações**

## Tecnologias Utilizadas
- Java 17+
- Swing (Interface Gráfica)
- H2 Database (Banco de dados)
- Maven (Gerenciamento de dependências)

## Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior

### Passos para execução

1. **Clone ou baixe o projeto**

2. **Navegue até a pasta do projeto**
   ```bash
   cd ProjetoFinal
   ```

3. **Compile o projeto**
   ```bash
   mvn clean compile
   ```

4. **Execute o projeto**
   ```bash
   mvn exec:java -Dexec.mainClass="br.com.banco.Main"
   ```

   Ou compile e execute o JAR:
   ```bash
   mvn clean package
   java -jar target/ProjetoFinal-1.0-SNAPSHOT.jar
   ```

## Contas de Teste
O sistema cria automaticamente as seguintes contas de teste na primeira execução:

- **Conta 1**: 12345 (Agência: 001) - João Silva - R$ 1.000,00
- **Conta 2**: 67890 (Agência: 001) - Maria Santos - R$ 2.500,00  
- **Conta 3**: 11111 (Agência: 002) - Pedro Costa - R$ 500,00

## Funcionalidades Corrigidas

### Problemas Resolvidos:
1. **Persistência de Dados**: Implementado banco de dados H2 para manter os dados entre execuções
2. **Método de Saque**: Corrigido para atualizar corretamente o saldo no banco de dados
3. **Transferência PIX**: Corrigido para debitar da conta origem e creditar na conta destino
4. **Validações**: Adicionadas validações mais robustas nos formulários
5. **Feedback ao Usuário**: Melhoradas as mensagens de erro e sucesso
6. **Atualização Automática**: A tela de conta atualiza imediatamente após operações

### Melhorias Implementadas:
- Validação de saldo antes de operações
- Verificação de existência das contas
- Tratamento de transações (rollback em caso de erro)
- Interface mais amigável com mensagens informativas
- Formatação de valores monetários
- **Atualização em tempo real do saldo na tela principal**
- **Preenchimento automático dos dados da conta nos formulários**
- **Campos de conta e agência desabilitados para edição**

## Atualização Automática da Tela

### Como Funciona:
1. **Ao abrir uma operação** (Saque, Depósito, PIX), os dados da conta são preenchidos automaticamente
2. **Durante a operação**, os campos de conta e agência ficam desabilitados para evitar erros
3. **Após a operação bem-sucedida**, a tela principal é atualizada automaticamente
4. **O saldo é recarregado** do banco de dados e exibido em tempo real

### Benefícios:
- **Experiência do usuário melhorada**: Não precisa fechar e abrir a tela para ver o saldo atualizado
- **Menos erros**: Dados da conta preenchidos automaticamente
- **Feedback imediato**: Saldo atualizado instantaneamente após operações
- **Interface mais intuitiva**: Campos bloqueados evitam confusão

## Estrutura do Projeto
```
src/main/java/br/com/banco/
├── Main.java                    # Classe principal
├── BancoDeDados.java           # Gerenciamento do banco de dados
├── GerenciadorDeContas.java    # Lógica de negócio
├── ContaBancaria.java          # Modelo de conta
├── FormConta.java              # Interface principal (com atualização automática)
├── FormSacar.java              # Interface de saque (com atualização automática)
├── FormPix.java                # Interface de PIX (com atualização automática)
├── FormDeposito.java           # Interface de depósito (com atualização automática)
└── ...                         # Outros formulários
```

## Banco de Dados
O sistema utiliza o banco de dados H2, que cria automaticamente um arquivo `banco_contas.mv.db` na pasta do projeto. Este arquivo contém todas as informações das contas e é persistido entre execuções.

## Autor
Ricardo Lima - RA: 2565510 