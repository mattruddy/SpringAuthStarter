#!/usr/bin/env bash
wait-for-db/wait-for-it.sh db:5432 -t 60
java -jar /app.jar