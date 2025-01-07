package com.brandonscs.desafio.literalura.repository;

import com.brandonscs.desafio.literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.yearNacimiento <= :yearBusqueda AND (a.yearMuerte IS NULL OR a.yearMuerte >= :yearBusqueda)")
    List<Autor> autoresVivosEnYearDeterminado(int yearBusqueda);
}