package com.challengeLiteralura.ChallengeLiteralura.model;

import com.challengeLiteralura.ChallengeLiteralura.limitarLon.LimitarCadena;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String titulo;
    private String idiomas;
    private Integer numeroDescargas;
    @OneToOne(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AutoresTabla autor;


    public Libro (){}

    public Libro (DatosLibro datosLibro){
        this.titulo = LimitarCadena.limitarLongitud(datosLibro.titulo(), 200);
        this.numeroDescargas = datosLibro.numeroDescargas();
        if (!datosLibro.idiomas().isEmpty())
            this.idiomas = datosLibro.idiomas().get(0);
        if (!datosLibro.autores().isEmpty()){
            for(DatosAutor datosAutor: datosLibro.autores()){
                this.autor = new AutoresTabla(datosAutor);
                break;
            }
        }
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public AutoresTabla getAutor() {
        return autor;
    }

    public void setAutor(AutoresTabla autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro: " +
                "Id=" + Id +
                ", titulo='" + titulo +
                ", idiomas='" + idiomas + '\'' +
                ", numeroDescargas=" + numeroDescargas +
                ", autor=" + autor;
    }
}
