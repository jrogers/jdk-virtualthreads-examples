# JDK Virtual Threads Examples

Demonstrates JDK virtual thread usage

## Building

Requires JDK version 19 or later.

The project currently uses Maven.
```bash
mvn package
```

## Running

```bash
java --enable-preview -cp target/jdk-virtual-threads-examples-1.0.0-SNAPSHOT.jar dev.jrogers.virtualthreads.ManyVirtualThreads
```
