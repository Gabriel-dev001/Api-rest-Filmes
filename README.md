# API de Filmes

## 📌 Sobre o Projeto

Esta é uma API REST desenvolvida com Spring Boot para gerenciamento de filmes. Ela permite criar, ler, atualizar e excluir filmes, incluindo informações como nome, sinopse, ano de lançamento e nota. A API está integrada com Swagger para fornecer uma documentação interativa e facilitar o teste dos endpoints diretamente no navegador.

## 🚀 Tecnologias Utilizadas

- **Java** (Spring Boot)
- **JUnit** (Testes)
- **Swagger**
- **Maven**

## 🛠 Como Rodar o Projeto

### 📍 Localmente (Sem Docker)

1. Clone o repositório:

   ```sh
   git clone https://github.com/Gabriel-dev001/Api-rest-Filmes.git
   cd Api-rest-Filmes
    ```
2. Certifique-se de ter o JDK instalado (preferencialmente a versão 11 ou superior).
   
3. Execute o projeto:

    ```sh
   ./mvnw spring-boot:run
    ```
    
    Ou, se preferir usar o Maven diretamente:

   ```sh
   mvn spring-boot:run
   ```

4. Acesse a API no navegador:
 
    ```sh
    http://localhost:8080
    ```

5. Acesse a documentação interativa do Swagger:

   ```sh
   http://localhost:8080/swagger-ui.html
    ```

## 📡 Como Usar a API

### 🔍 Endpoints Disponíveis:

**1. Criar Filme:**

**Rota:**
```
POST /filmes
```

**Exemplo de Requisição:**
```json
{
  "nome": "Filme Novo",
  "sinopse": "Uma descrição do filme",
  "anoLancamento": 2024,
  "nota": 4.5
}
```

**Exemplo de Resposta:**
```json
{
  "id": 1,
  "nome": "Filme Novo",
  "sinopse": "Uma descrição do filme",
  "anoLancamento": 2024,
  "nota": 4.5
}
```

**2. Buscar Filme:**

**Rota:**
```
GET /filmes
```

**Exemplo de Requisição:**
```
GET /filmes
```

**Exemplo de Resposta:**
```json
{
  "sinopse": "Uma descrição do filme",
  "anoLancamento": 2024,
  "nota": 4.5
}
```

**3. Buscar Filme por ID:**

**Rota:**
```
GET /filmes/{id}
```

**Exemplo de Requisição:**
```
GET /filmes/1
```

**Exemplo de Resposta:**
```json
{
  "id": 1,
  "nome": "Filme Novo",
  "sinopse": "Uma descrição do filme",
  "anoLancamento": 2024,
  "nota": 4.5
}
```

**4. Atualizar Filme:**

**Rota:**
```
PUT /filmes/{id}
```

**Exemplo de Requisição:**
```json
{
  "nome": "Filme Atualizado",
  "sinopse": "Nova descrição do filme",
  "anoLancamento": 2025,
  "nota": 5.0
}
```

**Exemplo de Resposta:**
```json
{
  "id": 1,
  "nome": "Filme Atualizado",
  "sinopse": "Nova descrição do filme",
  "anoLancamento": 2025,
  "nota": 5.0
}
```

**5. Excluir Filme:**

**Rota:**
```
DELETE /filmes/{id}
```

**Exemplo de Requisição:**
```
DELETE /filmes/1
```

**Exemplo de Resposta:**
```json
{
  "message": "Filme excluído com sucesso!"
}
```

# 📞 CONTATO  

👨‍💻 **Desenvolvedor:** **Gabriel**  
