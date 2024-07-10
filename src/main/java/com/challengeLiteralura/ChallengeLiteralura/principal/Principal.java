package com.challengeLiteralura.ChallengeLiteralura.principal;

import com.challengeLiteralura.ChallengeLiteralura.model.AutoresTabla;
import com.challengeLiteralura.ChallengeLiteralura.model.DatosGenerales;
import com.challengeLiteralura.ChallengeLiteralura.model.DatosLibro;
import com.challengeLiteralura.ChallengeLiteralura.model.Libro;
import com.challengeLiteralura.ChallengeLiteralura.repository.AutoresRepositorio;
import com.challengeLiteralura.ChallengeLiteralura.repository.LibroRepositorio;
import com.challengeLiteralura.ChallengeLiteralura.service.ConusmoApi;
import com.challengeLiteralura.ChallengeLiteralura.service.ConvierteDatos;

import java.util.*;

public class Principal {

    private Scanner entradaDatos = new Scanner(System.in);
    private ConusmoApi consumoApi = new ConusmoApi();
    private final String url_base = "https://gutendex.com/books/";
    private final String url_busqueda = "?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepositorio libroRepositorio;
    private AutoresRepositorio autoresRepositorio;

    public Principal(LibroRepositorio libroRepositorio, AutoresRepositorio autoresRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autoresRepositorio = autoresRepositorio;
    }


    public void muestraMenu (){

        var opcion = -1;
        while (opcion!=0){
            var menu = """
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    0- Salir""";

            System.out.println(menu);
            opcion = entradaDatos.nextInt();
            entradaDatos.nextLine();

            switch (opcion){
                case 1:
                    buscarLibrosWeb();
                    break;
                case 2:
                    buscarlibros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    buscarAutoresVivos();
                    break;
                case 5:
                    buscarPorIdiomas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo");

            }
        }
    }


    private void buscarlibros (){
        List<Libro> libros = libroRepositorio.findAll();

        if(!libros.isEmpty()){
            for (Libro libro: libros){
                System.out.println("************Libros**************");
                System.out.println("Titulo: "+libro.getTitulo());
                System.out.println("Autor: "+libro.getAutor().getNombre());
                System.out.println("Idioma: "+libro.getIdiomas());
                System.out.println("Descargas: "+libro.getNumeroDescargas());
                System.out.println("**********************************");
            }
        } else {
            System.out.println("***********NO SE ENCONTRARON RESULTADOS****************");
        }
    }


    private void buscarAutores (){
        List<AutoresTabla> autores = autoresRepositorio.findAll();
        if (!autores.isEmpty()){
            for(AutoresTabla autor: autores){
                System.out.println("*************AUTORES**************");
                System.out.println("Nombre: "+autor.getNombre());
                System.out.println("Fecha de nacimiento: "+autor.getFechaDeNacimiento());
                System.out.println("Fechas de fallecimiento: "+autor.getFechaDeFallecimiento());
                System.out.println("Libros: "+autor.getLibros().getTitulo());
                System.out.println("****************************************");
            }
        } else {
            System.out.println("No se encontraron resultados");
        }
    }


    private void buscarAutoresVivos (){
        System.out.println("Escriba el año que desee saber que autores vivieron en dicho año...");
        var anio = entradaDatos.nextInt();
        entradaDatos.nextLine();
        List<AutoresTabla> autores = autoresRepositorio.findForYear(anio);

        if (!autores.isEmpty()) {
            for (AutoresTabla autor : autores) {
                System.out.println("*************Autores****************");
                System.out.println(" Nombre: " + autor.getNombre());
                System.out.println(" Fecha de nacimiento: " + autor.getFechaDeNacimiento());
                System.out.println(" Fecha de fallecimiento: " + autor.getFechaDeFallecimiento());
                System.out.println(" Libros: " + autor.getLibros().getTitulo());
                System.out.println("**********************************");
            }
        } else {
            System.out.println("No se encontraron resultados");

        }

    }


    private void buscarPorIdiomas (){
        var menuIdiomas = """
                Selecione el idioma:
                1-Español
                2-Ingles
                """;
        System.out.println(menuIdiomas);
        var idiomaSeleccionado = entradaDatos.nextInt();
        entradaDatos.nextLine();

        var seleccion = "";
        if (idiomaSeleccionado == 1){
            seleccion = "es";
        } else if (idiomaSeleccionado == 2) {
            seleccion = "en";
        }

        List<Libro> libros = libroRepositorio.findForLenguage(seleccion);

        if(!libros.isEmpty()) {
            for(Libro libro :libros){
                System.out.println("*************Libro por idioma***************");
                System.out.println("Titulo: "+libro.getTitulo());
                System.out.println("Autor: "+libro.getAutor());
                System.out.println("Idioma: "+libro.getIdiomas());
                System.out.println("Numero de descargas: "+libro.getNumeroDescargas());
                System.out.println("******************************************");
            }
        } else {
            System.out.println("No se encontraron resultados");
        }
    }


    private void buscarLibrosWeb (){
        DatosGenerales datos = getDatosGenerales();
        Optional<DatosLibro> libroBuscado = datos.resultados().stream()
                .findFirst();
        if (libroBuscado.isPresent()){
            System.out.println("-------------------Libro----------------------");
            System.out.println("Titulo: "+libroBuscado.get().titulo());
            System.out.println("Lista de autores: "+libroBuscado.get().autores());
            System.out.println("Idiomas: "+libroBuscado.get().idiomas());
            System.out.println("Numero de descargas: "+libroBuscado.get().numeroDescargas());
            System.out.println("-----------------------------------------------");

        } else {
            System.out.println("Libro no encontrado");
        }


        if (!datos.resultados().isEmpty()){
            Libro libro = new Libro(datos.resultados().get(0));
            libroRepositorio.save(libro);
        }

        System.out.println("Datos: ");
        System.out.println(datos);
    }






    private DatosGenerales getDatosGenerales (){
        System.out.println("Escribe el nombre del libro que deseeas buscar...");
        var nombreLibro = entradaDatos.nextLine();
        var json = consumoApi.obtenerDatos(url_base+url_busqueda+nombreLibro.replace(" ", "+"));

        var datosConvertidos = conversor.obtenerDatos(json, DatosGenerales.class);
        return datosConvertidos;
    }




}
