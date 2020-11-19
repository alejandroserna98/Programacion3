/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

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

    Empleado(String cadena) {
    }

    public Empleado(String idEmpleado, String NombreEmpleado, double SueldoEmpleado) {

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

}
