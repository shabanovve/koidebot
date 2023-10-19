### Build tdlib for Linux
This is directory for telegram api library.
Use 
```
git clone git@github.com:tdlib/td.git
```
Run to build telegram api library
```
sudo apt-get update
sudo apt-get upgrade
sudo apt-get install make git zlib1g-dev libssl-dev gperf php-cli cmake default-jdk g++
git clone https://github.com/tdlib/td.git
cd td
rm -rf build
mkdir build
cd build
cmake -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX:PATH=../example/java/td -DTD_ENABLE_JNI=ON ..
cmake --build . --target install
cd ..
cd example/java
rm -rf build
mkdir build
cd build
cmake -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX:PATH=../../../tdlib -DTd_DIR:PATH=$(readlink -e ../td/lib/cmake/Td) ..
cmake --build . --target install
cd ../../..
cd ..
ls -l td/tdlib
``` 
### Build tdlib for Windows
```
git clone https://github.com/tdlib/td.git
cd td
git clone https://github.com/Microsoft/vcpkg.git
cd vcpkg
git checkout 1b1ae50e1a69f7c659bd7d731e80b358d21c86ad
./bootstrap-vcpkg.bat
./vcpkg.exe install gperf:x64-windows openssl:x64-windows zlib:x64-windows
cd ..
Remove-Item build -Force -Recurse -ErrorAction SilentlyContinue
mkdir build
cd build
cmake -A x64 -DCMAKE_INSTALL_PREFIX:PATH=../example/java/td -DTD_ENABLE_JNI=ON -DCMAKE_TOOLCHAIN_FILE:FILEPATH=../vcpkg/scripts/buildsystems/vcpkg.cmake ..
cmake --build . --target install --config Release
cd ..
cd example/java
Remove-Item build -Force -Recurse -ErrorAction SilentlyContinue
mkdir build
cd build
cmake -A x64 -DCMAKE_INSTALL_PREFIX:PATH=../../../tdlib -DCMAKE_TOOLCHAIN_FILE:FILEPATH=../../../vcpkg/scripts/buildsystems/vcpkg.cmake -DTd_DIR:PATH=$(Resolve-Path ../td/lib/cmake/Td) ..
cmake --build . --target install --config Release
cd ../../..
cd ..
dir td/tdlib
```

### Workflow to build native executable file
- Build TDLib (telegram api library)
- Pack .so file into jar
```
    cd td/tdlib
    jar cMf tdlib.jar -C bin .
```
- Push jar into maven local repository
  (run inside cliapp directory)
```
./mvnw install:install-file \
   -Dfile=telegram.api/td/tdlib/tdlib.jar \
   -DgroupId=tdlib  \
   -DartifactId=tdlib \
   -Dversion=0.0.1-SNAPSHOT \
   -Dpackaging=jar \ 
   -DgeneratePom=true
```