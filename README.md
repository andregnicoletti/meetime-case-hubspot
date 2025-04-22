
# HubSpot Integration

![Java](https://img.shields.io/badge/Java-21-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.4-brightgreen?logo=spring-boot)
![Maven](https://img.shields.io/badge/Maven-Build-red?logo=apachemaven)
![Swagger](https://img.shields.io/badge/Swagger_Doc-Available-informational?logo=swagger)
![Ngrok](https://img.shields.io/badge/ngrok-Webhook_Testing-blueviolet?logo=ngrok)

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
- [x] Página de sucesso estilizada com logo e animação após autenticação

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

## 📄 Documentação da API (PDF) 
Acesse: [Documentação Técnica (PDF)](./documentacao-tecnica-hubspot.pdf)

---

## 📑 Documentação da API (Swagger)

Acesse:

```
http://localhost:8080/docs
```

---

## 🚀 Como executar o projeto

### 1. Build do projeto

```bash
./mvnw clean install
```

### 2. Executar com o script `run.sh` (recomendado)

Este projeto possui um script chamado `run.sh` que automatiza:

- Exportação das variáveis de ambiente necessárias
- Verificação e instalação do `ngrok` (se necessário)
- Início do túnel público com o `ngrok`
- Execução da aplicação Java

#### Passos:

1. Dê permissão de execução ao script:

```bash
chmod +x run.sh
```

2. Edite o arquivo `run.sh` e configure as variáveis:

```bash
APPLICATION_PORT=8080
HUBSPOT_CLIENT_ID="sua_client_id"
HUBSPOT_CLIENT_SECRET="seu_client_secret"
```

3. Execute:

```bash
./run.sh
```

A URL pública do `ngrok` será exibida no terminal ou poderá ser consultada em `http://localhost:4040`.

---

## 🌍 Testar Webhooks com ngrok

Caso deseje iniciar o ngrok manualmente:

```bash
ngrok http 8080
```

URL gerada:

```
https://abcd1234.ngrok.io
```

Configure no HubSpot:

```
https://abcd1234.ngrok.io/webhook
```

---

## 📎 Referências

- [HubSpot API Docs](https://developers.hubspot.com/docs)
- [Webhooks](https://developers.hubspot.com/docs/guides/api/app-management/webhooks)
- [SpringDoc OpenAPI](https://springdoc.org)

---

Feito com 💛 por [André Nicoletti](https://github.com/andregnicoletti)
