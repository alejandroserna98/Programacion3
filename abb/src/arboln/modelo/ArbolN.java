package arboln.modelo;

import arbolbinario.modelo.Nodo;
import arboln.modelo.NodoN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carloaiza
 */
public class ArbolN implements Serializable {

    private NodoN raiz;
    private int cantidadNodos;
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

    public void adicionarNodo(Empleado dato, String padre) {
        if (raiz == null) {
            raiz = new NodoN(dato);

        } else {
            adicionarNodo(dato, padre, raiz);

        }
        cantidadNodos++;

    }

    public boolean adicionarNodo(Empleado dato, String padre, NodoN pivote) {
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

    public void adicionarNodoxCodigo(Empleado dato, Empleado padre) {
        if (raiz == null) {
            raiz = new NodoN(dato);

        } else {
            adicionarNodoxCodigo(dato, padre, raiz);

        }
        cantidadNodos++;

    }

    public boolean adicionarNodoxCodigo(Empleado dato, Empleado padre, NodoN pivote) {
        // boolean adicionado=false;
        if (padre.getIdEmpleado() == null ? pivote.getDato().getIdEmpleado() == null : padre.getIdEmpleado().equals(pivote.getDato().getIdEmpleado())) {
            //Es el padre donde debo adicionar
            pivote.getHijos().add(new NodoN(dato));
            //adicionado=true;
            return true;
        } else {
            for (NodoN hijo : pivote.getHijos()) {
                if (adicionarNodoxCodigo(dato, padre, hijo)) {
                    break;
                }

            }
        }
        return false;
    }

//    public List<Empleado> recorrerN() {
//        List<Empleado> listaCelulares = new ArrayList<>();
//        recorrerN(raiz, listaCelulares);
//        return listaCelulares;
//    }
//    
//    private void recorrerN(NodoN reco, List<Empleado> listado) {
//        
//        if (reco != null) {
//            listado.add(reco.getDato());
//            for (NodoN n : reco.getHijos())
//            recorrerN(n, listado);
//         }
//    }
//   public int contarNodos() //reco
//    {
//        return this.contarNodos(raiz);
//    }
//
//    public int contarNodos(Nodo reco) {
//        //Hacen el dllo del contar
//        if (reco == null) {
//            return 0;
//        }
//        if (reco.isHoja()) {
//            return 1;
//        } else {
//            return 1 + contarNodos(reco.getIzquierda()) + contarNodos(reco.getDerecha());
//        }
//    }
    public int sumarprecios(Nodo ref) {
        if (ref != null) {
            return ref.getDato() + sumarprecios(ref.getIzquierda())
                    + sumarprecios(ref.getDerecha());
        } else {
            return 0;
        }
    }

//        public int sumarNodos(NodoN ref) {
//        if (ref != null) {
//            return ref.getDato() + sumarNodos(ref.getIzquierda())
//                    + sumarNodos(ref.getDerecha());
//        } else {
//            return 0;
//        }
//    }
//        
//  public int sumarArbol() {
//        return sumarNodos(raiz);
//    }
//        
}
