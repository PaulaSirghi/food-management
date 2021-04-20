/**
 * Client
 * */
package model;

public class Client{
    private long idclient;
    private String email;
    private String nume;


    public Client(){}
    public Client(long idclient, String email, String nume) {
        this.idclient = idclient;
        this.nume = nume;
        this.email = email;
    }

    public long getIdclient() {
        return idclient;
    }

    public void setIdclient(long idclient) {
        this.idclient = idclient;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
