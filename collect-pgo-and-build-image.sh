./mvnw package -Pnative -Dquarkus.native.additional-build-args="--pgo-instrument,--gc=G1"
cd scripts
./benchmark.sh -n
cd ..
./mvnw package -Pnative -Dquarkus.native.additional-build-args="--pgo=../../scripts/default.iprof,--gc=G1,-g,-H:-DeleteLocalSymbols,-H:+PreserveFramePointer"
