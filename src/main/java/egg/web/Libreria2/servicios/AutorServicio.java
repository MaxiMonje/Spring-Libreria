/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.servicios;

import egg.web.Libreria2.repositorios.AutorRepositorio;
import egg.web.Libreria2.entidades.Autor;
import egg.web.Libreria2.excepciones.ErrorServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Max
 */

@Service
public class AutorServicio {

        @Autowired
        private AutorRepositorio repositorio;

        @Transactional
        public void crearAutor(String nombre) throws ErrorServicio{
            
            if(nombre==null || nombre.isEmpty()){
                throw new ErrorServicio("El nombre del autor no puede ser nulo");
            }
            
            Autor autor = new Autor();

            autor.setNombre(nombre);
            autor.setAlta(true);

            repositorio.save(autor);
        }
        
        @Transactional
        public void modificarAutor(String id, String nombre) throws ErrorServicio{
            
            if(nombre==null || nombre.isEmpty()){
                throw new ErrorServicio("El nombre del autor no puede ser nulo");
            }
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id del autor no puede ser nulo");
            }
            
            
            Optional<Autor> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Autor autor = respuesta.get();
                autor.setNombre(nombre);
            
                repositorio.save(autor);
            }else{
                throw new ErrorServicio("No se encontró el autor solicitado");
            }
            
        }
        
        @Transactional
        public void bajaAutor(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id del autor no puede ser nulo");
            }
            
            Optional<Autor> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Autor autor = respuesta.get();
                autor.setAlta(false);
            
                repositorio.save(autor);
            }else{
                throw new ErrorServicio("No se encontró el autor solicitado");
            }
        }
        
        @Transactional
        public void altaAutor(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id del autor no puede ser nulo");
            }
            
            Optional<Autor> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Autor autor = respuesta.get();
                autor.setAlta(true);
            
                repositorio.save(autor);
            }else{
                throw new ErrorServicio("No se encontró el autor solicitado");
            }
        }
        
        @Transactional
        public Autor buscarAutor(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id del autor no puede ser nulo");
            }
            
            Optional<Autor> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Autor autor = respuesta.get();
                return autor;
                
            }else{
                throw new ErrorServicio("No se encontró el autor solicitado");
            }
            
            
        }
        
        @Transactional
        public List<Autor> listaAutores() throws ErrorServicio{
        List<Autor> autores = new ArrayList();
        List<Autor> autoresAlta = new ArrayList();
        
        autores= repositorio.findAll();
        
        for(Autor a : autores){
            if(a.getAlta()==true){
                autoresAlta.add(a);
            }
        }
        
        if(autoresAlta.isEmpty()){
            throw new ErrorServicio("No hay editoriales disponibles");
        }else{
            return autoresAlta;
        }
        
    }
        
    }

