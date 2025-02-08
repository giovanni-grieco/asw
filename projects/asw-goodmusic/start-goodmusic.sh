#!/bin/bash
#If one of the parameters $1 or $2 is -c or --clean then

if [[ "$1" == "-h" || "$1" == "--help" || "$2" == "-h" || "$2" == "--help" ]]; then
    echo "Usage: $0 [-c|--clean] [-b|--build]"
    echo "  -c, --clean  Clean the project"
    echo "  -b, --build  Build the project"
    exit 0
fi

if [[ "$1" == "-c" || "$1" == "--clean" || "$2" == "-c" || "$2" == "--clean" ]]; then
    gradle clean
fi

if [[ "$1" == "-b" || "$1" == "--build" || "$2" == "-b" || "$2" == "--build"  ]]; then
    gradle build
fi

docker compose up -d --build