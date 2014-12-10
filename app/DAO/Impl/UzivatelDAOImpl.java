package DAO.Impl;

import DAO.UzivatelDAO;

import models.Uzivatel;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

/**
 * Created by Kuba on 10.12.2014.
 */
public class UzivatelDAOImpl implements UzivatelDAO {

    @Override
    @Transactional
    public int create(Uzivatel uzivatel) {
        if (uzivatel!=null) uzivatel.save();
        else return -1;
        return uzivatel.getId();
    }

    @Override
    @Transactional
    public Uzivatel read(int id) {
        Uzivatel kniha = JPA.em().find(Uzivatel.class, id);
        return kniha;
    }

    @Override
    @Transactional
    public boolean update(Uzivatel uzivatel) {
        try{
            JPA.em().persist(uzivatel);
            return true;
        }catch(Exception e){
            System.out.println("Chyba v update.");
            return false;
        }
    }

    @Override
    @Transactional
    public boolean delete(Uzivatel uzivatel) {
        try{
            JPA.em().remove(uzivatel);
            return true;
        }catch(Exception e){
            System.out.println("Chyba v delete.");
            return false;
        }
    }

}
