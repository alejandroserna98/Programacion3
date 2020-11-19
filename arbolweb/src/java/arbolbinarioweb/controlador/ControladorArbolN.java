/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinario.modelo.ArbolBinario;
import arbolbinario.modelo.Nodo;
import arbolbinarioweb.controlador.util.CelularExcepcion;

import arbolbinarioweb.controlador.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author Juan Jose Ospina B
 */
@SessionScoped
@Named(value = "arbolnarioControlador")
public class ControladorArbolN implements Serializable {

    private ArbolN arbol = new ArbolN();
    private Empleado empleado = new Empleado();
    private List<Empleado> lista;
    private boolean verRegistrar;
    private DefaultDiagramModel model;
    private String padre;
    private String textoHeader;
    private DefaultDiagramModel modelArbol2;
    private ArbolBinario arbolTerminados = new ArbolBinario();

    public String getTextoHeader() {
        return textoHeader;
    }

    public DefaultDiagramModel getModelArbol2() {
        return modelArbol2;
    }

    public void setModelArbol2(DefaultDiagramModel modelArbol2) {
        this.modelArbol2 = modelArbol2;
    }

    public void setTextoHeader(String textoHeader) {
        this.textoHeader = textoHeader;
    }

    public void pintarArbolTerminados() {

        modelArbol2 = new DefaultDiagramModel();
        modelArbol2.setMaxConnections(-1);
        modelArbol2.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:10}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        modelArbol2.setDefaultConnector(connector);
        pintarArbolTerminados(arbolTerminados.getRaiz(), modelArbol2, null, 30, 0);

    }

    private void pintarArbolTerminados(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element(reco.getDato());

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);

            pintarArbolTerminados(reco.getIzquierda(), model, elementHijo, x - 20, y + 5);
            pintarArbolTerminados(reco.getDerecha(), model, elementHijo, x + 20, y + 5);
        }
    }

    @PostConstruct
    private void inicializar() {
        arbol = new ArbolN();
        textoHeader = "Arbol n ario";
        pintarArbol();
    }

    public ControladorArbolN() {
    }

    public List<Empleado> getListaEmpleado() {
        return lista;
    }

    public void setListaCelulares(List<Empleado> listaEmpleados) {
        this.lista = listaEmpleados;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public ArbolN getArbol() {
        return arbol;
    }

    public void setArbol(ArbolN arbol) {
        this.arbol = arbol;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setCelular(Empleado celular) {
        this.empleado = celular;
    }

    public boolean isVerRegistrar() {
        return verRegistrar;
    }

    public void setVerRegistrar(boolean verRegistrar) {
        this.verRegistrar = verRegistrar;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    public void pintarArbol() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:2}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        pintarArbol(arbol.getRaiz(), model, null, 42, 0);

    }

    private void pintarArbol(NodoN reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element(reco.getDato().getIdEmpleado() + " " + reco.getDato().getNombreEmpleado());

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);
            for (NodoN hijo : reco.getHijos()) {
                pintarArbol(hijo, model, elementHijo, x - 10, y + 5);
                x += 10;
            }
        }
    }

//     public void pintarArbol()
//    {
//        
//        model = new DefaultDiagramModel();
//        model.setMaxConnections(-1);
//        model.setConnectionsDetachable(false);
//        StraightConnector connector = new StraightConnector();
//        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:2}");
//        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
//        model.setDefaultConnector(connector);
//        pintarArbol(arbol.getRaiz(), model, null, 42, 0);
//        
//    }
//    private void pintarArbol(NodoN reco,DefaultDiagramModel model, Element padre, int x, int y) {
//               
//        if (reco != null) {
//            Element elementHijo = new Element(reco.getDato().getIdEmpleado() + " " + reco.getDato().getIdEmpleado());
//            
//            elementHijo.setX(String.valueOf(x)+"em");
//            elementHijo.setY(String.valueOf(y)+"em");
//            elementHijo.setId(reco.getDato().getIdEmpleado());
//            
////            if(padre!=null)
////            {
//                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//                DotEndPoint conectorPadre=new DotEndPoint(EndPointAnchor.BOTTOM);
//                padre.addEndPoint(conectorPadre);                
//                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));        
//                
////            }    
//            
//            model.addElement(elementHijo);
//            for(NodoN hijo: reco.getHijos())
//            {
//                pintarArbol(hijo,model, elementHijo,x-10,y+5);
//                x += 10;                
//            }
//        }
//    }
//     private void pintarArbol(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {
//
//        if (reco != null) {
//            Element elementHijo = new Element(reco.getDato().getSuma());
//            elementHijo.setId(String.valueOf(reco.getDato().getNumero()));
//
//            elementHijo.setX(String.valueOf(x) + "em");
//            elementHijo.setY(String.valueOf(y) + "em");
//
//            if (padre != null) {
//                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
//                padre.addEndPoint(conectorPadre);
//                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));
//
//            }
//
//            model.addElement(elementHijo);
//
//            pintarArbol(reco.getIzquierda(), model, elementHijo, x - 10, y + 5);
//            pintarArbol(reco.getDerecha(), model, elementHijo, x + 10, y + 5);
//        }
//    }
    public void guardarEmpleado() {
        try {
            arbol.adicionarNodo(empleado, padre);
            empleado = new Empleado();
            verRegistrar = false;
            pintarArbol();
            //arbol.sumarPrecios();
        } catch (CelularExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");

        infanteSeleccionado = id.replaceAll("frmInfantesAbb:diagrama-", "");

    }

    private String infanteSeleccionado = "";

//    public void eliminarInfanteMenuContext() {
//        
//        
//        if (arbol.borrarInfante(infanteSeleccionado)!=null) {
//            pintarArbol();
//            JsfUtil.addSuccessMessage("Eliminado con éxito");
//        } else {
//            JsfUtil.addErrorMessage("No pudo eliminarse, comuníquese con el administrador ");
//        }
//    }
    public void habilitarVerRegistrar() {
        verRegistrar = true;
    }

    public void deshabilitarVerRegistrar() {
        verRegistrar = false;
    }

}
