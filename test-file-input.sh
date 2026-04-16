#!/bin/bash

echo "Building Credit Simulator..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo "==================================="
    echo "Build successful!"
    echo "==================================="
    echo ""
    echo "Testing with file input..."
    echo ""
    
    java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_inputs.txt
else
    echo "Build failed!"
    exit 1
fi
