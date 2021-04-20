/**
 * Produs
 * */
package model;

public class Produs {
    private long idprodus;
    private String nume;
    private int cantitate;
    private double pret;
    public Produs(){}
    public Produs(long produsId, String name, int cantitate,double pret) {
        this.idprodus=produsId;
        this.nume=name;
        this.cantitate=cantitate;
        this.pret=pret;
    }

    public long getIdprodus() {
        return idprodus;
    }

    public void setIdprodus(long idprodus) {
        this.idprodus = idprodus;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }
}
