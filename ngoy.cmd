@echo off
setlocal
set ngoyVersion=1.0.0-rc8
set ngoyPath=%~dp0build\tmp\ngoy-%ngoyVersion%

if not exist %ngoyPath% (
	echo Extracting ngoy binaries...
	call gradle extractNgoy
)
java -cp %ngoyPath%\* ngoy.Ngoy %*