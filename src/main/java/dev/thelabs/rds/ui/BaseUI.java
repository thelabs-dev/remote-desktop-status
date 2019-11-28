package dev.thelabs.rds.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class BaseUI
{
    protected String title = "Remote Desktop Status";
    protected DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); //"yyyy/MM/dd HH:mm:ss");

    public abstract String getHtmlClientsConnected();
    public abstract String getHtmlCssStyle();
    public abstract String getHtmlHeaders();
    public abstract String getHtmlBody();
    public abstract String getHtmlCpu();

}