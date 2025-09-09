package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class ConsultarMascota
 */
@WebServlet("/ConsultarMascota")
public class ConsultarMascota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarMascota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		  String idMascota = request.getParameter("IDMascota");

          try (Connection conn = Conexion.getConnection()) {
                  String sql = "SELECT * FROM mascota WHERE IDMascota=?";
                  PreparedStatement ps = conn.prepareStatement(sql);
                  ps.setString(1, idMascota);
                  ResultSet rs = ps.executeQuery();

                  if (rs.next()) {
                          request.setAttribute("nombre", rs.getString("Nombre"));
                          request.setAttribute("especie", rs.getString("Especie"));
                          request.setAttribute("genero", rs.getString("Género"));
                          request.setAttribute("raza", rs.getString("Raza"));
                          request.setAttribute("idcedula", rs.getString("IDCedula"));
                  } else {
                          request.setAttribute("mensaje", "No se encontró una mascota  con el Id: " + idMascota);
                  }

                  rs.close();
                  ps.close();

          } catch (Exception e) {
                  e.printStackTrace();
                  request.setAttribute("mensaje", "Error al consultar mascota: " + e.getMessage());
          }

          // Ir a la vista de consulta
          request.getRequestDispatcher("ConsultarMascota.jsp").forward(request, response);
  }
}
	
