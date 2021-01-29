package com.mycompany.tablas;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author adrir "jdbc:sqlite:Z:/db/sqlite/ejemplo.db"
 */
public class VerDatos {

    /* Conexion */
    Connection cn;
    Statement st;
    ArrayList<String[]> datos;
    ArrayList<String> tablas;
    ArrayList<String> columnas;
    File fichero;
    String usuario;
    String contra;
    String tabla;

    VerDatos(File fichero, String usuario, String contra) {
        this.fichero = fichero;
        this.usuario = usuario;
        this.contra = contra;
        tabla = null;
        datos = new ArrayList<>();
        tablas = new ArrayList<>();
        columnas = new ArrayList<>();
        generarDatos();
    }

    VerDatos(File fichero, String usuario, String contra, String tabla) {
        this.fichero = fichero;
        this.usuario = usuario;
        this.contra = contra;
        this.tabla = tabla;
        datos = new ArrayList<>();
        tablas = new ArrayList<>();
        columnas = new ArrayList<>();
        generarDatos();
    }

    public VerDatos(File fichero, String usuario, String contra, String tabla, String sentencia) {
        this.fichero = fichero;
        this.usuario = usuario;
        this.contra = contra;
        this.tabla = tabla;
        datos = new ArrayList<>();
        tablas = new ArrayList<>();
        columnas = new ArrayList<>();
        generarDatos(sentencia);
    }

    private void conectar() throws SQLException {
        try{
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        cn = DriverManager.getConnection("jdbc:sqlite:" + fichero.getPath(), "", "");
        st = cn.createStatement();
    }

    private void cerrarConexion() throws SQLException {
        st.close();
        cn.close();
    }

    /**
     * Genera datos al seleccionar una tabla graficamente
     */
    private void generarDatos() {
        try {
            conectar();;
            recuperarTablas();
            recuperarDatos("SELECT * from " + tabla);
            cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Genera datos a partir de una sentencia
     *
     * @param sentencia
     */
    private void generarDatos(String sentencia) {
        try {
            conectar();
            recuperarTablas();
            recuperarDatos(sentencia);
            cerrarConexion();
        } catch (SQLException ex) {

        }
    }

    /**
     * Recupera los datos a mostrar
     */
    private void recuperarDatos(String sentencia) throws SQLException {
        ResultSet rs = st.executeQuery(sentencia);
        ResultSetMetaData meta = rs.getMetaData();
        int numColumnas = meta.getColumnCount();
        tabla = meta.getTableName(1);
        String fila = "";
        
        for (int i = 1; i - 1 < numColumnas; i++) {
            columnas.add(meta.getColumnName(i));
        }
        if(rs.isBeforeFirst()){
            while (rs.next()) {
                for (int i = 1; i - 1 < numColumnas; i++) {
                    fila += rs.getString(i) + "<>";
                }
                try {
                    datos.add(addFila(fila.substring(0, fila.length() - 2)));
                    fila = "";
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        }
        else{
            columnas.set(0, "Sin datos");
            datos.add(addFila(""));
        }
        rs.close();
    }

    private void recuperarTablas() throws SQLException {
        String sel1 = "SELECT name FROM sqlite_master WHERE type = 'table'";
        ResultSet rs = st.executeQuery(sel1);
        while (rs.next()) {
            tablas.add(rs.getString(1));
        }
        if (tabla == null) {
            tabla = tablas.get(0);
        }
        rs.close();
    }

    private String[] addFila(String linea) {
        return linea.split("<>");
    }

    public String[] getFilaDatos(int pos) {
        return datos.get(pos);
    }

    public ArrayList<String[]> getDatos() {
        return datos;
    }

    public int getNumColumnas() {
        return datos.size();
    }

    public void setDatos(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    public ArrayList<String> getTablas() {
        return tablas;
    }

    public ArrayList<String> getColumnas() {
        return columnas;
    }

    public File getFichero() {
        return fichero;
    }

    public String getTablaActual() {
        return tabla;
    }
}
