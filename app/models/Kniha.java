package models;

import play.db.ebean.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Kuba on 12.11.2014.
 */

@Entity
public class Kniha extends Model {

    @Id
    @GeneratedValue
    public int id;

    public String nazev;

    public String autor;

    public String rokVydani;

    public String nakladatelstvi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getRokVydani() {
        return rokVydani;
    }

    public void setRokVydani(String rokVydani) {
        this.rokVydani = rokVydani;
    }

    public String getNakladatelstvi() {
        return nakladatelstvi;
    }

    public void setNakladatelstvi(String nakladatelstvi) {
        this.nakladatelstvi = nakladatelstvi;
    }

    /**
     * Nalezne knihu
     */
    public static Finder<String,Kniha> find = new Finder<String,Kniha>(
            String.class, Kniha.class);




}
