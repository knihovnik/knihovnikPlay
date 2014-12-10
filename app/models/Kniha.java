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
    public String id;

    public String nazev;

    public String autor;

    public String rokVydani;

    public String nakladatelstvi;

    /**
     * Nalezne knihu
     */
    public  static Finder<String,Kniha> find = new Finder<String,Kniha>(
            String.class, Kniha.class
    );

    /*public void save(){
        JPA.em().persist(this);
    }*/


}
