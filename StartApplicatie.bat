::
:: Batchfile for starting Fysiotherapie RMI.
::

:: Start java
start cmd /c Startregisttry.bat

java -classpath .\bin;.\lib\dom4j-1.6.1.jar;.\lib\miglayout-4.0.jar;.\lib\jaxen-1.1.6.jar;.\lib\jdom-2.0.5.jar -Djava.security.policy=server.policy Systeem.Main.Main

:: 
:: View all security settings.
::
::-Djava.security.debug=access,failure

@pause