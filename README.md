# quarkus-reactive-beer

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-reactive-beer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

If you want to profile/debug the application is native mode via `perf record --call-graph dwarf` is required to compile it with debug symbols:

```shell script
./mvnw package -Dnative -Dquarkus.native.additional-build-args=-H:-DeleteLocalSymbols,-H:-OmitInlinedMethodDebugLineInfo -Dquarkus.native.debug.enabled
```

if instead we want to use the frame pointer i.e. `perf record -g`:

```shell script
./mvnw package -Dnative -Dquarkus.native.additional-build-args=-H:-DeleteLocalSymbols,-H:-OmitInlinedMethodDebugLineInfo,-H:+PreserveFramePointer -Dquarkus.native.debug.enabled
```

In both cass `-H:-DeleteLocalSymbols` is required.

## How to benchmark it

Got into the `script` folder and type:

```bash
./benchmark
```
It starts a local benchmark using [Hyperfoil](https://hyperfoil.io/) in JVM mode without profiling, 
but still requiring `pidstat` on Linux to be available.

In order to perform the same test in native mode, it's necessary to add the `-n` native parameter.

**NOTE on APPLICATION PATHS**:
>The default relative paths of both the jar and native executable are hardcoded in the benchmarking
script but can be changed with ease.


Hyperfoil takes care of performing a *calibration phase* before performing the actual run, hence
don't expect `-d 10` to configure the whole experiment duration to be 10 seconds, because such calibration phase 
can take few seconds.

In addition, the script takes care to wait some time (it's an hard-coded proportion of the configured test duration) 
before collecting `pidstat` stats from the running quarkus application, in order to be sure both 
load generator and application to be warmed up.

The same warmup time is used to start [async-profiler](https://github.com/jvm-profiling-tools/ap-loader), in case
the user is running a JVM experiment and by adding the `-a` option to it.
(Further async-profiler configurations are available to select the events  `-e <events>` and 
the output format `-o JFR`).

Additionally, on Linux machines which have `perf-tools` installed, can run `perf stat` during the non-warmup portion of the test by 
adding `-p` option to the script. 

The default parameters of the scripts can be obtained by running it with  `-h` parameter.