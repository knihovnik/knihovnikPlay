package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Kuba on 12.11.2014.
 */

@Entity
public class Kniha extends Model {

    @Id
    public String id;

    public String nazev;

}
