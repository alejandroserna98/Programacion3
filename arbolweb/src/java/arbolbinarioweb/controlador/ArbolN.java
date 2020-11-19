/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinarioweb.controlador.util.CelularExcepcion;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Juan Jose Ospina B
 */
@Named
public class ArbolN implements Serializable {

    public NodoN raiz;
    public int cantidadNodos;
    private int sumaPrecios;
    

    public NodoN getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoN raiz) {
        this.raiz = raiz;
    }

    public int getCantidadNodos() {
        return cantidadNodos;
    }

    public void setCantidadNodos(int cantidadNodos) {
        this.cantidadNodos = cantidadNodos;
    }

    public int getSumaPrecios() {
        return sumaPrecios;
    }

    public void setSumaPrecios(int sumaPrecios) {
        this.sumaPrecios = sumaPrecios;
    }

    public void adicionarNodo(Empleado dato, String padre) throws CelularExcepcion {
        if (raiz == null) {
            raiz = new NodoN(dato);

        } else {
            adicionarNodo(dato, padre, raiz);

        }
        cantidadNodos++;
        this.sumaPrecios = 0;
        this.sumarPrecios();

    }

    public boolean adicionarNodo(Empleado dato, String padre, NodoN pivote) throws CelularExcepcion {
        // boolean adicionado=false;
        if (pivote.getDato().getIdEmpleado().
                compareTo(padre) == 0) {
            //Es el padre donde debo adicionar
            pivote.getHijos().add(new NodoN(dato));
            //adicionado=true;
            return true;
        } else {
            for (NodoN hijo : pivote.getHijos()) {
                if (adicionarNodo(dato, padre, hijo)) {
                    break;
                }
            }
        }
        return false;

    }

    public List<Empleado> recorrerN() {
        List<Empleado> listaCelulares = new ArrayList<>();
        recorrerN(raiz, listaCelulares);
        return listaCelulares;
    }

    private void recorrerN(NodoN reco, List<Empleado> listado) {

        if (reco != null) {
            listado.add(reco.getDato());
            for (NodoN n : reco.getHijos()) {
                recorrerN(n, listado);
            }
        }

    }

    public double sumarPrecios() {

        sumarInOrdenRecursivo(raiz);

        return sumaPrecios;

    }
//

    public void sumarInOrdenRecursivo(NodoN reco) {
        if (reco != null) {
            sumaPrecios += (int) reco.getDato().getSueldoEmpleado();
            for (NodoN n : reco.getHijos()) {
                sumaPrecios += n.getDato().getSueldoEmpleado();
            }

        }
    }
    
    
}
