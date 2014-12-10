package DAO;

import models.Uzivatel;

/**
 * Created by Kuba on 10.12.2014.
 */
public interface UzivatelDAO {

    public int create();

    public Uzivatel read();

    public boolean update();

    public boolean delete();

}
