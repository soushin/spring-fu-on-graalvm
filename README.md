# Spring-fu on GraalVM

## Overview

This repository contains the application implemented by [Spring Fu](https://github.com/spring-projects/spring-fu) on [GraalVM](https://www.graalvm.org/).

## Motivation

My concern is ... 🤔

- How to resolve problem that Dynamic Class Loading?
- How to make Spring-Fu to a native image?

## Running the applications

Build up docker image
```
$ docker build -t soushin/spring-fu-on-graalvm .
```

then run docker container!
```
docker run -it -p 8080:8080 soushin/spring-fu-on-graalvm:latest  bash
```

**via API of HTTP**

You can to request the api.

```
curl http://localhost:8080/?q=test
echo test
```

```
curl -XGET -H 'Content-Type:text/event-stream' http://localhost:8080/sse?q=test                                                                                                 (docker-for-desktop/default)
data:test

data:test

data:test
```



