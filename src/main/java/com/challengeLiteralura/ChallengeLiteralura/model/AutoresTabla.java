package com.challengeLiteralura.ChallengeLiteralura.model;

import com.challengeLiteralura.ChallengeLiteralura.limitarLon.LimitarCadena;
import jakarta.persistence.*;

@Entity
@Table(name = "Autor")

public class AutoresTabla {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @OneToOne
    @JoinTable(name = "libros", joinColumns = @JoinColumn(name = "autor_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private Libro libros;

    public AutoresTabla(AutoresTabla autor){

    }
    public AutoresTabla(){}

    public AutoresTabla (DatosAutor datosAutor){
        this.nombre = LimitarCadena.limitarLongitud(datosAutor.nombre(), 200);
        if (datosAutor.birthyear() == null){
            this.fechaDeNacimiento = 1980;
        }else {
            this.fechaDeNacimiento = datosAutor.birthyear();
        }
        if (datosAutor.deathYear()== null){
            this.fechaDeFallecimiento= 3022;
        } else {
            this.fechaDeFallecimiento = datosAutor.deathYear();
        }
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

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }


    @Override
    public String toString() {
        return "AutoresTabla{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", fechaDeFallecimiento=" + fechaDeFallecimiento +
                ", libros=" + libros;
    }

    public Libro getLibros() {
        return libros;
    }

    public void setLibros(Libro libros) {
        this.libros = libros;
    }
}
