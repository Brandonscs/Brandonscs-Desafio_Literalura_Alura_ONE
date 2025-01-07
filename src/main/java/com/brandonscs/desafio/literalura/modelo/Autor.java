package com.brandonscs.desafio.literalura.modelo;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer yearNacimiento;
    private Integer yearMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> libros;

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        nombre = datosAutor.nombre();
        yearNacimiento = datosAutor.yearNacimiento();
        yearMuerte = datosAutor.yearMuerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getYearNacimiento() {
        return yearNacimiento;
    }

    public void setYearNacimiento(Integer yearNacimiento) {
        this.yearNacimiento = yearNacimiento;
    }

    public Integer getYearMuerte() {
        return yearMuerte;
    }

    public void setYearMuerte(Integer yearMuerte) {
        this.yearMuerte = yearMuerte;
    }

    public List<Book> getLibros() {
        return libros;
    }

    public void setLibros(List<Book> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String nombreAutor = (nombre == null) ? "Desconocido" : nombre;
        String nacimiento = (yearNacimiento == null) ? "N/A" : String.valueOf(yearNacimiento);
        String muerte = (yearMuerte == null) ? "N/A" : String.valueOf(yearMuerte);
        String librosListados = libros.stream()
                .map(Book::getTitulo)
                .collect(Collectors.joining("\n"));

        return "************************************" +
                "\nNombre: " + nombreAutor +
                "\nAño de nacimiento: " + nacimiento +
                "\nAño de Muerte: " + muerte +
                "\nLibros: " + librosListados +
                "\n************************************";
    }
}
