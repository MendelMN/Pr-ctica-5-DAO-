package com.emergentes.controlador;
import com.emergentes.dao.BlogDAO;
import com.emergentes.dao.BlogDAOimpl;
import com.emergentes.modelo.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Blog cli = new Blog();
            int id;
            BlogDAO dao = new BlogDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    request.setAttribute("blog", cli);
                    request.getRequestDispatcher("frmblog.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    cli =  dao.getById(id);
                    request.setAttribute("blog", cli);
                    request.getRequestDispatcher("frmblog.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("Controlador");
                    break;
                case "view":
                    // Obtener la lista de registros
                    List<Blog> lista = dao.getAll();
                    request.setAttribute("blog",lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                    
                default:
                    break;
            }
            
        }catch(Exception ex){
            System.out.println("Error en doget" + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SimpleDateFormat forma= new SimpleDateFormat("yyyy-MM-dd");
        
        int id = Integer.parseInt(request.getParameter("id"));
        Date fecha;
        Blog cli = new Blog();
        try{
        fecha = forma.parse(request.getParameter("fecha"));
        
        cli.setFecha((java.sql.Date) fecha);
        }catch(Exception ex)
        { 
            System.out.println("Error al editar "+ex.getMessage());
        }
        String titulo =  request.getParameter("titulo");
        String contenido =  request.getParameter("contenido");
        
        
        cli.setId(id);
        
        cli.setTitulo(titulo);
        cli.setContenido(contenido);
        
        
        BlogDAO dao = new BlogDAOimpl();
        if (id == 0){
            try {
                // Nuevo registro
                dao.insert(cli);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ex.getMessage());
            }
        }
        else{
            try {
                // Edicion de registro
                dao.update(cli);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            }
        }
        response.sendRedirect("Controlador");
    }
 
}
