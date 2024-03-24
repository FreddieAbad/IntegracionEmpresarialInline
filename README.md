# IntegracionEmpresarialOutOfLine
Author: Freddy Abad

UPS - Departamento de Posgrados

### PREREQUISITOS
Ejecutar los siguientes comandos para levantar un contenedor de postgres en Docker :
mvn clean compile package
docker-compose build
docker-compose up 
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



http://localhost:80/receive_client_info

{
        "codigo": 1,
        "identificacion": "user1Online",
        "nombre": "Juan Perez Online",
        "email": "user2@example.com",
        "canal": "online"
}


http://localhost:80/receive_client_info

{
        "codigo": 1,
        "identificacion": "user1Offline",
        "nombre": "Juan Perez Offline",
        "email": "user2@example.com",
        "canal": "offline"
}

http://localhost:80/receive_client_info
{
        "codigo": 2,
        "identificacion": "user2Online",
        "nombre": "Juan Perez Online",
        "email": "user2@example.com",
        "canal": "personal"
}



SERVICIOS NO CAMEL
http://localhost:8080/users
{
    "codigo": 623457,
    "identificacion": "user2",
    "nombre": "Juan Perez",
    "email": "user2@example.com",
    "canal": "online"
}
http://localhost:5000/users
{
    "codigo": 623457,
    "identificacion": "user2",
    "nombre": "Juan Perez",
    "email": "user2@example.com",
    "canal": "offline"
}

GET
http://localhost:80/users/offline
http://localhost:80/users/online
http://localhost:8080/get_users
http://localhost:5000/get_users