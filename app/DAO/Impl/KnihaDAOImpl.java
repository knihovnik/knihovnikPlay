package DAO.Impl;

import DAO.KnihaDAO;
import com.avaje.ebean.annotation.Transactional;
import models.Kniha;
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
    @Transactional
    public Kniha read(int id) {
        Kniha kniha = JPA.em().find(Kniha.class, id);
        return kniha;
    }

    @Override
    @Transactional
    public boolean update(Kniha kniha) {
        try{
            JPA.em().persist(kniha);
            return true;
        }catch(Exception e){
            System.out.println("Chyba v update.");
            return false;
        }
    }

    @Override
    @Transactional
    public boolean delete(Kniha kniha) {
        try{
            JPA.em().remove(kniha);
            return true;
        }catch(Exception e){
            System.out.println("Chyba v delete.");
            return false;
        }
    }

}
