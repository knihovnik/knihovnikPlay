package DAO.Impl;

import DAO.VypujckaDAO;
import models.Vypujcka;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

/**
 * Created by jaraskala on 18.12.14.
 */
public class VypujckaDAOImpl implements VypujckaDAO{

    @Override
    @Transactional
    public int create(Vypujcka vypujcka) {
        if (vypujcka==null) return -1;
        else vypujcka.save();
        return vypujcka.getId();
    }

    @Override
    @Transactional
    public Vypujcka read(int id) {
        return JPA.em().find(Vypujcka.class, id);
    }

    @Override
    @Transactional
    public boolean update(Vypujcka vypujcka) {
        try{
            JPA.em().persist(vypujcka);
            return true;
        }catch(Exception e){
            System.out.println("Chyba v update.");
            return false;
        }
    }

    @Override
    @Transactional
    public boolean delete(Vypujcka vypujcka) {
        try{
            JPA.em().remove(vypujcka);
            return true;
        }catch(Exception e){
            System.out.println("Chyba v delete.");
            return false;
        }
    }
}
