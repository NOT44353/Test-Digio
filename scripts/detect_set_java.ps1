# Detect Temurin JDK 17 and set JAVA_HOME and PATH for current session
$ErrorActionPreference = 'Stop'

$javaHome = $null
$adoptium = 'C:\Program Files\Eclipse Adoptium'
$javaDir = 'C:\Program Files\Java'

if (Test-Path $adoptium) {
	$found = Get-ChildItem -Path $adoptium -Directory -Recurse -ErrorAction SilentlyContinue |
		Where-Object { $_.Name -like 'jdk-17*' } |
		Sort-Object FullName -Descending |
		Select-Object -First 1
	if ($found) { $javaHome = $found.FullName }
}

if (-not $javaHome -and (Test-Path $javaDir)) {
	$found = Get-ChildItem -Path $javaDir -Directory -Recurse -ErrorAction SilentlyContinue |
		Where-Object { $_.Name -like 'jdk-17*' -or $_.Name -like 'jdk-21*' } |
		Sort-Object FullName -Descending |
		Select-Object -First 1
	if ($found) { $javaHome = $found.FullName }
}

if (-not $javaHome) {
	Write-Host 'JDK 17 not found. Please install Temurin 17 and reopen terminal.'
	exit 1
}

$env:JAVA_HOME = $javaHome
$env:Path = (Join-Path $env:JAVA_HOME 'bin') + ';' + $env:Path

Write-Host ("JAVA_HOME=" + $env:JAVA_HOME)
java -version
