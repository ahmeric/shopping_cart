

## Version Number

741e2021841b2ee9dfe6da974bbeb934b579947f



#### Installing graddle 

To install graddle, you can follow the instructions:
https://gradle.org/install/


### Development environment

Open a terminal and run the following commands to ensure that you have valid versions of Java and Gradle installed:

You can also use JDK 11 
```bash
$ java -version
openjdk version "15.0.1" 2020-10-20
OpenJDK Runtime Environment (build 15.0.1+9-18)
OpenJDK 64-Bit Server VM (build 15.0.1+9-18, mixed mode, sharing)
```

```bash
$ gradle -v
------------------------------------------------------------
Gradle 6.8
------------------------------------------------------------

Build time:   2021-01-08 16:38:46 UTC
Revision:     b7e82460c5373e194fb478a998c4fcfe7da53a7e

Kotlin:       1.4.20
Groovy:       2.5.12
Ant:          Apache Ant(TM) version 1.10.9 compiled on September 27 2020
JVM:          15.0.1 (Oracle Corporation 15.0.1+9)
OS:           Mac OS X 11.1 x86_64
```

## Tests

Tests can be run by executing following command from the root of the project:

```bash
$ ./gradlew  test
```
or
```bash
$ gradle  test
```