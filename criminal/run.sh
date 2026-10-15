#!/usr/bin/env bash
# Inicializa o serviço PJe Criminal com a configuração do ambiente antigo (TJPE).
#
# Observacoes:
#  - O banco eh acessado via PgBouncer (transaction pooling), por isso:
#      * prepareThreshold=0  -> desabilita server-side prepared statements (evita 'prepared statement S_x does not exist')
#      * spring.flyway.enabled=false -> o Flyway trava tentando advisory lock via bouncer; o banco ja esta 100% migrado.
#  - Ajuste CRIMINAL_RABBITMQ_HOST / credenciais conforme o broker disponivel.

set -euo pipefail

export CRIMINAL_APP_NAME=criminal
export CRIMINAL_DB_USER=user_pje_jud1g
export CRIMINAL_DB_PASSWORD=user_pje_jud1g
export CRIMINAL_DB_URL="jdbc:postgresql://shdbbouncer01.tjpe.gov.br:6432/criminal?stringtype=unspecified&prepareThreshold=0"
export CRIMINAL_PORT=8480
export CRIMINAL_MANAGEMENT_PORT=8481
export CRIMINAL_RABBITMQ_HOST=localhost
export CRIMINAL_RABBIT_USERNAME=pje
export CRIMINAL_RABBIT_PASSWORD=pje
export CRIMINAL_RABBIT_VHOST=pje-dev
export CRIMINAL_RABBIT_CONNECTION_TIMEOUT=5000
export EUREKA_CLIENT_ENABLED=true
export EUREKA_SERVER_DEFAULT_ZONE="http://tjpe295846.tjpe.gov.br:8761/eureka"


JAR="$(ls target/criminal-*.jar | head -1)"
echo "Iniciando: $JAR"
exec java -jar "$JAR" --spring.flyway.enabled=false
