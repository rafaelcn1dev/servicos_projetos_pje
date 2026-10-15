@echo off
title RabbitMQ
podman info >nul 2>&1 || podman machine start
podman rm -f rabbitmq >nul 2>&1
podman run -d --rm --name rabbitmq -p 5672:5672 -p 15672:15672 docker.io/library/rabbitmq:3.11-management || (pause & exit /b 1)

:wait
curl -s -o NUL -w "%%{http_code}" http://localhost:15672 | findstr /b 200 >nul || (timeout /t 3 /nobreak >nul & goto wait)

podman exec rabbitmq rabbitmqctl add_vhost pje-dev
podman exec rabbitmq rabbitmqctl add_user pje pje
podman exec rabbitmq rabbitmqctl set_user_tags pje administrator
podman exec rabbitmq rabbitmqctl set_permissions -p pje-dev pje ".*" ".*" ".*"
podman logs -f rabbitmq
