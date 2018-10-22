FROM findepi/graalvm

EXPOSE 8080 8080

COPY build/libs/spring-fu-on-graalvm.jar /usr/local/app/lib/
COPY graal-app.json /usr/local/app/lib/

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y build-essential && \
    apt-get install zlib1g-dev && \
    apt-get install unzip && \
    unzip /usr/local/app/lib/spring-fu-on-graalvm.jar -d /usr/local/app/lib/spring-fu-on-graalvm && \
    cd /usr/local/app/lib/ && \
    native-image --verbose -H:Name=app \
      --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,org.springframework.core.io.VfsUtils \
      -H:ReflectionConfigurationFiles=graal-app.json \
      -Dio.netty.noUnsafe=true \
      -H:+ReportUnsupportedElementsAtRuntime \
      -Dfile.encoding=UTF-8 -cp ".:$(echo spring-fu-on-graalvm/BOOT-INF/lib/*.jar | tr ' ' ':')":spring-fu-on-graalvm/BOOT-INF/classes me.soushin.app.ApplicationKt

ENTRYPOINT /usr/local/app/lib/app
