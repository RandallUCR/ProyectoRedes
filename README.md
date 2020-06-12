# ProyectoRedes
Para el uso de la aplicación es necesario implementar jdk 8 o superior, conector a base de datos mysql (incluido en los ambos proyectos).

El FTPServer incluye una conexión a base de datos para manejar el control de usarios y registrarlos, también posee una clase Server, la cual contiene un server socket el cual se iniciará en el puerto indicado por el usuario, al inicar este inicia un hilo el cual iniciara un hilo con cada socket cliente que se conecte, creando una carpeta si no la tiene o enviadole al cliente los datos de su propia carpeta.

El FTPCliente incluye también una conexion a la base de datos para realizar el proceso de login y comenzar a utilizar el servidor ftp, el cliente debe ingresa el puerto del servidor al que desea conectarse, una vez dentro el usuario podra enviar archivos al ServerSocket y se les mostraran estos en una tabla.
