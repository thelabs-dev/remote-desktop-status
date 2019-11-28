package dev.thelabs.rds.ui;
import java.util.List;
import java.util.Set;
import dev.thelabs.rds.User;
import dev.thelabs.rds.Utils;

public class MaterialUI extends BaseUI{

	@Override
	public String getHtmlClientsConnected() {
		Set<String> clientes = Utils.getConnectedIPs();
        String html = "";
        if (clientes.size()>0){
            try {
                for (String user : clientes) {
                    html += "<li class=\"mdc-list-item\">";
                    html +=     "<span class=\"mdc-list-item__text\">" + Utils.getNameByIP(user).trim() + "</span>";
                    html += "</li>";
                }
                html += "\n</div></div>";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            html += "<h3 class=\"card__title mdc-typography--caption\">No sessions started</h3>";
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
        html += "body{margin:0px;display: flex;}";
        html += ".page__content{margin-top: 72px;}";
        html += ".card__title{margin-left: 16px;margin-right: 16px;}";
        
        return html;
	}

	@Override
	public String getHtmlHeaders() {
        String html = "";
        html += "<link rel=\"stylesheet\" href=\"https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css\">";
        html += "<script src=\"https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js\"></script>";
        html += "<script src=\"chats/Chart.min.js\" crossorigin=\"anonymous\"></script>";
        html += "<script src=\"chats/chartjs-plugin-datalabels.min.js\" crossorigin=\"anonymous\"></script>";
        return html;
	}

	@Override
	public String getHtmlBody() {
        //Date  date = new Date();
        
        String html = "";
        /* "   <nav class=\"navbar navbar-expand-lg navbar-light bg-light\" style=\"background-color: #0d6003 !important;margin-bottom: 16px;\">";
        html += "       <div class=\"container\">";
        html += "           <a class=\"navbar-brand\" href=\"\" style=\"color: white;\">" + this.title +"</a>";
        html += "           <a target=\"_blank\" rel=\"noopener noreferrer\" href=\"https://github.com/thelabs-dev\" class=\"navbar-right\"><span class=\"navbar-text\" style=\"color: white;font-weight: lighter;\">";
        html += "               by thelabs.dev";
        html += "           </span></a>";
        html += "       </div>";
        html += "   </nav>";*/

        html += "<header class=\"mdc-top-app-bar mdc-top-app-bar--fixed\">";
        html +=     "<div class=\"mdc-top-app-bar__row\">";
        html +=         "<section class=\"mdc-top-app-bar__section mdc-top-app-bar__section--align-start\">";
        html +=             "<span class=\"mdc-top-app-bar__title\">" + this.title + "</span>";
        html +=         "</section>";
        html +=         "<section class=\"mdc-top-app-bar__section mdc-top-app-bar__section--align-end\" role=\"toolbar\">";
        html +=             "<button class=\"material-icons mdc-top-app-bar__action-item mdc-icon-button\" aria-label=\"Download\">file_download</button>";
        html +=         "</section>";
        html +=     "</div>";
        html += "</header>";

        html += "<div class=\"page__content page__flex-element\">";
        html +=     "<div class=\"mdc-layout-grid\">";
        html +=         "<div class=\"mdc-layout-grid__inner\">";


        html +=             "<div class=\"mdc-layout-grid__cell \">"; //mdc-layout-grid__cell--span-8
        html +=                 "<div class=\"mdc-card mdc-card--outlined\">";
        html +=                     "<h2 class=\"card__title mdc-typography mdc-typography--headline6\">Sesiones activas</h2>";
        html +=                     "<ul class=\"mdc-list\">";
        html +=                         getItemsInHTML();
        html +=                     "</ul>";
        html +=                 "</div>";
        html +=             "</div>";

        html +=             "<div class=\"mdc-layout-grid__cell \">"; //mdc-layout-grid__cell--span-8
        html +=                 "<div class=\"mdc-card mdc-card--outlined\">";
        html +=                     "<h2 class=\"card__title mdc-typography mdc-typography--headline6\">Usuarios remotos</h2>";
        html +=                     "<ul class=\"mdc-list\">";
        html +=                         this.getHtmlClientsConnected();
        html +=                     "</ul>";
        html +=                 "</div>";
        html +=             "</div>";

        html +=             "<div class=\"mdc-layout-grid__cell \">"; //mdc-layout-grid__cell--span-8
        html +=                 "<div class=\"mdc-card mdc-card--outlined\">";
        html +=                     "<h2 class=\"card__title mdc-typography mdc-typography--headline6\">CPU</h2>";
        html +=                     "<ul class=\"mdc-list\">";
        html +=                          getHtmlCpu();
        html +=                     "</ul>";
        html +=                 "</div>";
        html +=             "</div>";


        html +=             "<div class=\"mdc-layout-grid__cell\"></div>";
        html +=         "</div>";
        html +=     "</div>";
        html += "</div>";

        
/*
        html += "   <nav aria-label=\"breadcrumb\" class=\"container\">";
        html += "       <ol class=\"breadcrumb\">";
        html += "           <li class=\"breadcrumb-item active\" aria-current=\"page\">Update: " + dateFormat.format(date) + "</li>";
        html += "       </ol>";
        html += "   </nav>";
        html += "   <div class=\"container\">";
        html += "       <div class=\"row\">";
        html +=         getItemsInHTML(htmlClientes.isEmpty());
        html +=         htmlClientes;
        html += "       </div>";
        html += "   </div>";

*/
        html += "<script>";
        html +=     "mdc.topAppBar.MDCTopAppBar.attachTo(document.querySelector('.mdc-top-app-bar'));";
        html +=     "mdc.list.MDCList.attachTo(document.querySelector('.mdc-list'));";
        html += "</script>";
        return html;
    }
    
    static private String getItemsInHTML(){
        List<User> users = Utils.getNamesOfRemoteUsers();
        String html = "";
        try {
            if (users.size()>0){
                for (User user : users) {
                    html += "<li class=\"mdc-list-item\">";
                    html +=     "<span class=\"mdc-list-item__text\">" + user.name.trim()+ "</span>";
                    html +=     "<span class=\"badge " + (user.sessionactive ? "ocupado": "libre") + " badge-pill\">" + (user.sessionactive ? "OCUPADO": "LIBRE") +"</span>";
                    html += "</li>";
                }
            }else{
                html += "<h3 class=\"card__title mdc-typography--caption\">No sessions started</h3>";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    public String getHtmlCpu(){
        String html = "";
		try {
            double cpu = Utils.getCpuLoadPercentege();
            html = "<div style=\"width:100%;padding: 16px;padding-top: 0px;\"><canvas id=\"chart-area\" style=\"height:80px;\"></canvas></div>";
            html += "<script>var randomScalingFactor = function() {return Math.round(Math.random() * 100);};";
            html += "var config = {type: 'doughnut',data: {datasets: [{data: [";
            html += cpu + ",";
            html += (100-cpu) ;
            html += "],backgroundColor: ['rgb(255, 26, 75)','rgb(102, 204, 0)'],label: 'CPU'}],labels: ['En uso','Libre']},";
            html += "options: {responsive: true,legend: {position: 'top',},title: {display: false},animation: {animateScale: false,animateRotate: true},";
            html += "circumference:  Math.PI,rotation: Math.PI,legend:{display: false},tooltips: {enabled: false},";
            html += "plugins: {datalabels: {formatter: (value, ctx) => {let datasets = ctx.chart.data.datasets;if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {";
            html += "let sum = datasets[0].data.reduce((a, b) => a + b, 0);let percentage = Math.round((value / sum) * 100) + '%';return percentage;} else {return percentage;}},color: '#fff',font: {weight: 'bold',size: 20,}}}}};";
            html += "window.onload = function() {var ctx = document.getElementById('chart-area').getContext('2d');window.myDoughnut = new Chart(ctx, config);};";
            html += "</script>";
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return html;
    }
}