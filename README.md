## Microservicio Calculadora
# Realiza la suma/resta de dos parametros

# Endopint
POST: {url}/sanitas/calculate


# Body
{
  "param1":"1",
  "param2":"2",
  "operation": "ADD"
}

# Request
{
    "result": 3
}

# Generacion .jar
Instalar liberia externa en el repositorio de maven 
Para ello vamos a la raiz del proyecto y ejecutamos el siguiente comando maven
"mvn install:install-file -Dfile=libs/tracer-1.0.0.jar -DgroupId=com.tracer -DartifactId=tracer -Dversion=1.0.0 -Dpackaging=jar"


Posteriormente ejecutar: "mvn clean pakcage"
El jar se generará el dentro de la carpeta target el directorio del proyecto
calculatorSanitas/target/calculator-0.0.1-SNAPSHOT.jar

# Imagen docker 
Para levantar la imagen docker del servicio es necesario tener instalado en el sistema docker.
Ejecutar en "mvn clean install" en la raiz del proyecto
Ejecutar el comando "docker-compose up" en la raiz del proyecto
Al levantar el microservicio en la consola se puede visualizar la ip que tiene asignada en docker (172.18.0.2)
