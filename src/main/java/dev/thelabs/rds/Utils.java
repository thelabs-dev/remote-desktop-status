
package dev.thelabs.rds;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utils
{
    static public Set<String> getConnectedIPs() {
        //  TCP    192.168.0.120:3389     192.168.0.110:51461    ESTABLISHED
        Set<String> clientes    = new HashSet<String>();
        String command      = "netstat -n | find \"3389\" | find \"ESTABLISHED\"";
        String pattern      = "^ *?[a-zA-Z]+? *?\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b:[0-9]+? +?(\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b):[0-9]+ *?[a-zA-Z]+";
        Process process;
        try {
            process = Runtime.getRuntime().exec("cmd.exe /c " + command);
            BufferedReader reader=new BufferedReader( new InputStreamReader(process.getInputStream()));
            process.getOutputStream().close();
            String s; 
            while ((s = reader.readLine()) != null){
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(s);
                if (m.find( )) {
                    String ip         = m.group(1).trim();
                    clientes.add(ip);
                }
            }  
            reader.close();
            BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorstr= "";
            while ((s = stderr.readLine()) != null) {
                errorstr += s + "\n";
            }
            if (!errorstr.isEmpty())
                System.out.println(errorstr);
            stderr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return clientes;
    }

    static public String getNameByIP(String userIP){
        File fXmlFile = new File("C:\\thelabs\\rds\\users.xml");
        if (fXmlFile.exists()){
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
                NodeList kbs = doc.getElementsByTagName("user");
                for (int temp = 0; temp < kbs.getLength(); temp++) {
                    Node nNode = kbs.item(temp);                      
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        String ip = getStringElement(eElement, "ip");
                        if (userIP.equals(ip)){
                            String name         = getStringElement(eElement,"name");
                            if (name.isEmpty()){
                                return userIP;
                            }else{
                                return name;
                            }
                        }
                    }
                }
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        return userIP;           
    }

    static public String getStringElement(Element eElement, String key){
        String value   = ((eElement.getElementsByTagName(key) != null && eElement.getElementsByTagName(key).getLength() > 0) ? eElement.getElementsByTagName(key).item(0).getTextContent() : "");
        return value;
    }

    static public List<User> getNamesOfRemoteUsers() {
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

            BufferedReader stderr = new BufferedReader(new InputStreamReader(
            process.getErrorStream()));
            while ((s = stderr.readLine()) != null) {
                System.out.println(s);
            }
            stderr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return users;
    }

    public static int getCpuLoadPercentege() throws Exception {
        int percentege      = -1;
        String command      = "wmic cpu get loadpercentage";
        String pattern      = "[0-9]+";
        Process process;
        try {
            process = Runtime.getRuntime().exec("cmd.exe /c " + command);
            BufferedReader reader=new BufferedReader( new InputStreamReader(process.getInputStream()));
            process.getOutputStream().close();
            String s; 
            Pattern r = Pattern.compile(pattern);
            while ((s = reader.readLine()) != null){
                Matcher m = r.matcher(s);
                if (m.find( )) {
                    String val  = m.group(0).trim();
                    percentege = Integer.parseInt(val);   
                }
            }  
            reader.close();
            BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorstr= "";
            while ((s = stderr.readLine()) != null) {
                errorstr += s + "\n";
            }
            if (!errorstr.isEmpty())
                System.out.println(errorstr);
            stderr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return percentege;
    }
}