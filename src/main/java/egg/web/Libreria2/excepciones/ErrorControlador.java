/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.Libreria2.excepciones;

/**
 *
 * @author Max
 */
public class ErrorControlador extends Exception {

    /**
     * Creates a new instance of <code>errorControlador</code> without detail
     * message.
     */
    public ErrorControlador() {
    }

    /**
     * Constructs an instance of <code>errorControlador</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ErrorControlador(String msg) {
        super(msg);
    }
}
