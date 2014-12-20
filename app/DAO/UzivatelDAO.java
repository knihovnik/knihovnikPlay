package DAO;

import models.Uzivatel;

/**
 * Created by Kuba on 10.12.2014.
 */
public interface UzivatelDAO {

    int create(Uzivatel uzivatel);

    Uzivatel read(int id);

    boolean update(Uzivatel uzivatel);

    boolean delete(Uzivatel uzivatel);

}
