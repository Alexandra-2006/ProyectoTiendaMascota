
package Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// Librerías de iText

@WebServlet(name = "GenerarPdfServlet", urlPatterns = "/GenerarPdfServlet")
public class GenerarPdfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GenerarPdfServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //  Forzar descarga automática
        //response.setContentType("application/pdf");
        //response.setHeader("Content-Disposition", "attachment; filename=reporte_clientes.pdf");
    	
    	
    	 try (Connection connection = Conexion.getConnection()) {

             if (connection == null) {
                 throw new ServletException("No se pudo establecer la conexión con la base de datos.");
             }

             Document document = new Document();
             PdfWriter.getInstance(document, response.getOutputStream());
             document.open();

             //  Encabezado del PDF
             document.add(new Paragraph("Lista de Mascotas"));
             document.add(new Paragraph("Fecha de generación: " + new java.util.Date()));
             document.add(new Paragraph(" "));

             // Consulta a la BD
             
             String sql = "SELECT Nombre, Especie, Género, Raza, IDCedula FROM mascota";
             try (Statement stmt = connection.createStatement();
                  ResultSet rs = stmt.executeQuery(sql)) {

                 // Crear tabla con 5 columnas
                 PdfPTable table = new PdfPTable(5);
                 table.setWidthPercentage(100);

                 // Encabezados
                 table.addCell("Nombre");
                 table.addCell("Especie");
                 table.addCell("Género");
                 table.addCell("Raza");
                 table.addCell("IDCedula");

                 // Llenar la tabla con los datos de la BD
                 while (rs.next()) {
                     table.addCell(rs.getString("Nombre"));
                     table.addCell(rs.getString("Especie"));
                     table.addCell(rs.getString("Género"));
                     table.addCell(rs.getString("Raza"));
                     table.addCell(rs.getString("IDCedula"));
                 }

                 // Agregar tabla al documento
                 document.add(table);
             }

             document.close();

         } catch (SQLException e) {
             throw new ServletException("Error al acceder a la base de datos", e);
         } catch (Exception e) {
             throw new ServletException("Error al generar el PDF", e);
         }

       
    }}