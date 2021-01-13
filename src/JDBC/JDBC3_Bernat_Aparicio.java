package JDBC;

import java.sql.*;
import java.util.TimeZone;

public class JDBC3_Bernat_Aparicio {

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

    public static void mostrarTablas() {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SHOW FULL TABLES");

            while(rs.next()) {
                System.out.println(rs.getString(1)+" :"+rs.getString(2));

            }
            rs.close();
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

    public static void mostrarFuncionesyProcedimentos() {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("show procedure status");
            System.out.println("\n");
            while(rs.next()) {
                System.out.println("Procedimiento :"+rs.getString(2));
            }
            rs = stmt.executeQuery("show function status");
            while(rs.next()) {
                System.out.println("Funciones :"+rs.getString(2));
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

    public static void mostrarInformacionTabla(String squema, String table) {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            DatabaseMetaData infoBD = conn.getMetaData();
            rs = infoBD.getColumns(conn.getCatalog(), squema, table, null);
            System.out.println("\n");
            while (rs.next()){
                System.out.println("Nombre: "+rs.getString("TABLE_NAME") + " Tipo: " + rs.getString("TYPE_NAME") + " Tamaño: " + rs.getString("COLUMN_SIZE") + " Is nullable: " + rs.getString("IS_NULLABLE"));
            }
            rs.close();
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

    public static void mostrarPKdeBD(String squema, String table) {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            DatabaseMetaData infoBD = conn.getMetaData();
            rs = infoBD.getPrimaryKeys(conn.getCatalog(), squema, table);
            System.out.println("\n");
            while(rs.next()) {
                System.out.println("BD: "+squema+"\nNombre Tabla: "+rs.getString("TABLE_NAME")+"\nNombre Columna: "+rs.getString("TABLE_SCHEM"));
            }
            rs.close();
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

    public static void mostrarFKdetabla(String squema, String table) {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            DatabaseMetaData infoBD = conn.getMetaData();
            rs = infoBD.getExportedKeys(conn.getCatalog(), squema, table);
            System.out.println("\n");
            while(rs.next()) {
                System.out.println("\nNombre Tabla PK: "+rs.getString("PKTABLE_NAME")+" \nNombre PK: "+rs.getString("PK_NAME")+
                        "\nNombre Tabla FK: "+rs.getString("FKTABLE_NAME")+"\nNombre FK: "+rs.getString("FK_NAME")+"\n");
            }
            rs.close();
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

    public static void daw() {
        // Trabajo con base de datos
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("");
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


    public static void main(String[] args) {
        //mostrarTablas();
        //mostrarFuncionesyProcedimentos();
        //mostrarInformacionTabla( "demo", "dept");
        //mostrarPKdeBD("demo", "dept");
        mostrarFKdetabla("demo", "dept");
    }

}
