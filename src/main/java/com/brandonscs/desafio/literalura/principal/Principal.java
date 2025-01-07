package com.brandonscs.desafio.literalura.principal;

import com.brandonscs.desafio.literalura.modelo.*;
import com.brandonscs.desafio.literalura.repository.AutorRepository;
import com.brandonscs.desafio.literalura.repository.BookRepository;
import com.brandonscs.desafio.literalura.service.ConsumoApi;
import com.brandonscs.desafio.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner leer = new Scanner(System.in);
    private BookRepository repositorioLibro;
    private AutorRepository repositorioAutor;
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convertirDatos = new ConvierteDatos();
    private List<Book> libros;
    private List<Autor> autores;
    private List<String> idiomas;
    private String menu = """
            **************************************************
            Elija una opcion a traves de su numero:
            
            1- Buscar libro por titulo
            2- Listar libros registrados
            3- Listar autores registrados
            4- Listar autores vivos en un determinado año
            5- Listar libros por idioma
            0- Salir
            **************************************************
            """;

    public Principal(BookRepository repositorioLibro, AutorRepository repositorioAutor) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioAutor = repositorioAutor;
    }

    public void mostrarMenu() {
        var eleccion = -1;
        while (eleccion != 0) {
            System.out.println(menu);

            if (leer.hasNextInt()) {
                eleccion = leer.nextInt();
                leer.nextLine();

                switch (eleccion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        mostrarLibros();
                        break;
                    case 3:
                        mostrarAutores();
                        break;
                    case 4:
                        buscarAutoresPorAño();
                        break;
                    case 5:
                        buscarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicacion");
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } else {
                System.out.println("Entrada invalida. Por favor, ingrese un número.");
                leer.nextLine();
            }
        }
    }

    public DatosBook obtenerDatosLibro() {
        System.out.println("Ingrese el nombre del libro que quiere buscar");
        var nombreLibro = leer.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        System.out.println(json);

        if (json == null || json.isEmpty()) {
            System.out.println("No se pudo obtener datos de la API.");
            return null;
        }

        RespuestaApi respuesta = convertirDatos.obtenerDatos(json, RespuestaApi.class);

        if (respuesta == null || respuesta.getResults() == null || respuesta.getResults().isEmpty()) {
            System.out.println("Libro no encontrado");
            return null;
        }

        DatosBook datos = respuesta.getResults().get(0);
        return procesarLibro(datos);
    }

    private DatosBook procesarLibro(DatosBook datos) {
        Optional<Book> libroExistente = repositorioLibro.findByTitulo(datos.titulo());
        if (libroExistente.isPresent()) {
            System.out.println("Libro ya registrado: \n" + libroExistente.get());
            return null;
        }

        System.out.println("Libro encontrado: \n" + datos);
        return datos;
    }

    private void buscarLibro() {
        DatosBook datos = obtenerDatosLibro();
        if (datos != null) {
            Book libro = new Book(datos);
            procesarAutor(datos.autores(), libro);
            guardarLibro(libro);
        }
    }

    private void guardarLibro(Book libro) {
        repositorioLibro.save(libro);
        System.out.println(libro);
    }

    private void procesarAutor(List<DatosAutor> autores, Book libro) {
        if (autores != null && !autores.isEmpty()) {
            DatosAutor datosAutor = autores.get(0);
            Autor autor = repositorioAutor.findByNombre(datosAutor.nombre())
                    .orElseGet(() -> guardarAutor(datosAutor));
            libro.setAutor(autor);
        }
    }

    private Autor guardarAutor(DatosAutor datosAutor) {
        Autor nuevoAutor = new Autor(datosAutor);
        return repositorioAutor.save(nuevoAutor);
    }

    private void mostrarLibros() {
        libros = repositorioLibro.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Book::getTitulo))
                .forEach(System.out::println);
    }

    private void mostrarAutores() {
        autores = repositorioAutor.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getYearNacimiento, Comparator.nullsLast(Comparator.naturalOrder())))
                .forEach(System.out::println);
    }

    private void buscarAutoresPorAño() {
        System.out.println("Ingrese un año para ver autores vivos en ese año:");
        try {
            Integer yearBusqueda = leer.nextInt();
            leer.nextLine();
            mostrarAutoresPorYearDeterminado(yearBusqueda);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            leer.nextLine();
        }
    }

    private void mostrarAutoresPorYearDeterminado(Integer yearBusqueda) {
        List<Autor> autores = repositorioAutor.autoresVivosEnYearDeterminado(yearBusqueda);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            autores.stream()
                    .sorted(Comparator.comparing(Autor::getYearNacimiento, Comparator.nullsLast(Comparator.naturalOrder())))
                    .forEach(System.out::println);
        }
    }


    private void buscarLibrosPorIdioma() {
        List<String> idiomas = repositorioLibro.econtrarLenguajesDisponibles();
        System.out.println("Escriba uno de los siguientes lenguajes para listar los libros:");
        idiomas.forEach(System.out::println);
        String idiomaElegido = leer.nextLine();
        if (validarSeleccionIdiomas(idiomaElegido, idiomas)) {
            mostrarLibrosPorIdioma(idiomaElegido);
        } else {
            System.out.println("Entrada inválida.");
        }
    }

    private boolean validarSeleccionIdiomas(String idiomaElegido, List<String> idiomas) {
        return idiomas.contains(idiomaElegido);
    }

    private void mostrarLibrosPorIdioma(String idiomaElegido) {
        libros = repositorioLibro.findByLenguaje(idiomaElegido);
        libros.stream()
                .sorted(Comparator.comparing(Book::getTitulo))
                .forEach(System.out::println);
    }
}