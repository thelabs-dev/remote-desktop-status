package dev.thelabs.rds;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dev.thelabs.rds.ui.BaseUI;
import dev.thelabs.rds.ui.BootstrapUI;

@WebServlet(urlPatterns = {"/view"}, loadOnStartup = 1)
public class RemoteDesktopStatusServlet extends HttpServlet
{
	private static final long serialVersionUID = 370641943452937450L;

	@Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    { 
        response.getOutputStream().println(getHtmlPage());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        BaseUI ui = new BootstrapUI();
        if(action.equalsIgnoreCase("cpu")){
            response.getOutputStream().println(ui.getHtmlCpu());
            return;
        }
    }

    private String getHtmlPage(){
        BaseUI ui = new BootstrapUI();

        String html = "";
        html += "<!doctype html>";
        html += "<html lang=\"en\">";
        html +=     "<head>";
        html +=         "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />";
        html +=         "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">";
        html +=         ui.getHtmlHeaders();
        html +=         "<title>Remote Desktop Status</title>";
        html +=         "<style>";
        html +=             ui.getHtmlCssStyle();
        html +=         "</style>";
        html +=     "</head>";
        html +=     "<body>";
        html +=         ui.getHtmlBody();
        html +=     "</body>";
        html += "</html>";
        return html;
    }
}