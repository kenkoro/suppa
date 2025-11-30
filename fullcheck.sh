#!/usr/bin/env bash

set -e
set -o pipefail

processError() {
  echo -e "\033[31mFull check failed, read logs above!\033[0m" >&2
  exit 1
}

trap processError ERR

compileGradle() {
  ./gradlew assembleDevRelease lintDevRelease
}

main() {
  ./fastcheck.sh
  compileGradle
}

time main