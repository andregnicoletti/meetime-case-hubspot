# HubSpot Integration

Projeto em Spring Boot para integração OAuth2 com a API do HubSpot. Permite autenticar via OAuth, obter `access_token` e criar contatos na plataforma HubSpot.

---

## 🔧 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.4
- Spring Security (OAuth2 Client)
- Lombok
- Maven

---

## ✅ Funcionalidades Implementadas

- [x] Autenticação OAuth2 via HubSpot
- [x] Troca de código de autorização por `access_token` e `refresh_token`
- [x] Endpoint para criação de contatos no HubSpot

---

## 🔐 Fluxo de Autenticação

1. O usuário acessa o endpoint:
   ```
   GET /authorize
   ```
   Isso redireciona para a URL de autorização do HubSpot.

2. Após login e permissão, o HubSpot redireciona para:
   ```
   GET /oauth/callback?code=...
   ```
   O código recebido é trocado por um token de acesso.

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
  "email": "andre@exemplo.com",
  "accessToken": "seu_token_de_acesso"
}
```

Retorna o `id` do contato criado no HubSpot.

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

Você pode definir essas variáveis via `.env`, `application.properties`, ou diretamente no ambiente de execução.

---

## 🔁 Observações

- O token de acesso tem tempo de expiração. Use o `refresh_token` para obter um novo.
- **IMPORTANTE**: para testar com permissões reais, use uma conta de **teste** (não conta de desenvolvedor).

---

## 📌 Próximos Passos

- [ ] Listar contatos do HubSpot
- [ ] Atualizar contatos existentes
- [ ] Armazenar e gerenciar tokens com segurança
- [ ] Implementar atualização automática do `access_token`

---

## 📎 Documentação oficial

- https://developers.hubspot.com/docs/api/oauth
- https://developers.hubspot.com/docs/api/crm/contacts

---

Feito com 💛 por [André Nicoletti](https://github.com/andregnicoletti)