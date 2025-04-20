#!/bin/bash

# === VARIÁVEIS DO SISTEMA ===
APP_PORT=8080
HUBSPOT_CLIENT_ID="8b9eca77-4212-4416-bf9e-e89696705a3d"
HUBSPOT_CLIENT_SECRET="352d3d9f-fef9-4e99-bd07-c773802b4bd0"

export APPLICATION_PORT=$APP_PORT
export HUBSPOT_CLIENT_ID=$HUBSPOT_CLIENT_ID
export HUBSPOT_CLIENT_SECRET=$HUBSPOT_CLIENT_SECRET

echo ""
echo "=============================================="
echo " 🚀 Iniciando a aplicação HubSpot Integration"
echo "=============================================="

# === FUNÇÃO: Instala o ngrok se não estiver presente ===
function instalar_ngrok() {
  echo ""
  echo "🔍 Verificando instalação do ngrok..."

  if command -v ngrok &> /dev/null; then
    echo "✅ ngrok já está instalado."
  else
    echo "⚠️ ngrok não encontrado. Iniciando instalação..."

    mkdir -p ./bin
    cd ./bin

    ARCH=$(uname -m)
    PLATFORM=$(uname)

    if [[ "$PLATFORM" == "Linux" && "$ARCH" == "x86_64" ]]; then
      curl -s -O https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-linux-amd64.tgz
      tar -xzf ngrok-v3-stable-linux-amd64.tgz
    elif [[ "$PLATFORM" == "Darwin" ]]; then
      curl -s -O https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-darwin-amd64.tgz
      tar -xzf ngrok-v3-stable-darwin-amd64.tgz
    else
      echo "❌ Sistema operacional não suportado para instalação automática do ngrok."
      exit 1
    fi

    chmod +x ngrok
    export PATH="$PWD:$PATH"
    cd ..
    echo "✅ ngrok instalado localmente em ./bin"
  fi
}

# === PERGUNTA: Deseja iniciar o ngrok ===
read -p "Deseja iniciar o ngrok para expor a API? (y/n): " usar_ngrok
if [[ "$usar_ngrok" =~ ^[Yy]$ ]]; then
  instalar_ngrok
  echo "🌐 Iniciando ngrok..."
  ngrok http 8080 > /dev/null 2>&1 &
  sleep 10
fi

# === FAZENDO A BUILD DA APLICAÇÂO ===
./mvnw clean package


# === 
# Obter URL pública do ngrok
NGROK_URL=$(curl -s http://localhost:4040/api/tunnels \
  | grep -o 'https://[a-zA-Z0-9.-]*\.ngrok-free\.app' \
  | head -n 1 )
if [ -z "$NGROK_URL" ]; then
  echo "⚠️ Não foi possível obter a URL pública do ngrok. Verifique manualmente em http://localhost:4040"
else
  echo "🌐 URL pública do ngrok: $NGROK_URL"
fi

# === RODA A APLICAÇÃO ===
echo ""
echo "🚧 Rodando aplicação..."
echo "🌐 URL pública do ngrok: $NGROK_URL"
java -jar target/hubspot-integration-0.0.1-SNAPSHOT.jar
