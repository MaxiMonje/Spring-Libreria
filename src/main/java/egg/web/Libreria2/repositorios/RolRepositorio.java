/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.repositorios;

import egg.web.Libreria2.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Max
 */
@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer> {
    
}
