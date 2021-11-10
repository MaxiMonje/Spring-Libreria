/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.controladores;

import egg.web.Libreria2.entidades.Editorial;
import egg.web.Libreria2.excepciones.ErrorServicio;
import egg.web.Libreria2.servicios.EditorialServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    
    @GetMapping("/mostrar")
    public ModelAndView mostrarTodos() throws ErrorServicio{
        
        ModelAndView mav = new ModelAndView("editorial");
        mav.addObject("listaEditorial", editorialServicio.listaEditoriales());
        return mav;
    }
    
    
    @PostMapping("/editarEditorial")
    public String editarEditorial(ModelMap modelo,@RequestParam String nombre,@RequestParam String id){
        try {
            editorialServicio.modificarEditorial(id, nombre);
        } catch (ErrorServicio ex) {
            modelo.put("errorEditar", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial.html";
        }
        
        return "redirect:/editorial";
    }
    
    @PostMapping("/ingresarEditorial")
    public String ingresarEditorial(ModelMap modelo, @RequestParam String nombre){
        
        try {
            editorialServicio.crearEditorial(nombre);
            
        } catch (ErrorServicio ex) {
            modelo.put("errorIngresar", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial.html";
        }
        return "redirect:/editorial";
    }
    
    @PostMapping("/mostrarEditorial")
    public String mostrarEditorial(ModelMap modelo,@RequestParam String id){
        try {
            editorialServicio.buscarEditorial(id);
        } catch (ErrorServicio ex) {
            modelo.put("errorMostrar", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/editorial";
        }
        return "redirect:/editorial";
    }
    
    @PostMapping("/bajaEditorial")
    public String bajaEditorial(ModelMap modelo, @RequestParam String id){
        try {
            editorialServicio.bajaEditorial(id);
        } catch (ErrorServicio ex) {
            modelo.put("errorBaja", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial.html";
        }
        return "redirect:/editorial";
    }
}