/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tablas;

import java.io.*;
import java.util.ArrayList;
import javax.swing.table.*;
/**
 *
 * @author adrir
 */
public class Modelo extends AbstractTableModel{
   VerDatos dat;
    private File file;
   public Modelo(){
       dat = new VerDatos(new File("chinookk.db"), "","");
   }
   
   public Modelo(File file){
       dat = new VerDatos(file.getAbsoluteFile(), "","");
   }
   
   /**
    * Cambio de tabla
    * @param file
    * @param tabla 
    */
   public Modelo(File file, String tabla){
       dat = new VerDatos(file.getAbsoluteFile(), "","", tabla);
   }
   /**
    * Modelo para select
     * @param file
     * @param tabla
     * @param sentencia
    * @return 
    */
   public Modelo (File file, String tabla, String sentencia){
        dat = new VerDatos(file.getAbsoluteFile(),"","", tabla, sentencia);
   }
    @Override
    public int getRowCount() {
        return dat.getNumColumnas(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return dat.getDatos().get(0).length; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        return dat.getDatos().get(rowIndex)[columnIndex];
    }
    public String[] fila(String linea){
        return linea.split("-");
    }
    public ArrayList<String> getTablas(){
        return dat.getTablas();
    }
    
    public ArrayList<String> getColumnas(){
        return dat.getColumnas();
    }
    public File getFichero(){
        return dat.getFichero();
    }
    public String getTablaActual(){
        return dat.getTablaActual();
    }
}
    

