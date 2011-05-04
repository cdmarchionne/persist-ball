@echo off
rem @echo on

Title Cargar DB

cls
echo.
echo -------------------------------------
echo 	Cargar DB 
echo -------------------------------------
echo.
echo.

set /p usuario=Escriba su nombre de usuario:
set /p password=Escriba su password:
rem set usuario=root
rem set password=root

if exist "%MYSQL_HOME%\bin\mysql.exe" goto ServerOk
echo.
echo Defina la variable de sistema "MYSQL_HOME" con el directorio donde se encuentra instado el server.
echo Para automatizar este proceso.
echo.
:SetServer
set /p MYSQL_HOME=Escriba el directorio raiz del servidor Mysql:
if exist "%MYSQL_HOME%\bin\mysql.exe" goto ServerOk
echo Error: No se encontro el servidor Mysql en "%MYSQL_HOME%\bin\mysql.exe"
echo Ingreselo nuevamente
goto SetServer

:ServerOk
echo.
set /p dir_script=Escriba el path entero del script que desea ejecutar:
echo.

chdir /D "%MYSQL_HOME%\bin"
mysql -u %usuario% -p%password% <%dir_script%

goto exit

	
:errorSERVER
REM		Verifique que exista la direccion donde se encuentra el servidor este bien escrita.
	echo Error 1:	No se encontro el servidor de la base de datos.
	echo 			Verifique que este activado el servicio.
	echo Error 1:	No se encontro el servidor de la base de datos. >> %log%
	echo 			Verifique que este activado el servicio.		>> %log%
	goto exit

:exit
	echo.
	echo Aprieta cualquier tecla para finalizar
	pause>nul

rem msg * DB Construida

exit
