@echo off
title RabbitMQ Criminal (3.7.9)
podman info >nul 2>&1 || podman machine start
podman rm -f rabbitmq >nul 2>&1
podman run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=pje -e RABBITMQ_DEFAULT_PASS=pje -e RABBITMQ_DEFAULT_VHOST=pje-dev docker.io/library/rabbitmq:3.7.9-management-alpine || (pause & exit /b 1)
podman logs -f rabbitmq
