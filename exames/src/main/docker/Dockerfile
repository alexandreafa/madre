FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS="" \
    TZ="America/Sao_Paulo"

RUN apk add --no-cache msttcorefonts-installer fontconfig && update-ms-fonts

RUN apk update && \
    apk add --no-cache tzdata && \
	cp /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Add a jhipster user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh jhipster
WORKDIR /home/jhipster

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown jhipster:jhipster entrypoint.sh
USER jhipster

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8081 5701/udp

ADD *.jar app.jar