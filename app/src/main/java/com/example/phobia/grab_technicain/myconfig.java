package com.example.phobia.grab_technicain;

/**
 * Created by Phobia on 5/15/2017.
 */

public class myconfig {
    private String ip = "10.5.12.132";
    private String port = "8081";
    private String register = "http://"+ip+":"+port+"/Final_Project/service/register.php";
    private String login = "http://"+ip+":"+port+"/Final_Project/service/login.php";
    private String show_all_marker ="http://"+ip+":"+port+"/Final_Project/service/get_allTechnician.php";
    public String getLogin() {
        return login;
    }

    public String getRegister() {
        return register;
    }

    public String getShow_all_marker() {
        return show_all_marker;
    }
}
