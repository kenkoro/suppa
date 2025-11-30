#!/usr/bin/env bash

set -e
set -o pipefail

processError() {
  echo -e "\033[31mFast check failed, read logs above!\033[0m" >&2
  exit 1
}

trap processError ERR

compileGradle() {
  ./gradlew --version
  ./gradlew detektWithoutTests
  ./gradlew assembleDevDebug testDebugUnitTest
  ./gradlew compileKotlin
}

main() {
  compileGradle
}

time main