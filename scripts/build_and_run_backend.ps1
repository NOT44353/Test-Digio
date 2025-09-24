# Build and run Spring Boot backend
$ErrorActionPreference = 'Stop'

Push-Location (Split-Path -Parent $MyInvocation.MyCommand.Path)
Push-Location ..

mvn -q -DskipTests clean install
mvn spring-boot:run
