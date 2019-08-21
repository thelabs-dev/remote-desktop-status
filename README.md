# Remote-Desktop-Status

## Descripción
Esta es una herramienta web desarrollada en java que permite ver el estado de las sesiones remotas en un Windows Server.
La aplicación lista todas las sesiones de usuarios activas, y de ellas cuáles tienen una conexión por escritorio remoto, marcándola como “OCUPADO”.
Mientras se tenga foco en la página, la misma es actualizada cada 30 segundos.

## [Releases](https://github.com/thelabs-dev/Remote-Desktop-Status/releases)


El war _rds.war_ se puede deployar en tomcat, para acceder a la herramienta usar la url _/rds/view_

## Configuración
Ejecutar con:

    $ mvn jetty:run -Djetty.http.port=9999
  
o generar el war con:

    $ mvn package -Pproduction-mode
 
## Pruebas
- Winsows Server 2012R2
- Windows 7


## Capturas
![alt text](https://raw.githubusercontent.com/thelabs-dev/Remote-Desktop-Status/master/readme/img_1.png)
![alt text](https://raw.githubusercontent.com/thelabs-dev/Remote-Desktop-Status/master/readme/img_2.png)
