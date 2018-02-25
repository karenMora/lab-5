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
    private Cliente SelectedCliente;
    private List<ItemRentado> rentados;
    private List<Integer> multas;
    private List<Item> items;
    private Item item;
    private int numeroItem;
    
    String nombre="";
    long documento=0;
    String telefono="";
    String direccion="";
    String email="";

    ServiciosAlquiler sp = ServiciosAlquiler.getInstance();

    public AlquilerItemsBean() throws ExcepcionServiciosAlquiler {
        numeroItem = 0;
        clientes = sp.consultarClientes();
        items = sp.consultarItemsDisponibles();
    }

    public List<Cliente> getClientes(){
        return clientes;
    }
    public void setClientes(List<Cliente> x){
        clientes = x;
    }
   
    
    public List<ItemRentado> getRentados(){
        rentados();
        return rentados;
    }
    public void setRentados(List<ItemRentado> r) {
        rentados = r;
    }
    
    public List<Integer> getMultas(){
        calcularMulta();
        return multas;
    }
    public void setMultas(List<Integer> m) {
        multas = m;
    }
    
    public int getNumeroItem(){
        return numeroItem;
    }
    public void setNumeroItem(int i) throws ExcepcionServiciosAlquiler  {
        
        numeroItem = i;
        item();    
    }
    public void registrar(){
        Date localDate = Date.valueOf(LocalDate.MAX);
        try {
            sp.registrarAlquilerCliente(localDate, SelectedCliente.getDocumento(), item, 3);
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Cliente getSelectedCliente(){
        return SelectedCliente;
    }
    public void setSelectedCliente(Cliente c){
        SelectedCliente = c;
    }
    
    private void rentados(){
        try {
            rentados = sp.consultarItemsCliente(SelectedCliente.getDocumento());
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void item(){
        try {
            item = sp.consultarItem(numeroItem);
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void calcularMulta(){
        
    }
    
    public void addCliente(){
        clientes.add(0,(new Cliente(nombre,documento,telefono,direccion,email)));
    }
    
    public String getNombre() throws ExcepcionServiciosAlquiler{
        return nombre;
    }
    public void setNombre(String n){
        nombre=n;
    }
    
    public long getDocumento()throws ExcepcionServiciosAlquiler{
        return documento;
    }
    public void setDocumento(long d){
        documento=d;
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    public void setTelefono(String t){
      telefono=t;
    }
    
    public String getDireccion(){
        return direccion;
    }
    
    public void setDireccion(String d){
        direccion=d;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String e){
        email=e;
    }
    
    public boolean estVEtado()throws ExcepcionServiciosAlquiler{
        return sp.consultarCliente(documento).isVetado();
    }
    
}
