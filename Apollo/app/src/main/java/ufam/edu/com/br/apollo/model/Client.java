package ufam.edu.com.br.apollo.model;

/**
 * Created by JhonnyBarbosa on 7/13/16.
 */
public class Client {

    private String IP;

    private String userName;

    public void setIP(String IP){
        this.IP = IP;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getIP(){
        return this.IP;
    }

    public String getUserName(){
        return this.userName;
    }
}
