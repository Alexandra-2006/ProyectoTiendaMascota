package Controlador;

import java.io.IOException;
import java.util.List;

import DAO.MascotaDAO;
import Modelo.Mascota;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MascotaServlet", urlPatterns = "/MascotaServlet")
public class MascotaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    MascotaDAO Dao = new MascotaDAO();
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        switch (accion) {
            case "insertar":
                insertar(request, response);
                return;

            case "actualizar":
                actualizarMascota(request, response);
                return;

            case "eliminar":
                eliminarMascota(request, response);
                return;

            case "listar":
                List<Mascota> lista = Dao.listarMascota();
                request.setAttribute("mascotas", lista);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
                return;

                
          
            default:
                response.sendRedirect("index.jsp");
                return;
        }
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("Parámetros recibidos:");
        request.getParameterMap().forEach((key, value) -> {
            System.out.println(key + " = " + String.join(", ", value));
        });
        

        try {
        	
        	
            String nombre = request.getParameter("nombre");
            String especie = request.getParameter("especie");
            String genero = request.getParameter("genero");
            String raza = request.getParameter("raza");
            int idcedula = Integer.parseInt(request.getParameter("IDCedula"));

            Mascota m = new Mascota();
            m.setNombre(nombre);
            m.setEspecie(especie);
            m.setGénero(genero);
            m.setRaza(raza);
            m.setIdcedula(idcedula);

            Dao.insert(m);
            
            
            if (!response.isCommitted()) {
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("La respuesta ya está comprometida, no se puede redirigir");
            }
            return;

        
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al insertar mascota", e);
        }
    }

    private void actualizarMascota(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int idMascota = Integer.parseInt(request.getParameter("IDMascota"));
            String nombre = request.getParameter("nombre");
            String especie = request.getParameter("especie");
            String genero = request.getParameter("genero");
            String raza = request.getParameter("raza");
            int idcedula = Integer.parseInt(request.getParameter("IDCedula"));

            Mascota m = new Mascota();
            m.setIDMascota(idMascota);
            m.setNombre(nombre);
            m.setEspecie(especie);
            m.setGénero(genero);
            m.setRaza(raza);
            m.setIdcedula(idcedula);

            Dao.update(m);
            if (!response.isCommitted()) {
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("La respuesta ya está comprometida, no se puede redirigir");
            }
            return;

         
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar mascota");
        }
    }

    private void eliminarMascota(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int idMascota = Integer.parseInt(request.getParameter("IDMascota"));
            Dao.delete(idMascota);
            
            if (!response.isCommitted()) {
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("La respuesta ya está comprometida, no se puede redirigir");
            }
            return;
           

           
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar mascota");
        }
  
        }

    
}
