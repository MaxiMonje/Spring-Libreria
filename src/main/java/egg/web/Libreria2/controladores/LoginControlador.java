/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.controladores;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Max
 */
@Controller
public class LoginControlador {
    
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required= false) String error, @RequestParam(required=false) String logout, Principal principal){
        ModelAndView modelAndView = new ModelAndView("login");
        
        if(error != null){
            modelAndView.addObject("error", "Usuario o Contraseña Inválida");
        }
        
        if(logout!=null){
            modelAndView.addObject("logout", "Ha cerrado sesión correctamente");
        }
        
        if(principal != null){
            modelAndView.setViewName("redirect:/");
        }
        
        return modelAndView;
    }
    
    
}
