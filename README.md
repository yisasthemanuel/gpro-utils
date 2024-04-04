# GPRO UTILS

## Introducción

Libería que contiene utilidades de propósito general dentro del proyecto GPRO.


## Desarrollo

### Prerequisitos

* Máquina versión 17 Java instalada (<https://adoptium.net/es/temurin/releases/?package=jdk&version=17>)
* Maven: No necesario, está integrado en el proyecto mediante "maven wrapper" / mvnw (<https://github.com/takari/maven-wrapper>)


## Construcción

Para generar el jar

```sh
$ cd gpro-utils
$ ./mvnw clean package
```

Para publicar el jar en el repositorio maven local


```sh
$ cd gpro-utils
$ ./mvnw clean install
```

## Changelog [ES]

* **1.0.2 (04/04/2024)** - Se añaden los métodos getIDSeason, getIDRace y convertDate para poder parsear datos provenientes de la página de récords que los managers de un equipo han establecido en un circuito concreto.

## TO DOs

* Integrar con Artifactory para publicar las nuevas releases