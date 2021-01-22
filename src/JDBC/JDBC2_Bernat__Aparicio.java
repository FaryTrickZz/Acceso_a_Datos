package JDBC;

import java.sql.*;
import java.util.TimeZone;

public class JDBC2_Bernat__Aparicio {

    // Propiedades de la conexion a la BD
    private static Connection conn = null;
    private static Statement stmt;
    private static ResultSet rs = null;
    private static String url = "jdbc:mysql://localhost:3306/demo?serverTimezone=" + TimeZone.getDefault().getID();
    private static String user = "root";
    private static String password = "Bernat1234";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static void forNameDriver() {
        // Métodos
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Llama a un procedimiento que actualiza un departamento
    public static void actDepPrecedure(int numDept, String nomDept) {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("call actualizaDept(" + numDept + ", '" + nomDept + "');");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Llama a un procedimiento que devuelve un departamento y lo transforma en un objeto
    public static Object getObjectProcedure(int numDept) {
        String dname = "";
        String loc = "";
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            CallableStatement cst =conn.prepareCall("call consultaDepar("+numDept+", ?, ?);");
            cst.registerOutParameter(1, java.sql.Types.VARCHAR);
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.execute();
            dname = cst.getString(1);
            loc = cst.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                }
            }
        }
        departament dept = new departament(numDept, dname, loc);
        return dept;
    }

    //Llama a un procedimiento que actualiza el sueldo de un empleado
    public static void actSAL(int deptno, int aumento) {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("call actualizarSueldo(" + deptno + ", '" + aumento + "');");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Actualiza el sueldo de varios empleados
    public static void actSALRS(int deptno, int aumento) {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from emp where DEPTNO = "+deptno+";");
            while (rs.next()){
                double salarioNuevo = rs.getDouble("SAL") + aumento;
                rs.updateDouble("SAL",salarioNuevo);
                rs.updateRow();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Proporciona diferente informacion de la BD
    public static void infoBD() {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData infoBD = conn.getMetaData();
            String user = infoBD.getUserName();
            ResultSet x = infoBD.getTables(conn.getCatalog(), "demo", "dept", null);
            while (x.next()){
                System.out.println(x.getString("REMARKS\t"));
            }
            String driver = infoBD.getDriverName();
            String gestor = infoBD.getDatabaseProductName();
            System.out.println(user + driver + gestor);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void main(String[] args) {
        actDepPrecedure(10, "SPAIN");
        getObjectProcedure(10);
        //actSAL(10, 100);
        actSALRS(10, 100);
        infoBD();
    }
}
