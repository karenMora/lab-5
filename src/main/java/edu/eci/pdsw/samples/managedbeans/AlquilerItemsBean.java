/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped
public class AlquilerItemsBean implements Serializable {
    private List<Cliente> clientes;
    

    ServiciosAlquiler sp = ServiciosAlquiler.getInstance();

    public AlquilerItemsBean() throws ExcepcionServiciosAlquiler {
        clientes = sp.consultarClientes();
    }

    public List<Cliente> getClientes(){
        return clientes;
    }
    public void setClientes(List<Cliente> x){
        clientes = x;
        
        
    }
}
