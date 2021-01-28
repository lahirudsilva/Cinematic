/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminDAO;
import Model.DAOFactory;
import Model.MovieFactory;
import Model.Movies;
import Model.Reservation;
import Model.UserDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)

/**
 *
 * @author Lahiru De silva
 */
public class AddMovieController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String command = request.getParameter("command");
            //getting the command parameter from the webpage into a String variable

            if (command == null) {
                command = "AddMovies";
            }

            switch (command) {
                //switch-case statement to call methods depending on the command string variable
                case "AddMovies":
                    addMovies(request, response);
                    break;
                
                default:
                    addMovies(request, response);

            }
        } catch (Exception e) {
           Logger.getLogger(AddMovieController.class.getName()).log(Level.SEVERE,null,e);
        }

    }
    
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    final Set<String> identifiers = new HashSet<String>();

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
    

    private void addMovies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
      
        UserDAO daoObject = DAOFactory.createDAO("admin");
        
        PrintWriter pout = response.getWriter();//retrieve an instance of PrintWriter
        response.setContentType("text/html"); //set content type to html

        String movieName = request.getParameter("MovieName");
        String theaterName = request.getParameter("Theater");
        String category = request.getParameter("Category");
        Part filePart = request.getPart("fileToUpload");
        String status = request.getParameter("status");

        String imgfileName = null;
        String photo = "";
        String path = "C:\\Users\\Asus\\Documents\\NetBeansProjects\\Cinematic\\web\\MovieImages";
        File file = new File(path);
        file.mkdir();
        //String fileName = getFileName(filePart);
        if (filePart == null) {
            imgfileName = null;
        } else {
            imgfileName = randomIdentifier() + ".jpg";
        }

        OutputStream out = null;
        InputStream filecontent = null;

        PrintWriter writer = response.getWriter();
       

            out = new FileOutputStream(new File(path + File.separator + imgfileName));

            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                //photo = path + "\\" + fileName;
                photo = "MovieImages\\" + imgfileName;
                
            }
      
        
        
        
        Movies movie = new Movies(movieName,theaterName,category,photo,status);
        
        boolean isUserAdded = ((AdminDAO) daoObject).addMovies(movie);
        
        if(isUserAdded)
        {
            pout.println("<p style=\"color:white;font-weight: bolder;margin-top:10px;\">Movie successfully added to the system.........</p>");
            request.getRequestDispatcher("add-movies.jsp").include(request, response);
        }
        else
        {
            pout.println("<p style=\"color:#black; font:font-weight: bold;\">Movie did not added to the system ..........</p>");
            request.getRequestDispatcher("add-movies.jsp").include(request, response);
        }
        
        
        

//        Movies movie = new Movies();
//        dao.addUser(user);
//        response.sendRedirect("success.html");
    }

    

}
