package com.challengeLiteralura.ChallengeLiteralura.repository;

import com.challengeLiteralura.ChallengeLiteralura.model.AutoresTabla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutoresRepositorio extends JpaRepository <AutoresTabla, Long>  {

    @Query("SELECT a FROM AutoresTabla a WHERE :año between a.fechaDeNacimiento AND a.fechaDeFallecimiento")
    List<AutoresTabla> findForYear(int año);

}
