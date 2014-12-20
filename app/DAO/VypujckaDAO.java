package DAO;

import models.Vypujcka;

/**
 * Created by Kuba on 10.12.2014.
 */
public interface VypujckaDAO {

    int create(Vypujcka vypujcka);

    Vypujcka read(int id);

    boolean update(Vypujcka vypujcka);

    boolean delete(Vypujcka vypujcka);


}
