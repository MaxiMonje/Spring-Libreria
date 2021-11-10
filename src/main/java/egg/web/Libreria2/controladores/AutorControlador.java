/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.controladores;


import egg.web.Libreria2.excepciones.ErrorServicio;
import egg.web.Libreria2.servicios.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Max
 */
@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    
    @GetMapping("/mostrar")
    public ModelAndView mostrarTodos() throws ErrorServicio{
        
        ModelAndView mav = new ModelAndView("autor");
        mav.addObject("listaAutor", autorServicio.listaAutores());
        return mav;
    }
    
    @PostMapping("/editarAutor")
    @PreAuthorize("hasRole('ADMIN')")
    public String editarAutor(ModelMap modelo,@RequestParam String nombre,@RequestParam String id){
        
        try {
            autorServicio.modificarAutor(id, nombre);
        } catch (ErrorServicio ex) {
            modelo.put("errorEditar", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor.html";
        }
        return "redirect:/autor";
    }
    
    @PostMapping("/ingresarAutor")
    public String ingresarAutor(ModelMap modelo,@RequestParam String nombre){
        try {
            autorServicio.crearAutor(nombre);
        } catch (ErrorServicio ex) {
            modelo.put("errorIngresar", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor.html";
        }
        return "redirect:/autor";
    }
    
    @PostMapping("/mostrarAutor")
    public String mostrarAutor(ModelMap modelo, @RequestParam String id){
        try {
            autorServicio.buscarAutor(id);
        } catch (ErrorServicio ex) {
            modelo.put("errorMostrar", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/autor";
    }
   
    @PostMapping("/bajaAutor")
    @PreAuthorize("hasRole('ADMIN')")
    public String bajaAutor(ModelMap modelo, @RequestParam String id){
        
        try {
            autorServicio.bajaAutor(id);
        } catch (ErrorServicio ex) {
            modelo.put("errorBaja", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor.html";
        }
        return "redirect:/autor";
    }
}
