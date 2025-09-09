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

//  Responde a la URL /MascotaServlet.
@WebServlet(name = "MascotaServlet", urlPatterns = "/MascotaServlet")
public class MascotaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    MascotaDAO Dao = new MascotaDAO();
    Notificaciones noti = new Notificaciones();
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}
    
    //Peticiones HTTP(GET,POST)//Recibir datos del formulario y pasarlo al DAO

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");//Lee el parámetro oculto del formulario, para saber qué hacer

        if (accion == null) {
            response.sendRedirect("index.jsp");//Redirige al index
            return;
        }
       // Este bloque se encarga de controlar el flujo de ejecución según la acción solicitada desde el cliente através del formulario. 
       // Evalúa el valor de la variable accion y ejecuta la operación correspondiente sobre una entidad Mascota. Dependiendo del caso:
        switch (accion) {
            case "insertar"://Llama al método que está en el formulario y los pasa al DAO
                insertar(request, response);
                return;

            case "actualizar":
                actualizarMascota(request, response);
                return;

            case "eliminar":
                eliminarMascota(request, response);
                return;

            case "listar": //Obtiene la lista desde el DAO
                List<Mascota> lista = Dao.listarMascota();
                request.setAttribute("mascota", lista);//Guarda en el resquest
                request.getRequestDispatcher("Listar.jsp").forward(request, response);
                return;

                
          
            default://Si la acción no existe redirige al index
                response.sendRedirect("index.jsp");
                return;
        }
    }
    //Inserta una nueva mascota en la base de datos.
   // * Recibe los datos desde el formulario, los encapsula en un objeto Mascota,
    //* y llama al DAO para almacenarla.
   
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

            Mascota m = new Mascota();//Objeto mascota para asignar los valores
            m.setNombre(nombre);
            m.setEspecie(especie);
            m.setGénero(genero);
            m.setRaza(raza);
            m.setIdcedula(idcedula);

            Dao.insert(m);//Llama al Dao
            
          
            if (!response.isCommitted()) {
                response.sendRedirect("index.jsp"); //Redirige al index
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
        	
        	 // Obtener y validar IDMascota
            String idMascotaStr = request.getParameter("IDMascota");
            if (idMascotaStr == null || idMascotaStr.trim().isEmpty()) {
                throw new IllegalArgumentException("IDMascota no puede estar vacío o nulo.");
            }
            int idMascota = Integer.parseInt(idMascotaStr);

            // Obtener y validar IDCedula
            String idCedulaStr = request.getParameter("IDCedula");
            if (idCedulaStr == null || idCedulaStr.trim().isEmpty()) {
                throw new IllegalArgumentException("IDCedula no puede estar vacío o nulo.");
            }
            int idcedula = Integer.parseInt(idCedulaStr);

           
            String nombre = request.getParameter("nombre");
            String especie = request.getParameter("especie");
            String genero = request.getParameter("genero");
            String raza = request.getParameter("raza");
            
            

            Mascota m = new Mascota();//Objeto Mascota
            m.setIDMascota(idMascota);
            m.setNombre(nombre);
            m.setEspecie(especie);
            m.setGénero(genero);
            m.setRaza(raza);
            m.setIdcedula(idcedula);
			
            System.out.println("Llamando a Dao.update...");
            Dao.update(m);
            System.out.println("Mascota actualizada correctamente.");
            
            try {
                noti.SIM("Actualización de registro", "Se actualizó el registro de la mascota con nombre: " + m.getNombre());
                System.out.println("Correo enviado correctamente.");
            } catch (Exception e) {
                System.out.println("Error al enviar correo: " + e.getMessage());
            }


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
            Dao.delete(idMascota);//Llama al DAO
            
            
            if (!response.isCommitted()) {
                response.sendRedirect("index.jsp");//Redirige al index
            } else {
                System.out.println("La respuesta ya está comprometida, no se puede redirigir");
            }
            return;
           
        } catch (NumberFormatException e) {
            // Error específico si IDCedula o IDMascota no son números válidos
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "IDCedula o IDMascota no son números válidos.");
        } catch (IllegalArgumentException e) {
            // Error si los parámetros requeridos están vacíos
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
           
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar mascota");
        }
  
        }

    
}
