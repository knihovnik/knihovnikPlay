package models;

/**
 * Created by jaraskala on 18.12.14.
 */

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Vypujcka extends Model {

    @Id
    @GeneratedValue
    public int id;

    public Kniha kniha;

    public Uzivatel uzivatel;

    public Date datumVypujceni;

    public Date datumVraceni;

    public Vypujcka(Kniha kniha, Uzivatel uzivatel, Date datumVypujceni, Date datumVraceni) {
        this.kniha = kniha;
        this.uzivatel = uzivatel;
        this.datumVypujceni = datumVypujceni;
        this.datumVraceni = datumVraceni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Date getDatumVypujceni() {
        return datumVypujceni;
    }

    public void setDatumVypujceni(Date datumVypujceni) {
        this.datumVypujceni = datumVypujceni;
    }

    public Date getDatumVraceni() {
        return datumVraceni;
    }

    public void setDatumVraceni(Date datumVraceni) {
        this.datumVraceni = datumVraceni;
    }
}
