#!/bin/bash
# wait-for-sql.sh

host="db"
port="${DOCKER_PORT}"

until nc -z ${host} ${port}; do
  echo "Aguardando $host:$port..."
  sleep 8
done

exec "$@"
