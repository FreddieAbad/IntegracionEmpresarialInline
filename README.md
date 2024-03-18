# IntegracionEmpresarialOutOfLine
Author: Freddy Abad

UPS - Departamento de Posgrados

### PREREQUISITOS
Ejecutar los siguientes comandos para levantar un contenedor de postgres en Docker :


### PARA CORRER EL PROYECTO

En caso se requerir modificar las credenciales de conexion a la base, o de cambiar el nombre del archivo a validar, dirigirse al archivo (src/main/resources/application.properties)
Las variables ruta :




### INFORMACION DEL PROYECTO

El proyecto se genero usando Apache Camel como herramienta orientada a integracion empresarial.

##### ESTRUCTURA DEL PROYECTO
Como se ve en la siguiente imagen se tiene una jerarquia dentro del proyecto (camel-integrationinline-fabad/src/main/java/org/apache/camel/learn):

builder: Clase que extiende de RouteBuilder, donde se detalla el flujo a seguir en la validacion, insercion, logs.

model: Clase que modela cada linea para el manejo interno del servicio.

processor: Clases que contienen la logica de cada uno de los pasos establecidos en el paquete builder


#### Requerimientos abordados en el proyecto

