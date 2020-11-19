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

public class ArbolABB implements Serializable {

    private NodoABB raiz;
    private int cantidadNodos;
    private String direccionNodo;
    private double sumaPrecios;

    public ArbolABB() {
    }

    public NodoABB getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoABB raiz) {
        this.raiz = raiz;
    }

    public String getDireccionNodo() {
        return direccionNodo;
    }

    public void setDireccionNodo(String direccionNodo) {
        this.direccionNodo = direccionNodo;
    }

    public int getCantidadNodos() {
        return cantidadNodos;
    }

    public void setCantidadNodos(int cantidadNodos) {
        this.cantidadNodos = cantidadNodos;
    }

    ///Adicionar en el árbol
    public void adicionarNodo(Empleado dato) throws CelularExcepcion {

     
        NodoABB nuevo = new NodoABB(dato);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            adicionarNodo(nuevo, raiz);
        }
        cantidadNodos++;
    }

    private void adicionarNodo(NodoABB nuevo, NodoABB pivote)
            throws CelularExcepcion {
        if (nuevo.getDato().getIdEmpleado().compareTo(pivote.getDato().getIdEmpleado()) == 0) {
            throw new CelularExcepcion("Ya existe un celular con el imei "
                    + nuevo.getDato().getIdEmpleado());
        } else if (nuevo.getDato().getIdEmpleado().compareTo(pivote.getDato().
                getIdEmpleado()) < 0) {
            //Va por la izq
            if (pivote.getIzquierda() == null) {
                direccionNodo = "izq";
                pivote.setIzquierda(nuevo);

            } else {

                adicionarNodo(nuevo, pivote.getIzquierda());

            }
        } else {
            //Va por la derecha
            if (pivote.getDerecha() == null) {
                direccionNodo = "der";
                pivote.setDerecha(nuevo);

            } else {
                adicionarNodo(nuevo, pivote.getDerecha());
            }
        }

    }

    public List<Empleado> recorrerInOrden() {
        List<Empleado> listaCelulares = new ArrayList<>();
        recorrerInOrden(raiz, listaCelulares);
        return listaCelulares;
    }

    private void recorrerInOrden(NodoABB reco, List<Empleado> listado) {
        if (reco != null) {
            recorrerInOrden(reco.getIzquierda(), listado);
            listado.add(reco.getDato());
            recorrerInOrden(reco.getDerecha(), listado);
        }
    }

    public List<Empleado> recorrerPreOrden() {
        List<Empleado> listaCelulares = new ArrayList<>();
        recorrerPreOrden(raiz, listaCelulares);
        return listaCelulares;
    }

    private void recorrerPreOrden(NodoABB reco, List<Empleado> listado) {
        if (reco != null) {
            listado.add(reco.getDato());
            recorrerPreOrden(reco.getIzquierda(), listado);
            recorrerPreOrden(reco.getDerecha(), listado);
        }
    }

    public List<Empleado> recorrerPostOrden() {
        List<Empleado> listaCelulares = new ArrayList<>();
        recorrerPostOrden(raiz, listaCelulares);
        return listaCelulares;
    }

    private void recorrerPostOrden(NodoABB reco, List<Empleado> listado) {
        if (reco != null) {

            recorrerPostOrden(reco.getIzquierda(), listado);
            recorrerPostOrden(reco.getDerecha(), listado);
            listado.add(reco.getDato());
        }
    }

    public boolean esVacio() {
        return raiz == null;
    }

    //Borrar el que sea
    public String borrar(String imei) {
        if (!this.buscar(imei)) {
            return "";
        }

        NodoABB z = borrar(this.raiz, imei);
        this.setRaiz(z);
        return imei;

    }

    private NodoABB borrar(NodoABB r, String imei) {
        if (r == null) {
            return null;//<--Dato no encontrado		
        }
        //Compara si el dato que va a borrar está a la der o izq del nodo de referencia
        int compara = ((Comparable) r.getDato().getIdEmpleado()).compareTo(imei);
        if (compara > 0) {
            r.setIzquierda(borrar(r.getIzquierda(), imei));
        } else if (compara < 0) {
            r.setDerecha(borrar(r.getDerecha(), imei));
        } else {
            //Nodo a borrar encontrado
            System.out.println("Encontro el dato:" + imei);
            if (r.getIzquierda() != null && r.getDerecha() != null) {
                /*
                 *	Buscar el menor de los derechos y lo intercambia por el dato
                 *	que desea borrar. La idea del algoritmo es que el dato a borrar 
                 *	se coloque en una hoja o en un nodo que no tenga una de sus ramas.
                 **/
                NodoABB cambiar = buscarMin(r.getDerecha());
                Empleado aux = cambiar.getDato();
                cambiar.setDato(r.getDato());
                r.setDato(aux);
                r.setDerecha(borrar(r.getDerecha(), imei));
                System.out.println("2 ramas");
            } else {
                r = (r.getIzquierda() != null) ? r.getIzquierda() : r.getDerecha();
                System.out.println("Entro cuando le faltan ramas ");
            }
        }
        return r;
    }

    //buscar
    public boolean buscar(String imei) {
        return (buscar(this.raiz, imei));

    }

    private boolean buscar(NodoABB r, String imei) {
        if (r == null) {
            return (false);
        }
        int compara = ((Comparable) r.getDato().getIdEmpleado()).compareTo(imei);
        if (compara > 0) {
            return (buscar(r.getIzquierda(), imei));
        } else if (compara < 0) {
            return (buscar(r.getDerecha(), imei));
        } else {
            return (true);
        }
    }

    //buscar min
    private NodoABB buscarMin(NodoABB r) {
        for (; r.getIzquierda() != null; r = r.getIzquierda());
        return (r);
    }

  

 
    
    //hojas
  

   
    protected boolean esHoja(NodoABB x) {
        return (x != null && x.getIzquierda()== null && x.getDerecha()== null);
    }
    
    //eliminar hojas
     public void podar() {
        podar(this.raiz);
    }

    private void podar(NodoABB x) {
        if (x == null) {
            return;
        }
        if (this.esHoja(x.getIzquierda())) {
            x.setIzquierda(null);
        }
        if (this.esHoja(x.getDerecha())) {
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
    }
    
    public String buscarPadre(String info) {
        if (info.equals("") || this.raiz == null) {
            return "";
        }
        NodoABB padreEncontrado = buscarPadre(this.raiz, info);
        if (padreEncontrado == null) {
            return "";
        }
        return (padreEncontrado.getDato().getIdEmpleado());
    }

    private NodoABB buscarPadre(NodoABB padreEncontrado, String info) {
        if (padreEncontrado == null) {
            return null;
        }
        if ((padreEncontrado.getIzquierda()!= null && padreEncontrado.getIzquierda().getDato().getIdEmpleado().equals(info)) || (padreEncontrado.getDerecha()!= null && padreEncontrado.getDerecha().getDato().getIdEmpleado().equals(info))) {
            return padreEncontrado;
        }
        NodoABB y = buscarPadre(padreEncontrado.getIzquierda(), info);
        if (y == null) {
            return (buscarPadre(padreEncontrado.getDerecha(), info));
        } else {
            return (y);
        }
    }
    
   
        //Borrar menor
    public String borrarMenor() {
        NodoABB reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getIzquierda()== null) {
                raiz = raiz.getDerecha();
            } else {
                NodoABB anterior = raiz;
                reco = raiz.getIzquierda();
                while (reco.getIzquierda()!= null) {
                    anterior = reco;
                    reco = reco.getIzquierda();
                }
                
                anterior.setIzquierda(reco.getDerecha());
            }
        }
        return ("Valor eliminado: " + reco.getDato().getIdEmpleado());
    }

    //borrar mayor
    public String borrarMayor() {
        NodoABB reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha()== null) {
                raiz = raiz.getIzquierda();
            } else {
                NodoABB anterior = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha()!= null) {
                    anterior = reco;
                    reco = reco.getDerecha();
                }
                
                anterior.setDerecha(reco.getIzquierda());
            }
        }
        return ("Valor eliminado: " + reco.getDato().getIdEmpleado());
    }
   
    NodoABB temporal;
   
    
    
    public String borrarCelular(String imei) {
//        if (!this.buscar(x)) {
//            return 0;
//        }

        NodoABB z = borrarCelular(this.raiz, imei);
        this.setRaiz(z);
        return imei;

    }
    
    // eliminar por posicion
    private NodoABB buscarNodoMenor(NodoABB pivote) {
        for (; pivote.getIzquierda() != null; pivote = pivote.getIzquierda());
        return (pivote);
    }

    public Empleado buscarCelularMenor() {
        return buscarCelularMenor(raiz);
    }

    private Empleado buscarCelularMenor(NodoABB pivote) {
        for (; pivote.getIzquierda() != null; pivote = pivote.getIzquierda());
        return (pivote.getDato());
    }

    private NodoABB borrarCelular(NodoABB pivote, String imei) {
        if (pivote == null) {
            return null;//<--Dato no encontrado		
        }
        int compara = pivote.getDato().getIdEmpleado().compareTo(imei);
        if (compara > 0) {
            pivote.setIzquierda(borrarCelular(pivote.getIzquierda(), imei));
        } else if (compara < 0) {
            pivote.setDerecha(borrarCelular(pivote.getDerecha(), imei));
        } else {
            if (pivote.getIzquierda() != null && pivote.getDerecha() != null) {
                /*
                 *	Buscar el menor de los derechos y lo intercambia por el dato
                 *	que desea borrar. La idea del algoritmo es que el dato a borrar 
                 *	se coloque en una hoja o en un nodo que no tenga una de sus ramas.
                 **/
                NodoABB cambiar = buscarNodoMenor(pivote.getDerecha());
                Empleado aux = cambiar.getDato();
                cambiar.setDato(pivote.getDato());
                pivote.setDato(aux);
                pivote.setDerecha(borrarCelular(pivote.getDerecha(),
                        imei));

            } else {
                pivote = (pivote.getIzquierda() != null) ? pivote.getIzquierda()
                        : pivote.getDerecha();

            }
        }
        return pivote;
    }
    
    public NodoABB borrarDato(String imei) {
        NodoABB nodo = borrarCelular(raiz, imei);
        this.setRaiz(nodo);
        return nodo;
    }
    
   
}
