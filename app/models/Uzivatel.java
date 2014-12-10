package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by jaraskala on 10.12.14.
 */
@Entity
public class Uzivatel extends Model {

    @Id
    @GeneratedValue
    public String id;

    public String jmeno;

    public String heslo;

    public Uzivatel(String jmeno, String heslo) {
        this.jmeno = jmeno;
        this.heslo=heslo;
    }

    public static Finder<String,Uzivatel> find = new Finder<String,Uzivatel>(
            String.class, Uzivatel.class
    );

    public static boolean kontrolaPrihlaseni (String jmeno, String heslo){
        List<Uzivatel> uzivatele = new Model.Finder(String.class, Uzivatel.class).all();
        boolean kontrolaPrihlaseni=false;
        for (int i=0; i< uzivatele.size(); i++){
            if(uzivatele.get(i).jmeno.equals(jmeno)){
                if(uzivatele.get(i).heslo.equals(heslo)) {
                    kontrolaPrihlaseni = true;
                }
            }
        }
        return kontrolaPrihlaseni;

    }
}
