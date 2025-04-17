# HubSpot Integration - Meetime Case

Este projeto foi desenvolvido como parte do **processo seletivo da Meetime**.  
Consiste em uma **API REST em Java com Spring Boot**, que realiza integração com a API do **HubSpot** utilizando **OAuth 2.0 (authorization code flow)**.

---

## 🚀 Funcionalidades

- [x] Geração da URL de autorização OAuth (`/oauth/authorize`)
- [x] Callback OAuth para troca de código por access token (`/oauth/callback`)
- [ ] Criação de contatos no CRM (`/contacts`)
- [ ] Recebimento de webhooks de criação de contato (`/webhook/contact-creation`)

---

## 🛠️ Tecnologias

- Java 21
- Spring Boot 3.4.4
- Maven
- OAuth 2.0 (Authorization Code)
- Lombok

---

## 🔐 Configuração

Você precisará configurar as seguintes propriedades no `application.yml` ou como variáveis de ambiente:

```yaml
hubspot:
  client-id: SEU_CLIENT_ID
  client-secret: SEU_CLIENT_SECRET
  redirect-uri: http://localhost:8080/oauth/callback
```

---

## ▶️ Como executar o projeto

```bash
# Clonar o repositório
git clone https://github.com/seu-usuario/hubspot-integration.git
cd hubspot-integration

# Rodar localmente
./mvnw spring-boot:run
```

A aplicação iniciará em `http://localhost:8080`.

---

## 📬 Entrega

O projeto deve ser enviado via GitHub com o assunto:

```
Processo seletivo Meetime - Case técnico
```

E encaminhado para:
- thais.dias@meetime.com.br
- joao@meetime.com.br
- william.willers@meetime.com.br
- victor@meetime.com.br

---

## 📈 Melhorias futuras (previstas)

- Armazenar tokens com refresh automático
- Implementar controle de rate limit da API
- Validação de payloads recebidos via webhook
- Testes unitários e integração com cobertura
- Deploy via Docker

---

## 📄 Licença

MIT © 2025 - Desenvolvido por André Nicoletti