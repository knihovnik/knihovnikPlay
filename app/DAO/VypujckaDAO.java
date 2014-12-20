package DAO;

import models.Vypujcka;

/**
 * Created by Kuba on 10.12.2014.
 */
public interface VypujckaDAO {

    public int create(Vypujcka vypujcka);

    public Vypujcka read(int id);

    public boolean update(Vypujcka vypujcka);

    public boolean delete(Vypujcka vypujcka);


}
