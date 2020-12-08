package JDBC;

import java.sql.*;
import java.util.TimeZone;

public class JDBC2_Bernat__Aparicio {

    // Propiedades de la conexion a la BD
    private static Connection conn = null;
    private static Statement stmt;
    private static ResultSet rs = null;
    private static String url = "jdbc:mysql://localhost:3306/demo?serverTimezone="+ TimeZone.getDefault().getID();
    private static String user = "root";
    private static String password = "Bernat1234";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static void forNameDriver(){
        // Métodos
        try{
            Class.forName(driver);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void actDepPrecedure(int numDept, String nomDept){
        // Trabajo con base de datos
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("call actualizaDept("+ numDept+ ", '"+ nomDept+"');");
            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            if(conn != null){
                try{
                    conn.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        actDepPrecedure(10,"SPAIN");
    }
}
