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
Ejecutar comand mvn clean package
El jar se generará el dentro de la carpeta target el directorio del proyecto
calculator/target/calculator-0.0.1-SNAPSHOT.jar



