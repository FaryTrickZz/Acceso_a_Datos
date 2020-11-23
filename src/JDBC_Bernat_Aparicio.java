import java.sql.*;
import java.util.TimeZone;

public class JDBC_Bernat_Aparicio {

    public static void connection(String statement) {
        // Propiedades
        Connection conn = null;
        Statement stmt;
        ResultSet rs;
        String url = "jdbc:mysql://localhost:3306/demo?serverTimezone="+ TimeZone.getDefault().getID();
        String user = "root";
        String password = "Bernat1234";

        // Métodos
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        // Trabajo con base de datos
        try{
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate(statement);



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

    public static String inserirDepartament(int deptno, String dname, String loc) {
        String statement = "insert into dept (deptno, dname, loc) values ("+deptno+", "+dname+", "+loc+");";
        return statement;
    }

    public static void main(String[] args){
        connection(inserirDepartament(50, "MARKETING", "SPAIN"));

    }
}
