package com.challengeLiteralura.ChallengeLiteralura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosGenerales(@JsonAlias("count") int count,
                             @JsonAlias("next") String next,
                             @JsonAlias("previous") String previous,
                             @JsonAlias("results") List <DatosLibro> resultados ) {
}
