/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.controladores;
import egg.web.Libreria2.entidades.Autor;
import egg.web.Libreria2.entidades.Editorial;
import egg.web.Libreria2.excepciones.ErrorServicio;
import egg.web.Libreria2.servicios.LibroServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Max
 */
@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;
    
    
    @PostMapping("/editarLibro")
    public String editarLibro(ModelMap modelo, @RequestParam String id,@RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Autor autor, @RequestParam Editorial editorial){
        
        try {
            libroServicio.modificarLibro(id, isbn, titulo, anio, ejemplares, autor.getId(),editorial.getId());
        } catch (ErrorServicio ex) {
            modelo.put("errorEditar", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libro.html";
        }
        return "redirect:/libro";
    }
    
    @PostMapping("/ingresarLibro")
    public String ingresarLibro(ModelMap modelo, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Autor autor, @RequestParam Editorial editorial){
        try {
            libroServicio.crearLibro(isbn, titulo, anio, ejemplares, autor.getId(), editorial.getId());
        } catch (ErrorServicio ex) {
            modelo.put("errorIngresar", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libro.html";
        }
        return "redirect:/libro";
    }
    
    @PostMapping("/mostrarLibro")
    public String mostrarLibro(@RequestParam String id){
        try {
            libroServicio.buscarLibro(id);
        } catch (ErrorServicio ex) {
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "libro.html";
    }
    
    @PostMapping("/bajaLibro")
    public String bajaLibro(ModelMap modelo,@RequestParam String id){
        try {
            libroServicio.bajaLibro(id);
        } catch (ErrorServicio ex) {
            modelo.put("errorBaja", ex.getMessage());
            Logger.getLogger(indexControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libro.html";
        }
        return "redirect:/libro";
    }
    
    
}

