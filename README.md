## API de Tarefas

Este é um projeto de faculdade que consiste em uma API RESTful simples, construída com Spring Boot, para gerenciar tarefas. Ela permite realizar operações CRUD (Criar, Ler, Atualizar, Deletar) em tarefas, além de marcá-las como concluídas ou arquivá-las.

### Endpoints da API

Você pode encontrar a documentação interativa do Swagger para esta API em:

[https://app.swaggerhub.com/apis/FELIPEMALACARNE012/tarefas-prog-web/1.0.0](https://app.swaggerhub.com/apis/FELIPEMALACARNE012/tarefas-prog-web/1.0.0)

### Funcionalidades

- Criar novas tarefas com título e descrição.
- Recuperar uma lista de todas as tarefas.
- Recuperar uma tarefa específica por ID.
- Atualizar o título e a descrição de uma tarefa.
- Marcar uma tarefa como concluída.
- Arquivar uma tarefa.
- Excluir uma tarefa.

### Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- PostgreSQL (Banco de Dados)
- Maven (Ferramenta de Construção)
- Swagger (Documentação da API)

### Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/your-username/tarefas-api.git
   ```
2. **Configurar o banco de dados:**
    - Crie um banco de dados PostgreSQL.
    - Atualize os detalhes de conexão do banco de dados em `application.properties`.
3. **Construir e executar a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

### Testes

A aplicação inclui testes unitários e de integração para garantir sua funcionalidade:

- **Testes unitários:** Localizados em `src/test/java`, cobrem componentes individuais, como serviços e controladores.

Para executar os testes, use o seguinte comando:

```bash
mvn test
```
