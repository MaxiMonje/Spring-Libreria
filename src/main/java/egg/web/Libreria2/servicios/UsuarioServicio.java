package egg.web.Libreria2.servicios;

import egg.web.Libreria2.entidades.Rol;
import egg.web.Libreria2.entidades.Usuario;
import egg.web.Libreria2.excepciones.ErrorServicio;
import egg.web.Libreria2.repositorios.UsuarioRepositorio;
import java.util.Collections;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



/**
 *
 * @author Max
 */
@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String MENSAJE = "El username ingresado no existe %s";

    @Transactional
    public void crearUsuario(String nombre, String apellido, String password, String username, Long dni, Rol rol) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede ser nulo");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede ser nulo");
        }

        if (password == null || password.isEmpty()) {
            throw new ErrorServicio("La password del usuario no puede ser nulo");
        }

        if (username == null || username.isEmpty()) {
            throw new ErrorServicio("El username del usuario no puede ser nulo");
        }

        if (dni == null) {
            throw new ErrorServicio("El DNI del usuario no puede ser nulo");
        }
        
        if(usuarioRepositorio.existsById(dni)){
            throw new ErrorServicio("Ya hay un usuario registrado con ese DNI");
        }
        
        if(usuarioRepositorio.existsUsuarioByUsername(username)){
            throw new ErrorServicio("El usuario ingresado ya existe, ingrese otro");
        }
        
        if(rol==null){
            throw new ErrorServicio("El rol no puede ser nulo");
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setClave(encoder.encode(password));
        usuario.setDni(dni);
        usuario.setUsername(username);
        usuario.setRol(rol);

        usuarioRepositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(MENSAJE, username)));
        
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre());
        
        return new User(usuario.getUsername(), usuario.getClave(), Collections.singletonList(authority));

    }
}
