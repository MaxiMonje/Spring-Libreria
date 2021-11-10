/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.servicios;

import egg.web.Libreria2.entidades.Editorial;
import egg.web.Libreria2.excepciones.ErrorServicio;
import egg.web.Libreria2.repositorios.EditorialRepositorio;
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
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio repositorio;
    
    @Transactional
    public void crearEditorial(String nombre) throws ErrorServicio{
        
        if(nombre==null || nombre.isEmpty()){
                throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
            }
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        repositorio.save(editorial);
    }
    
    @Transactional
    public void modificarEditorial(String id, String nombre) throws ErrorServicio{
        
        if(nombre==null || nombre.isEmpty()){
                throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
            }
        
        if(id==null || id.isEmpty()){
                throw new ErrorServicio("El ID de la editorial no puede ser nulo");
            }
        
        
        
        Optional<Editorial> respuesta = repositorio.findById(id);
        
        if(respuesta.isPresent()){
            Editorial editorial = repositorio.findById(id).get();
        
            editorial.setNombre(nombre);
        
            repositorio.save(editorial);
        }else{
            throw new ErrorServicio("No se encontró la editorial solicitada");
        }
    }
    
    @Transactional
    public void bajaEditorial(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id de la editorial no puede ser nulo");
            }
            
            Optional<Editorial> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Editorial editorial = respuesta.get();
                editorial.setAlta(false);
            
                repositorio.save(editorial);
            }else{
                throw new ErrorServicio("No se encontró la editorial solicitada");
            }
        }
        
    @Transactional
    public void altaEditorial(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id de la editorial no puede ser nulo");
            }
            
            Optional<Editorial> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Editorial editorial = respuesta.get();
                editorial.setAlta(true);
            
                repositorio.save(editorial);
            }else{
                throw new ErrorServicio("No se encontró la editorial solicitada");
            }
        }
    @Transactional
    public Editorial buscarEditorial(String id) throws ErrorServicio{
            
            if(id==null || id.isEmpty()){
                throw new ErrorServicio("El id de la editorial no puede ser nulo");
            }
            
            Optional<Editorial> respuesta = repositorio.findById(id);
           
            if(respuesta.isPresent()){
                
                Editorial editorial = respuesta.get();
                return editorial;
                
            }else{
                throw new ErrorServicio("No se encontró la editorial solicitado");
            }
            
            
        }
    
    @Transactional
    public List<Editorial> listaEditoriales() throws ErrorServicio{
        List<Editorial> editoriales = new ArrayList();
        List<Editorial> editorialesAlta = new ArrayList();
        
        editoriales= repositorio.findAll();
        
        for(Editorial e : editoriales){
            if(e.getAlta()==true){
                editorialesAlta.add(e);
            }
        }
        
        if(editorialesAlta.isEmpty()){
            throw new ErrorServicio("No hay editoriales disponibles");
        }else{
            return editorialesAlta;
        }
        
    }
}
