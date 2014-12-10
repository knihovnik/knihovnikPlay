package DAO;

import models.Kniha;
import play.db.ebean.Model;

/**
 * Created by Kuba on 10.12.2014.
 */
public interface KnihaDAO {

    public int create(Kniha kniha);

    public Kniha read(int id);

    public boolean update();

    public boolean delete();

}
