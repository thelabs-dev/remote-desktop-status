package dev.thelabs.rds.ui;

import java.util.Date;
import java.util.List;
import java.util.Set;
import dev.thelabs.rds.User;
import dev.thelabs.rds.Utils;

public class BootstrapUI extends BaseUI{
        
	@Override
	public String getHtmlClientsConnected() {
		Set<String> clientes = Utils.getConnectedIPs();
        String html = "";
        if (clientes.size()>0){
            try {
                html += "\n<div class=\"col-4\"><div class=\"list-group\" id=\"list-tab\" role=\"tablist\">";
                for (String user : clientes) {
                    html += "\n<a class=\"list-group-item list-group-item-action d-flex justify-content-between align-items-center\" id=\"list-profile-list\" role=\"tab\" aria-controls=\"profile\" data-toggle=\"tooltip\" title=\"" + user.trim() + "\">";
                    html += Utils.getNameByIP(user).trim();;
                    html += "\n</a>";
                }
                html += "\n</div></div>";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return html;
	}

	@Override
	public String getHtmlCssStyle() {
        String html = "";
        html += ".loader {  position: relative;  text-align: center;  margin: 15px auto 35px auto;  z-index: 9999;  display: block;  width: 80px;  height: 80px;  border: 10px solid rgba(0, 0, 0, .3);  border-radius: 50%;  border-top-color: #000;  animation: spin 1s ease-in-out infinite;  -webkit-animation: spin 1s ease-in-out infinite;}@keyframes spin {  to {    -webkit-transform: rotate(360deg);  }}@-webkit-keyframes spin {  to {    -webkit-transform: rotate(360deg);  }}";

        html += ".libre {";
        html += "   color: #2f2e2e;";
        html += "   background-color: #06F906;";
        html += "}";
        html += ".ocupado {";
        html += "   color: #FFF;";
        html += "   background-color: #F00;";
        html += "}";
        return html;
	}

	@Override
	public String getHtmlHeaders() {
        String html = "";
        html += "<link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.min.css\" crossorigin=\"anonymous\">";
		html += "<script src=\"bootstrap/js/jquery.min.js\" crossorigin=\"anonymous\"></script>";
        html += "<script src=\"bootstrap/js/popper.min.js\" crossorigin=\"anonymous\"></script>";
        html += "<script src=\"bootstrap/js/refresh.js\" crossorigin=\"anonymous\"></script>";
        html += "<script src=\"bootstrap/js/bootstrap.min.js\" crossorigin=\"anonymous\"></script>";
        html += "<script src=\"chats/Chart.min.js\" crossorigin=\"anonymous\"></script>";
        html += "<script src=\"chats/chartjs-plugin-datalabels.min.js\" crossorigin=\"anonymous\"></script>";
        return html;
	}

	@Override
	public String getHtmlBody() {
        Date  date = new Date();
        String htmlClientes = this.getHtmlClientsConnected();

        String html = "";
        // APP BAR
        html += "   <nav class=\"navbar navbar-expand-lg navbar-light bg-light\" style=\"background-color: #0d6003 !important;margin-bottom: 16px;\">";
        html += "       <div class=\"container\">";
        html += "           <a class=\"navbar-brand\" href=\"\" style=\"color: white;\">" + this.title +"</a>";
        //html += "           <a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://github.com/thelabs-dev\" class=\"navbar-right\"><span class=\"navbar-text\" style=\"color: white;font-weight: lighter;\">";
        //html += "               by thelabs.dev";
        //html += "           </span></a>";
        html += "       </div>";
        html += "   </nav>";

        // GITHUB LOGO
        html += "<a href=\"https://github.com/thelabs-dev\" class=\"github-corner\" aria-label=\"View source on GitHub\"><svg width=\"80\" height=\"80\" viewBox=\"0 0 250 250\" style=\"fill:#ffffff; color:#0d6003 ; position: absolute; top: 0; border: 0; right: 0;\" aria-hidden=\"true\"><path d=\"M0,0 L115,115 L130,115 L142,142 L250,250 L250,0 Z\"></path><path d=\"M128.3,109.0 C113.8,99.7 119.0,89.6 119.0,89.6 C122.0,82.7 120.5,78.6 120.5,78.6 C119.2,72.0 123.4,76.3 123.4,76.3 C127.3,80.9 125.5,87.3 125.5,87.3 C122.9,97.6 130.6,101.9 134.4,103.2\" fill=\"currentColor\" style=\"transform-origin: 130px 106px;\" class=\"octo-arm\"></path><path d=\"M115.0,115.0 C114.9,115.1 118.7,116.5 119.8,115.4 L133.7,101.6 C136.9,99.2 139.9,98.4 142.2,98.6 C133.8,88.0 127.5,74.4 143.8,58.0 C148.5,53.4 154.0,51.2 159.7,51.0 C160.3,49.4 163.2,43.6 171.4,40.1 C171.4,40.1 176.1,42.5 178.8,56.2 C183.1,58.6 187.2,61.8 190.9,65.4 C194.5,69.0 197.7,73.2 200.1,77.6 C213.8,80.2 216.3,84.9 216.3,84.9 C212.7,93.1 206.9,96.0 205.4,96.6 C205.1,102.4 203.0,107.8 198.3,112.5 C181.9,128.9 168.3,122.5 157.7,114.1 C157.9,116.9 156.7,120.9 152.7,124.9 L141.0,136.5 C139.8,137.7 141.6,141.9 141.8,141.8 Z\" fill=\"currentColor\" class=\"octo-body\"></path></svg></a><style>.github-corner:hover .octo-arm{animation:octocat-wave 560ms ease-in-out}@keyframes octocat-wave{0%,100%{transform:rotate(0)}20%,60%{transform:rotate(-25deg)}40%,80%{transform:rotate(10deg)}}@media (max-width:500px){.github-corner:hover .octo-arm{animation:none}.github-corner .octo-arm{animation:octocat-wave 560ms ease-in-out}}</style>";
        
        // ULTIMA ACTUALIZACION
        html += "   <nav aria-label=\"breadcrumb\" class=\"container\">";
        html += "       <ol class=\"breadcrumb\">";
        html += "           <li class=\"breadcrumb-item active\" aria-current=\"page\">Actualizado: " + dateFormat.format(date) + "</li>";
        html +=             "<div id=\"cpu\" class=\"spinner-border text-primary\" role=\"status\" style=\"color:#8c8c8c !important;height:18px;width:18px;display: flex;margin-left: auto;margin-top: auto;margin-bottom: auto;\"></div>";
        html += "       </ol>";
        html += "   </nav>";


        html += "   <div class=\"container\">";
        html += "       <div class=\"row\">";
        html +=         getItemsInHTML(htmlClientes.isEmpty());
        html +=         htmlClientes;
        html += "       </div>";
        html += "   </div>";


        html += "<script>";
        html +=     "setTimeout(function() {";
        html +=     "var request = $.ajax({";
        html +=         "url: 'view',";
        html +=         "method: 'POST',";
        html +=         "data: 'action=cpu',";
        html +=     "});";
    
        html +=     "request.done(function(respuesta) {";
        html +=         "$(\"#cpu\").replaceWith(respuesta);";
        html +=         "$('[data-toggle=\"tooltip\"]').tooltip();";
        html +=     "});";

        html +=     "request.fail(function(jqXHR, textStatus) {";
        html +=         "alert('Hubo un error: ' + textStatus);";
        html +=     "}); }, 1000);";

        html += "$(function () {$('[data-toggle=\"tooltip\"]').tooltip()})";
        html += "</script>";
        return html;
    }
    
    static private String getItemsInHTML(Boolean isFull){
        List<User> users = Utils.getNamesOfRemoteUsers();
        String html = "";
        try {
            html += "\n<div class=\"col-" + (isFull ? "12" : "8") +"\"><div class=\"list-group\" id=\"list-tab\" role=\"tablist\">";
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

    public String getHtmlCpu(){
        String html = "";
		try {
            int cpu = Utils.getCpuLoadPercentege();
            if (cpu >=0){
                html += "<div class=\"progress\" data-toggle=\"tooltip\" title=\"CPU\" data-placement=\"left\" style=\"height:14px;width:100px;display: flex;margin-left: auto;margin-top: auto;margin-bottom: auto;\">";
                html += "<div class=\"progress-bar\" role=\"progressbar\" style=\"background-color: #e60032;width: " + cpu + "%\" aria-valuenow=\"" + cpu + "\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div>";
                html += "<div class=\"progress-bar\" role=\"progressbar\" style=\"background-color: #59b300;width: " +(100-cpu) + "%\" aria-valuenow=\""+(100-cpu)+"\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div>";
                html += "</div>";
            }
            //    html = "<div style=\"height:14px;width:100px;display: flex;margin-left: auto;margin-top: auto;margin-bottom: auto;\"><div style=\"background-color: #ff1a4b; height:100%; width:" +cpu + "%\"></div><div style=\"background-color: #66cc00; height:100%; width:"+(100-cpu)+"%\"></div></div>";
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return html;
    }

}