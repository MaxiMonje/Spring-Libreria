/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.controladores;


import egg.web.Libreria2.entidades.Autor;
import egg.web.Libreria2.entidades.Editorial;
import egg.web.Libreria2.repositorios.AutorRepositorio;
import egg.web.Libreria2.repositorios.EditorialRepositorio;
import egg.web.Libreria2.servicios.AutorServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author Max
 */
@Controller
@RequestMapping("/")
public class indexControlador {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/autor")
    public String autor(){
        return "autor.html";
    }
    
    
    
    @GetMapping("/editorial")
    public String editorial(){
        return "editorial.html";
    }
    
    @GetMapping("/libro")
    public String libro(ModelMap modelo){
        
        List<Autor> vacia = autorRepositorio.findAll();
        List<Autor> autores = new ArrayList();
        for(Autor a : vacia){
            if(a.getAlta()==true){
                autores.add(a);
            }
        }
        modelo.put("listaAutor", autores );
        
        List<Editorial> vacia2 = editorialRepositorio.findAll();
        List<Editorial> editoriales = new ArrayList();
        for(Editorial e : vacia2){
            if(e.getAlta()==true){
                editoriales.add(e);
            }
        }
        modelo.put("listaEditorial", editoriales );
        
        return "libro.html";
    }

}
