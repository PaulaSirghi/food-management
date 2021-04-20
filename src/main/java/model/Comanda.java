/**
 * Comanda
 * */
package model;

public class Comanda {
    private int idcomanda;
    private long idclient;
    private long idprodus;
    private int cantitate;
    private double pret;
    public Comanda(int idcomanda, long idclient, long idprodus, int cantitate,double pret)
    {
        this.idclient=idclient;
        this.idcomanda=idcomanda;
        this.cantitate=cantitate;
        this.idprodus=idprodus;
        this.pret=pret;
    }

    public double getPret() {
        return pret;
    }

    public int getIdcomanda() {
        return idcomanda;
    }

    public void setIdcomanda(int idcomanda) {
        this.idcomanda = idcomanda;
    }

    public long getIdclient() {
        return idclient;
    }

    public void setIdclient(long idclient) {
        this.idclient = idclient;
    }

    public long getIdprodus() {
        return idprodus;
    }

    public void setIdprodus(long idprodus) {
        this.idprodus = idprodus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }
}
