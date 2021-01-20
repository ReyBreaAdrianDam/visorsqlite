/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tablas;
import java.io.File;
import java.sql.*;

/**
 *
 * @author adrir
 */
public class EjecutarConsulta {
    public static boolean Ejecutar(String sentencia, File fichero) throws SQLException{       
        Connection cn = DriverManager.getConnection("jdbc:sqlite:" + fichero.getAbsolutePath(), "", "");
        Statement st = cn.createStatement();
        if(st.execute(sentencia)){
            ResultSetMetaData meta = st.getResultSet().getMetaData();
            int numColumnas = meta.getColumnCount();
            System.out.print(numColumnas);
            st.close();
            cn.close();
            return true;
        }
        else{
            st.close();
            cn.close();
            return false;
        }
        
    }
}
