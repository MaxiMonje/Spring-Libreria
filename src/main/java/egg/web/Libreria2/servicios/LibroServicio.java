/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.servicios;

import egg.web.Libreria2.entidades.Editorial;
import egg.web.Libreria2.entidades.Libro;
import egg.web.Libreria2.repositorios.LibroRepositorio;
import egg.web.Libreria2.entidades.Autor;
import egg.web.Libreria2.excepciones.ErrorServicio;
import egg.web.Libreria2.repositorios.AutorRepositorio;
import egg.web.Libreria2.repositorios.EditorialRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Max
 */
@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio repositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, String idAutor, String idEditorial) throws ErrorServicio{
        
        if(isbn==null){
           throw new ErrorServicio("El campo ISBN no puede ser nulo"); 
        }
        
        if(titulo==null || titulo.isEmpty()){
           throw new ErrorServicio("El campo titulo no puede ser nulo"); 
        }
        
        if(anio==null){
           throw new ErrorServicio("El campo año no puede ser nulo"); 
        }
        
        if(idAutor==null || idAutor.isEmpty()){
           throw new ErrorServicio("El campo autor no puede ser nulo"); 
        }
        
        if(idEditorial==null || idEditorial.isEmpty()){
           throw new ErrorServicio("El campo editorial no puede ser nulo"); 
        }
        
        Libro libro = new Libro();
        Optional<Autor> respuestaAutor= autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial= editorialRepositorio.findById(idEditorial);
        if(respuestaAutor.isPresent() && respuestaEditorial.isPresent()){
                
            Autor autor = respuestaAutor.get();
            Editorial editorial = respuestaEditorial.get();
            
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setAlta(true);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
        
        
            repositorio.save(libro);
        }else{
            throw new ErrorServicio("No se encontró el autor o editorial solicitado");
        }
        
    }
    
    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, String idAutor, String idEditorial ) throws ErrorServicio{
        
        if(id==null || id.isEmpty()){
            throw new ErrorServicio("El campo ID no puede ser nulo");
        }
        
        if(isbn==null){
           throw new ErrorServicio("El campo ISBN no puede ser nulo"); 
        }
        
        if(titulo==null || titulo.isEmpty()){
           throw new ErrorServicio("El campo titulo no puede ser nulo"); 
        }
        
        if(anio==null){
           throw new ErrorServicio("El campo año no puede ser nulo"); 
        }
        
        if(idAutor==null || idAutor.isEmpty()){
           throw new ErrorServicio("El campo autor no puede ser nulo"); 
        }
        
        if(idEditorial==null || idEditorial.isEmpty()){
           throw new ErrorServicio("El campo editorial no puede ser nulo"); 
        }
        
        Optional<Libro> respuesta = repositorio.findById(id);
        if(respuesta.isPresent()){
            Libro libro = new Libro();
            
            Optional<Autor> respuestaAutor= autorRepositorio.findById(idAutor);
            Optional<Editorial> respuestaEditorial= editorialRepositorio.findById(idEditorial);
                if(respuestaAutor.isPresent() && respuestaEditorial.isPresent()){

                    Autor autor = respuestaAutor.get();
                    Editorial editorial = respuestaEditorial.get();
                    
                    libro.setAutor(autor);
                    libro.setEditorial(editorial);
                    libro.setAnio(anio);
                    libro.setEjemplares(ejemplares);
                    libro.setIsbn(isbn);
                    libro.setTitulo(titulo);
                    repositorio.save(libro);
                }else{
                    throw new ErrorServicio("No se encontró el autor o editorial solicitado");
                }
        }else{
            throw new ErrorServicio("No se encontró el libro solicitado");
        }
        
        
    }
    
    @Transactional
    public void bajaLibro(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id del libro no puede ser nulo");
            }
            
            Optional<Libro> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Libro libro = respuesta.get();
                libro.setAlta(false);
            
                repositorio.save(libro);
            }else{
                throw new ErrorServicio("No se encontró el libro solicitado");
            }
        }
        
    @Transactional
    public void altaLibro(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id del autor no puede ser nulo");
            }
            
            Optional<Libro> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Libro libro = respuesta.get();
                libro.setAlta(true);
            
                repositorio.save(libro);
            }else{
                throw new ErrorServicio("No se encontró el libro solicitado");
            }
        }
    
    @Transactional
    public Libro buscarLibro(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id del libro no puede ser nulo");
            }
            
            Optional<Libro> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Libro libro = respuesta.get();
                return libro;
                
            }else{
                throw new ErrorServicio("No se encontró el libro solicitado");
            }
            
            
        }
}
