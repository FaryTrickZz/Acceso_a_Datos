package JDBC;

import java.sql.*;
import java.util.TimeZone;

public class JDBC_Bernat_Aparicio {

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

    //Ejecuta la query para insertar un nuevo departamento con sus columnas recibidas por parametro
    public static void inserirDepartament(int deptno, String dname, String loc) {
        // Trabajo con base de datos
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into dept values ("+deptno+", '"+dname+"', '"+loc+"');");
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

    //Ejecuta la query para insertar un nuevo departamento a partir de un objeto
    public static void inserirDEpartamentObj(departament dept) {
        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into dept (deptno, dname, loc) values ("+dept.getDeptno()+", '"+dept.getDname()+"', '"+dept.getLoc()+"');");
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

    //Lee los departamentos de la BD y para cada uno de ellos crea un objeto y lo mete en un array
    public static departament[] departaments(){
        departament departamentos[] = new departament[10];
        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from dept");


            int x = 0;
            while(rs.next()) {
                departament dept = new departament(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));
                departamentos[x] = dept;
                System.out.println(rs.getInt("deptno") +" - "+ rs.getString("dname") +" - "+ rs.getString("loc"));
                x++;
            }
            stmt.close();
            rs.close();

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
        return departamentos;
    }

    //Busca en la BD un departamento dado el numero del departamento
    public static void departamentByDeptno(int deptno) {
        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from dept where deptno = "+ deptno+";");

            while(rs.next()) {
                departament dept = new departament(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")) ;
                System.out.println(rs.getInt("deptno") +" - "+ rs.getString("dname") +" - "+ rs.getString("loc"));
            }

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

    //Hace un update en la BD de un departamento recibiendo el departamento por parametro
    public static void updateDepartament(departament dept ) {
        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("update dept set dname = '"+dept.getDname()+"', loc = '"+ dept.getLoc() +"' where deptno = "+dept.getDeptno()+";");



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

    //Elimina un departamento de la BD dado su numero
    public static void deleteDepartamentByDeptno(int deptno) {
        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("delete from dept where deptno = "+ deptno+";");
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

    //Elimina un departamento de la BD dado su numero y printea el numero de filas que se han alterado
    public static void deleteDepartamentFilesAfectades(int deptno) {
        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from dept");
            int i = 0;
            while(rs.next()) {
                i++;
            }
            rs.close();
            stmt.executeUpdate("delete from dept where deptno = "+ deptno+";");
            rs = stmt.executeQuery("select * from dept");
            int x = 0;
            while(rs.next()) {
                x++;
            }
            int z = i-x;
            rs.close();
            System.out.println("Se han afectado: "+z+" filas");
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

    //Modifica el nombre de un departamento dado su numero
    public static void modificardnameByDeptnoDname(int deptno, String dname) {
        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("update dept set dname = '"+dname+"' where deptno = "+deptno+";");
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

    public static void main(String[] args){
        forNameDriver();
        //inserirDepartament(80, "VENTAS", "SPAIN");
        //inserirDEpartamentObj(new JDBC.departament(60, "RRHH", "FRANCE"));
        //departaments();
        //departamentByDeptno(50);
        //updateDepartament(new JDBC.departament(60, "COMPRAS", "PORTUGAL"));
        //deleteDepartamentByDeptno(50);
        //deleteDepartamentFilesAfectades(60);
        //modificardnameByDeptnoDname(60, "PIPAS");
    }
}
