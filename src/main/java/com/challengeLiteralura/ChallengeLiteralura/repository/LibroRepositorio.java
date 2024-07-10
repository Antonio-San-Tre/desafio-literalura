package com.challengeLiteralura.ChallengeLiteralura.repository;

import com.challengeLiteralura.ChallengeLiteralura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {


    @Query("SELECT l FROM Libro l WHERE l.idiomas >= :idioma")
    List<Libro> findForLenguage (String idioma);
}
