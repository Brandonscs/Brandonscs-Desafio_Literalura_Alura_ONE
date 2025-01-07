package com.brandonscs.desafio.literalura.repository;

import com.brandonscs.desafio.literalura.modelo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitulo(String titulo);

    @Query("SELECT DISTINCT b.lenguaje FROM Book b")
    List<String> econtrarLenguajesDisponibles();

    List<Book> findByLenguaje(String idiomaElegido);
}
