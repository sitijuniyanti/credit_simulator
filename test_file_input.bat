@echo off
echo Building project...
call mvn clean package -DskipTests

echo.
echo Running with file input...
java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_inputs.txt

pause
