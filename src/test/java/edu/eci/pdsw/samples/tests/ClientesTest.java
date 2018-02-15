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
        Item item=new Item(tipItm,11, "itemName", "itemDescription", java.sql.Date.valueOf("2015-10-01"), 16500,"formatoREnta","genero");
        ItemRentado itemRnt=new ItemRentado(item,java.sql.Date.valueOf("2017-01-25"), java.sql.Date.valueOf("2018-07-15"));
        Cliente p= new Cliente("karen",1020124,3214569 ,"Ak 200","karen.mora@mail.escuelaing.edu.co",true,itemRnt);
        
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        sa.registrarCliente(p);
        Assert.assertTrue((boolean) sa.existeCliente(p.getDocumento()));
    }
    
    
    
    
    
    
}
