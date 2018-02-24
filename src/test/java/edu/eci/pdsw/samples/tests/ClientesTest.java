/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import java.sql.Date;
import java.util.ArrayList;
import javax.validation.constraints.AssertTrue;
import org.junit.Assert;
import org.junit.Test;
/**
 *
 * 
 */
public class ClientesTest {

    public ClientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
  
    @Test
    public void additems1() throws ExcepcionServiciosAlquiler{
    	
    }
    
    /**
     * clases de equivalencia para el metodo de registro de nuevos clientes
     */
    @Test
    public void DeberiaRegistrarNuevosClientes() throws ExcepcionServiciosAlquiler{
        TipoItem tipItm=new TipoItem(10,"ESte es un tipo ITEM");
        TipoItem tipItm2=new TipoItem(10,"papas");
        TipoItem tipItm3=new TipoItem(10,"gaseosa");
        Item item=new Item(tipItm,11, "itemName", "itemDescription", java.sql.Date.valueOf("2015-10-01"), 16500,"formatoREnta","genero");
        Item item2=new Item(tipItm2,11, "pedro", "hola", java.sql.Date.valueOf("2015-12-10"), 16500,"formatoREnta","femenino");
        Item item3=new Item(tipItm3,11, "carol", "buenas tardes", java.sql.Date.valueOf("2015-09-13"), 16500,"formatoREnta","masculino");
        ItemRentado itemRnt=new ItemRentado(item,java.sql.Date.valueOf("2017-01-25"), java.sql.Date.valueOf("2018-07-15"));
        ItemRentado itemRnt2=new ItemRentado(item2,java.sql.Date.valueOf("2016-09-12"), java.sql.Date.valueOf("2017-09-20"));
        ItemRentado itemRnt3=new ItemRentado(item3,java.sql.Date.valueOf("2015-02-02"), java.sql.Date.valueOf("2016-12-01"));
        ArrayList<ItemRentado> arreglo = new ArrayList<ItemRentado>();
        arreglo.add(itemRnt);
        arreglo.add(itemRnt2);
        arreglo.add(itemRnt3);
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        sa.registrarCliente(new Cliente("karen",7564656,"3214569" ,"Ak 200","karen.mora@mail.escuelaing.edu.co",true,arreglo));
        assertEquals(7564656,sa.consultarCliente(7564656).getDocumento());
    }
    
    /**
    * registro de clientes nuevos sin saber si estan vetados
    */
     @Test
    public void nuevoCLienteSinRestriccion()throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        sa.registrarCliente(new Cliente("Pedro Perez",134567,"123456","calle 167","pedro.perez@gmail.com"));
        assertEquals(134567,sa.consultarCliente(134567).getDocumento());
    }
    
    
    @Test
    public void obtenerElNombre() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        sa.registrarCliente(new Cliente("carlos",1020124,"2456807" ,"pepe sierra","carlosPeres@gmail.com"));
        assertEquals("carlos",sa.consultarCliente(1020124).getNombre());
        
    }
    
    @Test
    public void obtenerElDocumento() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        sa.registrarCliente(new Cliente("pedro",9874465,"2456807" ,"pepe sierra","pedroTorres@gmail.com"));
        assertEquals("pedroTorres@gmail.com",sa.consultarCliente(9874465).getEmail());
    }
}
