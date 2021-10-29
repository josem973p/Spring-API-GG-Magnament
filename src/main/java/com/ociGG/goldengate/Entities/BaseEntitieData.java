package com.ociGG.goldengate.Entities;

public class BaseEntitieData {

    String url;
    String user;
    String password;
    String parametro;



    public BaseEntitieData(String url, String user, String password, String parametro) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.parametro = parametro;
    }

    public BaseEntitieData(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public BaseEntitieData() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
}
