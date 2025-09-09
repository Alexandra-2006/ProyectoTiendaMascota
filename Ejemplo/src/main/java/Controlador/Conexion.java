package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//Esta clase nos permitirá abrir o habilitar la conexión a la base de datos con el localhost, el puerto que es el 3306, la ruta y la contraseña
//

public class Conexion {
	
	public static final String URL = "jdbc:mysql://localhost:3306/bd_tiendamascotas";
    public static final String USER = "root";
    public static final String PASSWORD = "2556229";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return con;
    }
}


