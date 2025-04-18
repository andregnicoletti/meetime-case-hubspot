# HubSpot Integration

Projeto em Spring Boot que realiza a integração com a API do HubSpot, permitindo autenticação via OAuth2, criação e listagem de contatos, além de receber webhooks. A API é documentada com Swagger (SpringDoc OpenAPI).

---

## 🔧 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.4
- Spring Security (OAuth2 Client)
- SpringDoc OpenAPI (Swagger UI)
- Lombok
- Maven
- ngrok (para testes locais com webhooks)

---

## ✅ Funcionalidades Implementadas

- [x] OAuth2 com troca de código por `access_token`
- [x] Armazenamento de token em memória
- [x] Criação de contato via `POST /contacts`
- [x] Listagem de contatos via `GET /contacts`
- [x] Recebimento de webhooks `contact.creation` via `POST /webhook`
- [x] Integração com Swagger UI em `/docs`

---

## 📌 Endpoints disponíveis

| Método | Rota         | Descrição                                 | Exemplo de Body |
|--------|--------------|--------------------------------------------|-----------------|
| POST   | `/contacts`  | Cria um novo contato no HubSpot           | `{ "firstName": "André", "lastName": "Nicoletti", "email": "andre@exemplo.com" }` |
| GET    | `/contacts`  | Lista os contatos cadastrados             | —               |
| GET    | `/oauth/authorize` | Redireciona para autenticação OAuth2 | —               |
| GET    | `/oauth/callback`  | Recebe o código e troca pelo token    | —               |
| POST   | `/webhook`   | Recebe evento de criação de contato       | `[ { "eventId": 100, "subscriptionType": "contact.creation", "objectId": 123456 } ]` |
| GET    | `/docs`      | Interface Swagger com documentação da API | —               |

---

## 🔐 Exemplo de Autenticação

1. Acesse `/oauth/authorize` no navegador
2. Após o login no HubSpot, será redirecionado para `/oauth/callback?code=...`
3. O `access_token` é armazenado internamente e usado automaticamente nos próximos requests

---

## 📑 Documentação da API (Swagger)

Acesse:

```
http://localhost:8080/docs
```

Lá você verá a documentação completa gerada via SpringDoc OpenAPI com exemplos de requisição, schemas e parâmetros.

Se necessário, customize o título e descrição em `OpenApiConfig.java`:

```java
@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("HubSpot Integration API")
            .version("1.0")
            .description("Documentação da API de integração com HubSpot"));
}
```

---

## ⚙️ Configuração (application.yml)

```yaml
springdoc:
   swagger-ui:
      path: /docs
hubspot:
   client-id: ${HUBSPOT_CLIENT_ID}
   client-secret: ${HUBSPOT_CLIENT_SECRET}
   redirect-uri: http://localhost:8080/oauth/callback
   auth-url: https://app.hubspot.com/oauth/authorize
   token-url: https://api.hubapi.com/oauth/v1/token
   scopes: crm.objects.contacts.write crm.objects.contacts.read
```

---

## 🚀 Como executar o projeto

### 1. Build do projeto

```bash
./mvnw clean install
```

### 2. Rodar localmente

```bash
./mvnw spring-boot:run
```

O serviço estará disponível em `http://localhost:8080`.

---

## 🌍 Testar Webhooks com ngrok

### 1. Instalar ngrok

```bash
snap install ngrok
```

Ou baixe em: https://ngrok.com/download

### 2. Iniciar túnel

```bash
ngrok http 8080
```

Isso gerará uma URL pública como:

```
https://abcd1234.ngrok.io
```

### 3. Configurar no HubSpot

No painel do app, configure a URL do webhook como:

```
https://abcd1234.ngrok.io/webhook
```

---

## 📎 Referências

- [HubSpot API Docs](https://developers.hubspot.com/docs/api/crm/contacts)
- [OAuth2 Guide](https://developers.hubspot.com/docs/api/oauth)
- [Webhooks](https://developers.hubspot.com/docs/guides/api/app-management/webhooks)
- [SpringDoc OpenAPI](https://springdoc.org)

---

Feito com 💛 por [André Nicoletti](https://github.com/andregnicoletti)