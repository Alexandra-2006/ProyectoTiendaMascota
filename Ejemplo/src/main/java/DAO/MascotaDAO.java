package DAO;

import java.sql.*;
import java.util.*;
import Controlador.Conexion;
import Controlador.Notificaciones;
import Modelo.Mascota;

public class MascotaDAO {
	Connection con; //Conexió base de datos
	PreparedStatement ps; //Ejecuta sentencias SQL con parámetros
	ResultSet rs;//Guardar resultados de consulta

	// INSERT
	public void insert(Mascota m) {
		
		//Se abre la conexión a la base de datos  haciendo la consulta con el método asignado y sus variables para así registrar una nueva mascota

		String sql = "INSERT INTO mascota (Nombre, Especie, Género, Raza, IDCedula) VALUES (?, ?, ?, ?, ?)";
		try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, m.getNombre());
			ps.setString(2, m.getEspecie());
			ps.setString(3, m.getGénero());
			ps.setString(4, m.getRaza());
			ps.setInt(5, m.getIdcedula());
			ps.executeUpdate(); //Devuelve las filas
             
		
			System.out.println("Filas insertadas: ");

		} catch (Exception e) {
			System.out.println("Mascota insertada" + e.getMessage());
		}
	}

// READ (listar todos)
	public List<Mascota> listarMascota() {
	    List<Mascota> lista = new ArrayList<>();
	    String sql = "SELECT * FROM mascota";
	    try (Connection con = Conexion.getConnection();// Abre la conexion 
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        while (rs.next()) { //Avanza por cada fila
	            Mascota m = new Mascota();
	            m.setIDMascota(rs.getInt("IDMascota"));
	            m.setNombre(rs.getString("Nombre"));
	            m.setEspecie(rs.getString("Especie"));
	            m.setGénero(rs.getString("Género"));
	            m.setRaza(rs.getString("Raza"));
	            m.setIdcedula(rs.getInt("IDCedula"));
	            System.out.println(m.getNombre() + " - " + m.getGénero());
	            lista.add(m); //Guardar en lista
	        }

	    } catch (SQLException e) {
	    	
	    	System.out.println("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return lista; //Retorno la lista
	}

	

	// Con este método actualizamos cualquier registro de la base de datos con su Id, se abre la conexión primeramente
	//y cin el id actualizamos
	public void update(Mascota m) {

		String sql = "UPDATE mascota SET Nombre=?, Especie=?, Género=?, Raza=?, IDCedula=? WHERE IDMascota=?";

		  try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			  ps.setString(1, m.getNombre());
		        ps.setString(2, m.getEspecie());
		        ps.setString(3, m.getGénero());
		        ps.setString(4, m.getRaza());
		        ps.setInt(5, m.getIdcedula());
		        ps.setInt(6, m.getIDMascota());	        
		    	

		        int rows = ps.executeUpdate();// Cuantas filas fueron afectadas
		        System.out.println("Filas actualizadas: " + rows);

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	// DELETE
	public void delete(int IDMascota) {
		String sql = "DELETE FROM mascota WHERE IDMascota=?";
		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, IDMascota);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
		
	
	

}
