package dev.thelabs.rds;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;

@WebServlet(urlPatterns = {"/view"}, loadOnStartup = 1)
public class RemoteDesktopStatusServlet extends HttpServlet
{
    @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws IOException
    { 
        response.getOutputStream().println(getIndexPage());
    }

    static private List<User> obtenerUsuarios() {
        //  NOMBRE USUARIO        NOMBRE SESIàN      ID. ESTADO  TIEMPO IN. TIEMPO SESIàN
        List<User> users    = new ArrayList<User>();
        String command      = "powershell.exe -Command quser";
        String pattern      = "^ *(.+?) +(.*?) +([0-9]+?) +(.*?) ";

        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
            process.getOutputStream().close();
            BufferedReader reader=new BufferedReader( new InputStreamReader(process.getInputStream()));
            String s; 
            Boolean primerLinea = true;
            while ((s = reader.readLine()) != null){
                if(!primerLinea){
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(s);
                    if (m.find( )) {
                        String username         = m.group(1).trim();
                        String sessionname      = m.group(2).trim();
                        Boolean activo          = sessionname.contains("rdp");
                        
                        User user               = new User(username,activo);
                        user.name               = username;
                        user.sessionactive      = activo;
                        
                        users.add(user);
                    }
                }
                primerLinea = false;
            }  
            reader.close();

            String errorstr= "";
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
            process.getErrorStream()));
            while ((s = stderr.readLine()) != null) {
                errorstr += s + "<br>";
            }
            stderr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return users;
    }

    private String getIndexPage(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); //"yyyy/MM/dd HH:mm:ss");
	    Date  date = new Date();
        
        String html = "";
        html += "<!doctype html>";
        html += "<html lang=\"en\">";
        html += "<head>";
        html += "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />";
        html += "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">";
        html += "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\" crossorigin=\"anonymous\">";
        html += "    <title>Remote Desktop Status</title>";
        html += "<style>.loader {  position: relative;  text-align: center;  margin: 15px auto 35px auto;  z-index: 9999;  display: block;  width: 80px;  height: 80px;  border: 10px solid rgba(0, 0, 0, .3);  border-radius: 50%;  border-top-color: #000;  animation: spin 1s ease-in-out infinite;  -webkit-animation: spin 1s ease-in-out infinite;}@keyframes spin {  to {    -webkit-transform: rotate(360deg);  }}@-webkit-keyframes spin {  to {    -webkit-transform: rotate(360deg);  }}</style>";
        html += "  </head>";
        html += "  <body>";
        html += "   <nav class=\"navbar navbar-expand-lg navbar-light bg-light\" style=\"background-color: #0d6003 !important;margin-bottom: 16px;\">";
        html += "       <div class=\"container\">";
        html += "           <a class=\"navbar-brand\" href=\"\" style=\"color: white;\">Remote Desktop Status</a>";
        html += "           <a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://github.com/thelabs-dev\" class=\"navbar-right\"><span class=\"navbar-text\" style=\"color: white;font-weight: lighter;\">";
        html += "               by thelabs.dev";
        html += "           </span></a>";
        html += "       </div>";
        html += "   </nav>";
        html += "   <nav aria-label=\"breadcrumb\" class=\"container\">";
        html += "       <ol class=\"breadcrumb\">";
        html += "           <li class=\"breadcrumb-item active\" aria-current=\"page\">Update: " + dateFormat.format(date) + "</li>";
        html += "       </ol>";
        html += "   </nav>";
        html += "   <div class=\"container\">";
        html += "       <div class=\"row\">";
        html +=         getItemsInHTML();
        html += "       </div>";
        html += "   </div>";
        html += "    <script src=\"js/jquery.min.js\" crossorigin=\"anonymous\"></script>";
        html += "    <script src=\"js/popper.min.js\" crossorigin=\"anonymous\"></script>";
        html += "    <script src=\"js/refresh.js\" crossorigin=\"anonymous\"></script>";
        html += "    <script src=\"js/bootstrap.min.js\" crossorigin=\"anonymous\"></script>";
        html +=      getStyleHTML();
        html += "  </body>";
        html += "</html>";
        return html;
    }

    static private String getItemsInHTML(){
        List<User> users = obtenerUsuarios();
        String html = "";
        try {
            html += "\n<div class=\"col-12\"><div class=\"list-group\" id=\"list-tab\" role=\"tablist\">";
            for (User user : users) {
                html += "\n<a class=\"list-group-item list-group-item-action d-flex justify-content-between align-items-center\" id=\"list-profile-list\" data-toggle=\"modal\" role=\"tab\" aria-controls=\"profile\">";
                html += user.name.trim();
                html += "<span class=\"badge " + (user.sessionactive ? "ocupado": "libre") + " badge-pill\">" + (user.sessionactive ? "OCUPADO": "LIBRE") +"</span>";
                html += "\n</a>";
            }
            html += "\n</div></div>";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }


    static private String getStyleHTML(){
        String html = "";
        html += "<style>";
        html += ".libre {";
        html += "   color: #2f2e2e;";
        html += "   background-color: #06F906;";
        html += "}";
        html += ".ocupado {";
        html += "   color: #FFF;";
        html += "   background-color: #F00;";
        html += "}";
        html += "</style>";
        return html;
    }
}

class User{
    String name;
    Boolean sessionactive;

    User(String n, Boolean active){
        name = n;
        sessionactive = active;
    }
}