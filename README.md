# Proyecto de Gestor de Libros

## Descripción

Este proyecto es una aplicación Java que permite gestionar información de libros y autores. Utiliza La API Gutendex externa para buscar libros por título, almacenar información sobre los libros y autores en una base de datos, y realizar varias operaciones.

## Características

- **Buscar libro por título**: Permite buscar un libro por su título consultando la API.
- **Listar libros registrados**: Muestra todos los libros almacenados en la base de datos.
- **Listar autores registrados**: Muestra todos los autores almacenados en la base de datos.
- **Listar autores vivos en un determinado año**: Muestra los autores que estaban vivos en un año especificado por el usuario.
- **Listar libros por idioma**: Muestra los libros disponibles en un idioma especificado por el usuario.

## Estructura del Proyecto

### Clases Principales

- **Principal**: Clase principal que contiene el menú de la aplicación y métodos para interactuar con el usuario.
- **ConsumoApi**: Clase responsable de manejar las solicitudes a la API externa.
- **ConvierteDatos**: Clase para convertir los datos recibidos de la API en objetos Java.
- **BookRepository**: Repositorio para gestionar las operaciones de persistencia de libros.
- **AutorRepository**: Repositorio para gestionar las operaciones de persistencia de autores.

### Métodos Importantes

#### Menú Principal

```java
public void mostrarMenu()
```

Muestra el menú principal y maneja las opciones seleccionadas por el usuario.

#### Buscar Libro por Título

```java
private void buscarLibro()
```

Busca un libro por su título utilizando una API externa y guarda la información en la base de datos.

#### Listar Libros

```java
private void mostrarLibros()
```

Lista todos los libros registrados en la base de datos.

#### Listar Autores

```java
private void mostrarAutores()
```

Lista todos los autores registrados en la base de datos.

#### Buscar Autores por Año

```java
private void buscarAutoresPorAño()
```

Busca y lista autores que estaban vivos en un año especificado por el usuario.

#### Listar Libros por Idioma

```java
private void buscarLibrosPorIdioma()
```

Lista libros disponibles en un idioma especificado por el usuario.

## Instrucciones para Ejecutar

1. **Configurar Base de Datos**: Asegúrese de tener configurada la base de datos correspondiente y que las clases `BookRepository` y `AutorRepository` estén correctamente configuradas para interactuar con ella.
2. **Ejecutar la Aplicación**: Compilar y ejecutar la clase `Principal`.
3. **Interacción**: Utilice el menú para realizar las diferentes operaciones soportadas por la aplicación.

## Requisitos

- Java 11 o superior
- Dependencias de Hibernate o cualquier framework JPA
- Conexión a Internet para acceder a la API externa

## Tecnologías Utilizadas

- Java
- Hibernate / JPA
- API REST

---

**Nota**: Asegúrese de manejar las posibles excepciones y errores para mejorar la robustez de la aplicación. Además, puede ampliar la funcionalidad agregando métodos adicionales o integrando nuevas características según sea necesario.
