# Desafio: Consulta Vendas

Este repositório contém o projeto **Consulta Vendas**, desenvolvido como parte da formação **Desenvolvedor Moderno** no módulo de **Back end**, capítulo **JPA, consultas SQL e JPQL**.

## Sobre o Projeto

O sistema consiste em um gerenciamento de vendas (**Sale**) e vendedores (**Seller**). Cada venda está associada a um vendedor, e um vendedor pode ter várias vendas. O objetivo do projeto é realizar consultas e relatórios sobre as vendas realizadas, utilizando **JPA**, **SQL** e **JPQL**.

### Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- **Entidades**:
  - `Sale`: Representa uma venda, contendo informações como quantidade de visitas, negociações, valor e data.
  - `Seller`: Representa um vendedor, contendo informações como nome, e-mail e telefone.

- **DTOs**:
  - `SaleMinDTO`: Contém informações mínimas de uma venda.
  - `SaleReportDTO`: Contém informações detalhadas de uma venda para relatórios.
  - `SaleSumaryDTO`: Contém o resumo de vendas por vendedor.

- **Repositórios**:
  - `SaleRepository`: Interface para consultas ao banco de dados relacionadas a vendas.

- **Serviços**:
  - `SaleService`: Contém a lógica de negócios para consultas e relatórios de vendas.

- **Controladores**:
  - `SaleController`: Exposição de endpoints REST para acesso às funcionalidades do sistema.

### Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 2.7.3**
- **H2 Database**
- **JPA (Java Persistence API)**
- **Maven**

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/vanderleik/desafio-consulta-vendas.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd desafio-consulta-vendas
   ```

3. Execute o projeto utilizando o Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse o console H2 para visualizar os dados:
   - URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Usuário: `sa`
   - Senha: (em branco)

## Endpoints Disponíveis

### Consultar Venda por ID
- **GET** `/sales/{id}`

### Relatório de Vendas
- **GET** `/sales/report`
  - Parâmetros opcionais:
    - `minDate`: Data mínima (formato ISO: `yyyy-MM-dd`)
    - `maxDate`: Data máxima (formato ISO: `yyyy-MM-dd`)
    - `name`: Nome do vendedor

### Resumo de Vendas
- **GET** `/sales/summary`
  - Parâmetros opcionais:
    - `minDate`: Data mínima (formato ISO: `yyyy-MM-dd`)
    - `maxDate`: Data máxima (formato ISO: `yyyy-MM-dd`)

## Dados de Teste

O projeto já contém dados de teste pré-carregados no arquivo `import.sql`, que são automaticamente inseridos no banco de dados H2 ao iniciar a aplicação.

## Licença

Este projeto é um fork do repositório original disponível em [https://github.com/devsuperior/desafio-consulta-vendas](https://github.com/devsuperior/desafio-consulta-vendas).
