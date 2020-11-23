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

    public static void inserirDepartament(int deptno, String dname, String loc) {
        // Métodos
        try{
            Class.forName(driver);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        // Trabajo con base de datos
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into dept (deptno, dname, loc) values ("+deptno+", "+dname+", "+loc+");");
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

    public static void inserirDEpartamentObj(departament dept) {
        // Driver
        try{
            Class.forName(driver);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into dept (deptno, dname, loc) values ("+dept.getDeptno()+", "+dept.getDname()+", "+dept.getLoc()+");");
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

    public static departament[] departaments(){
        // Driver
        try{
            Class.forName(driver);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        departament departamentos[] = new departament[10];
        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from dept");
            stmt.close();

            int x = 0;
            while(rs.next()) {
                departament dept = new departament(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));
                departamentos[x] = dept;
                x++;
            }

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

    public static void departamentByDeptno(int deptno) {
        // Driver
        try{
            Class.forName(driver);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        // Query a BD
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeQuery("select * from dept where deptno = "+ deptno+";");

            while(rs.next()) {
                departament dept = new departament(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")) ;
                System.out.println(dept.toString());
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

    public static void main(String[] args){
        inserirDepartament(50, "MARKETING", "SPAIN");
        departament dept1 = new departament(60, "RRHH", "FRANCE");
        inserirDEpartamentObj(dept1);


    }
}
