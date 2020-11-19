/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador.util;

/**
 *
 * @author Juan Jose Ospina B
 */
public class CelularExcepcion extends Exception{

    public CelularExcepcion() {
    }

    public CelularExcepcion(String message) {
        super(message);
    }

    public CelularExcepcion(String message, Throwable cause) {
        super(message, cause);
    }

    public CelularExcepcion(Throwable cause) {
        super(cause);
    }
}
