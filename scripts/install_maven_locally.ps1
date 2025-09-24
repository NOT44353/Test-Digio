# Download and set up Maven locally in user profile
$ErrorActionPreference = 'Stop'

$version = '3.9.9'
$zipUrl = "https://archive.apache.org/dist/maven/maven-3/$version/binaries/apache-maven-$version-bin.zip"
$mvnRoot = Join-Path $env:USERPROFILE "maven-$version"
$zipPath = Join-Path $env:TEMP "apache-maven-$version-bin.zip"

if (-not (Test-Path (Join-Path $mvnRoot 'bin\mvn.cmd'))) {
	Write-Host "Setting up Maven $version..."
	if (-not (Test-Path $zipPath)) {
		Write-Host "Downloading Maven $version..."
		Invoke-WebRequest -Uri $zipUrl -OutFile $zipPath
	}
	if (Test-Path $mvnRoot) { Remove-Item -Recurse -Force $mvnRoot }
	Expand-Archive -Path $zipPath -DestinationPath $env:TEMP -Force
	Move-Item -Force (Join-Path $env:TEMP "apache-maven-$version") $mvnRoot
}

$env:MAVEN_HOME = $mvnRoot
$env:Path = (Join-Path $mvnRoot 'bin') + ';' + $env:Path

mvn -v
