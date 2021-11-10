/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.controladores;

import egg.web.Libreria2.entidades.Rol;
import egg.web.Libreria2.excepciones.ErrorServicio;
import egg.web.Libreria2.servicios.RolServicio;
import egg.web.Libreria2.servicios.UsuarioServicio;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



/**
 *
 * @author Max
 */
@Controller
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private RolServicio rolServicio;
    
    @GetMapping("/crearUsuario")
    public ModelAndView crearUsuario(Principal principal){
        
        ModelAndView modelAndView = new ModelAndView("/crearUsuario");
        
        modelAndView.addObject("roles", rolServicio.buscarTodos());
        
        if(principal != null){
            modelAndView.setViewName("redirect:/");
        }
        
       return modelAndView;
    }
    
    @PostMapping("/crearUsuario/guardar")
    public String guardar(ModelMap modelo, @RequestParam Long dni, @RequestParam String nombre,@RequestParam String apellido, @RequestParam String username, @RequestParam String password, @RequestParam Rol rol){
       try{
           usuarioServicio.crearUsuario(nombre, apellido, password, username, dni, rol);
         
       } catch (ErrorServicio ex) {
            modelo.put("errorRegistrar", ex.getMessage());
            modelo.put("dni", dni);
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("username", username );
            modelo.put("password", password);
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "crearUsuario.html";
        }
        return "redirect:/";
    }
}
