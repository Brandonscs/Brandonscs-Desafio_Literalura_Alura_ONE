package com.brandonscs.desafio.literalura.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    private String lenguaje;
    private Integer descargas;

    public Book() {
    }

    public Book(DatosBook datosBook) {
        this.titulo = datosBook.titulo();
        this.lenguaje = datosBook.lenguaje().get(0);
        this.descargas = datosBook.descargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        String nombreAutor = (autor == null || autor.getNombre() == null) ? "Desconocido" : autor.getNombre();

        return "************************************" +
                "\nTitulo: " + titulo +
                "\nAutor: " + nombreAutor +
                "\nLenguaje: " + lenguaje +
                "\nDescargas: " + descargas +
                "\n************************************";
    }
}
