# MandelbrotRest
Simple Mandelbrot REST implementation
## Create executable jar with dependencies and start the server
To Package the jar file you will need the maven build tool:  
https://maven.apache.org/install.html  
Run the following command within the Mandelbrot directory:

    mvn package

This will create a "target" folder. Inside this folder run:

    java -jar mandelbrot-1.0-SNAPSHOT-jar-with-dependencies.jar

The REST Service is available at:

    http://localhost:8080/Mandelbrot/getMandelbrot?w=1920&h=1080&it=1000

w = width of generated image  
h = height of generated image  
it = iterations
