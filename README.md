# HubSpot Integration

Projeto em Spring Boot para integração OAuth2 com a API do HubSpot. Permite autenticar via OAuth, obter `access_token`, criar e listar contatos, e receber webhooks de criação de contatos.

---

## 🔧 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.4
- Spring Security (OAuth2 Client)
- Lombok
- Maven
- ngrok (para expor aplicação local)

---

## ✅ Funcionalidades Implementadas

- [x] Autenticação OAuth2 via HubSpot
- [x] Troca de código de autorização por `access_token` e `refresh_token`
- [x] Armazenamento de token em memória (`TokenStore`)
- [x] Criação de contatos via API do HubSpot
- [x] Listagem de contatos com token armazenado
- [x] Recebimento de Webhooks (ex: `contact.creation`)
- [x] Log de eventos recebidos no webhook

---

## 🔐 Fluxo de Autenticação

1. O usuário acessa:
   ```
   GET /authorize
   ```
   Redireciona para o login do HubSpot.

2. Após login, é redirecionado para:
   ```
   GET /oauth/callback?code=...
   ```
   O código é trocado por um token, armazenado em memória.

---

## 📡 Criar Contato

Endpoint:
```
POST /contacts
```

### Payload:

```json
{
  "firstName": "André",
  "lastName": "Nicoletti",
  "email": "andre@exemplo.com"
}
```

Usa o `access_token` armazenado automaticamente.

---

## 📃 Listar Contatos

Endpoint:
```
GET /contacts
```

Retorna os contatos cadastrados na conta HubSpot autenticada.

---

## 📬 Receber Webhook

Endpoint:
```
POST /webhook
```

Recebe eventos de criação de contato (`contact.creation`) da plataforma HubSpot.

### Exemplo de Payload:

```json
[
  {
    "subscriptionType": "contact.creation",
    "objectId": 123456789,
    "occurredAt": 1744931960182
  }
]
```

---

## ⚙️ Configuração

### `application.yml`

```yaml
hubspot:
  client-id: ${HUBSPOT_CLIENT_ID}
  client-secret: ${HUBSPOT_CLIENT_SECRET}
  redirect-uri: http://localhost:8080/oauth/callback
  auth-url: https://app.hubspot.com/oauth/authorize
  token-url: https://api.hubapi.com/oauth/v1/token
  scopes: crm.objects.contacts.write crm.objects.contacts.read
```

---

## 🌍 Testar Webhooks com ngrok

1. Rode o app local:
   ```bash
   ./mvnw spring-boot:run
   ```

2. Exponha com ngrok:
   ```bash
   ngrok http 8080
   ```

3. Copie a URL gerada e configure no painel do seu app HubSpot:
   ```
   https://xxxxxx.ngrok.io/webhook
   ```

---

## 📌 Próximos Passos

- [ ] Buscar detalhes do contato automaticamente ao receber `objectId` via webhook
- [ ] Implementar renovação automática do `access_token` via `refresh_token`
- [ ] Melhorar validações e tratamento de erros
- [ ] Adicionar testes unitários
- [ ] Persistir tokens em banco de dados

---

## 📎 Documentação oficial

- https://developers.hubspot.com/docs/api/oauth
- https://developers.hubspot.com/docs/api/crm/contacts
- https://developers.hubspot.com/docs/guides/api/app-management/webhooks

---

Feito com 💛 por [André Nicoletti](https://github.com/andregnicoletti)