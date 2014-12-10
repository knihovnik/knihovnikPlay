package DAO;

import models.Uzivatel;

/**
 * Created by Kuba on 10.12.2014.
 */
public interface UzivatelDAO {

    public int create(Uzivatel uzivatel);

    public Uzivatel read(int id);

    public boolean update(Uzivatel uzivatel);

    public boolean delete(Uzivatel uzivatel);

}
