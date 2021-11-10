/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.controladores;

import egg.web.Libreria2.servicios.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Max
 */
@Controller
@RequestMapping("/rol")
public class RolControlador {
    
    @Autowired
    private RolServicio rolServicio;
    
    @GetMapping("/crear")
    public ModelAndView crear(){
        return new ModelAndView("rol-formulario");
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre){
        rolServicio.crear(nombre);
        return new RedirectView("/");
    }
}
