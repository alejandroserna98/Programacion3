/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln.modelo;

import java.io.Serializable;

/**
 *
 * @author Juan Jose Ospina B
 */
public class Empleado implements Serializable {
 
    private String idEmpleado;
    private String NombreEmpleado;
    private double SueldoEmpleado;

    public Empleado() {
    }

    public Empleado( String idEmpleado, String NombreEmpleado, double SueldoEmpleado) {
        
        this.idEmpleado = idEmpleado;
        this.NombreEmpleado = NombreEmpleado;
        this.SueldoEmpleado = SueldoEmpleado;
    }



    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return NombreEmpleado;
    }

    public void setNombreEmpleado(String NombreEmpleado) {
        this.NombreEmpleado = NombreEmpleado;
    }

    public double getSueldoEmpleado() {
        return SueldoEmpleado;
    }

    public void setSueldoEmpleado(double SueldoEmpleado) {
        this.SueldoEmpleado = SueldoEmpleado;
    }

    /*public void ascenso (NodoN EmpleadoAscender, NodoN NuevaPosicion){
        
        NodoN temp = NuevaPosicion;
        
        NuevaPosicion.setDato(EmpleadoAscender.getDato());
        EmpleadoAscender.setDato(temp.getDato());
        
    }*/
}
