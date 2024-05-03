Hol@, dejo a continuación el desarrollo de mi prueba técnica:

En una primera instancia, debemos tener en cuenta lo siguiente: 

Forma de ejecución:

1. Se debe realizar la descarga de dependencias y luego solamente ejecutar la aplicación, ya se con su IDE de preferencias o bien 
ejecutando el siguiente comando: **mvn spring-boot:run**

Descripción de la solución: 

A continuación, se deja una pequeña explicación de las decisiones tomados dentro del proyecto para abarcar cada uno de los requerimiento: 

1. En una primera instancia, tenemos que decir que la estructura del proyecto se basa en lo siguiente:
   1. Controller, Servicios, Modelo, DTO, DAO (Repositorio), en donde cada uno de estos tiene una responsabilidad
   particular dentro del ciclo de ejecución de cada uno de las peticiones. 
   2. Por otro lado, se dejo una carpeta de configuración que se encarga de tener las definiciones generales del proyecto
   en donde podemos encontrar la implementación de una clase global de control de excepciones, que nos permite
    poder tener un mayor control de ellos y además, nos da la posibilidad de poder tener optar a crear nuestras propias
   clases de excepciones para manejar dentro del código.

Postman:
![</span><span>Oxigent Api](https://github.com/CrisEspinoza/oxigent/blob/main/images/postman.png)

Sin mas que agregar, se despide Cristian Eduardo Espinoza Silva. 
