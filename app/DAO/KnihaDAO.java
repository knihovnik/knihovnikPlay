package DAO;

import models.Kniha;


/**
 * Created by Kuba on 10.12.2014.
 */
public interface KnihaDAO {

    int create(Kniha kniha);

    Kniha read(int id);

    boolean update(Kniha kniha);

    boolean delete(Kniha kniha);

}
