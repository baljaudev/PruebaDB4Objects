# PruebaDB4Objects
Prueba con DB4O en Java

## Enunciado:

•	Crea una base de datos DB4O biblioteca.yap para almacenar información sobre autores y libros. La información de cada Autor está formada por sus iniciales, su nombre y su nacionalidad y la información de cada Libro está formada por título, autor y número de páginas.   
•	Crea las clases Autor y Libro para manejar objetos con información de autores y libros. No se deben crear métodos que no se usen.  
•	Crea una clase AccesoBibliotecaDB que se encargue de establecer la conexión con la base de datos, contenga los métodos necesarios para manipular la información y un método para cerrar la conexión.  
•	Crea una clase ejecutable GestionBiblioteca que cree una instancia de la clase AccesoBibliotecaDB y que muestre un menú con las siguientes opciones:  
-	Introducir IL para insertar autores y libros. 
-	Introducir ML para modificar el número de páginas de un libro.
-	Introducir CT para consultar todos los libros.
-	Introducir CL para consultar los libros con más de 300 páginas.
-	Introducir S para terminar el proceso.
Si el usuario introduce IL se le deberán pedir los datos del autor y los datos del libro. Antes de insertar el autor se deberá comprobar si existe, solo habrá que insertarlo si no existe.
Si el usuario introduce ML se le deberá pedir el título del libro y el nuevo número de páginas.
En cada caso habrá que invocar al correspondiente método de la clase AccesoBibliotecaDB. No hay que olvidarse de cerrar la conexión.
