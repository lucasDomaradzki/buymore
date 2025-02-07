FROM openjdk:17-alpine
ARG JAR_FILE=buymore*.jar
COPY target/${JAR_FILE} .
COPY entrypoint.sh entrypoint.sh
ENV DEBUG_PORT 9999
ENV DEBUG_MODE true

RUN chmod +x buymore*.jar
RUN chmod +x entrypoint.sh

EXPOSE 8080 9999

ENTRYPOINT ["/entrypoint.sh"]