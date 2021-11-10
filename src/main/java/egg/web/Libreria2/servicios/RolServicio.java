/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.servicios;

import egg.web.Libreria2.entidades.Rol;
import egg.web.Libreria2.repositorios.RolRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Max
 */

@Service
public class RolServicio {
    
    @Autowired
    private RolRepositorio rolRepositorio;
    
    @Transactional
    public void crear(String nombre){
        Rol rol = new Rol();
        rol.setNombre(nombre);
        rolRepositorio.save(rol);
    }
    
    @Transactional
    public List<Rol> buscarTodos(){
        return rolRepositorio.findAll();
    }
}
