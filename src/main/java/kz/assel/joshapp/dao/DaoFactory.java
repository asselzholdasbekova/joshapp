package kz.assel.joshapp.dao;

public class DaoFactory {
    public static Dao createDao(String dao){
        Dao res = null;
        switch(dao){
            case "product":
                res = ProductDao.getInstance();
                break;
            case "profile":
                res = ProfileDao.getInstance();
        }
        return res;
    }
}
