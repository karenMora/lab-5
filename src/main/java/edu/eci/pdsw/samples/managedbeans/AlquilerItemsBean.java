/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Cliente cliente;
    private List<ItemRentado> rentados;
    private List<Integer> multas;
    private List<Item> items;
    private Item item;
    private int numeroItem;

    ServiciosAlquiler sp = ServiciosAlquiler.getInstance();

    public AlquilerItemsBean() throws ExcepcionServiciosAlquiler {
        clientes = sp.consultarClientes();
        items = sp.consultarItemsDisponibles();
        cliente=clientes.get(1);
        
    }

    public List<Cliente> getClientes(){
        return clientes;
    }
    public void setClientes(List<Cliente> x){
        clientes = x;
    }
    public void setCliente(Cliente c){
        cliente = c;
    }
    public Cliente getCliente(){
        return cliente;
    }
    
    public List<ItemRentado> getRentados(){
        rentados();
        return rentados;
    }
    public void setRentados(List<ItemRentado> r) {
        rentados = r;
    }
    
    public List<Integer> getMultas(){
        return multas;
    }
    public void setMultas(List<Integer> m) {
        multas = m;
    }
    
    public int getNumeroItem(){
        return numeroItem;
    }
    public void setNumeroItem(String i)  {
        
        numeroItem = Integer.parseInt(i);
        try {
            item = sp.consultarItem(numeroItem);
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    public void registrar(){
        Date localDate = Date.valueOf(LocalDate.MAX);
        try {
            sp.registrarAlquilerCliente(localDate, cliente.getDocumento(), item, 3);
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private void rentados(){
        try {
            rentados = sp.consultarItemsCliente(cliente.getDocumento());
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void calcularMulta(){
        
    }
    
}
