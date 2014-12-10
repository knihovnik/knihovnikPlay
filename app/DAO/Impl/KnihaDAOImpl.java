package DAO.Impl;

import DAO.KnihaDAO;
import com.avaje.ebean.annotation.Transactional;
import models.Kniha;
import play.data.Form;
import play.db.jpa.JPA;

/**
 * Created by Kuba on 10.12.2014.
 */
public class KnihaDAOImpl implements KnihaDAO {

    @Override
    @Transactional
    public int create(Kniha kniha) {
        if (kniha!=null) kniha.save();
        else return -1;
        return kniha.getId();
    }

    @Override
    public Kniha read(int id) {
        Kniha kniha = JPA.em().find(Kniha.class, id);
        return kniha;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

}
