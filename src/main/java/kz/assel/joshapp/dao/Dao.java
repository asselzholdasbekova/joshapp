package kz.assel.joshapp.dao;

import kz.assel.joshapp.models.Product;
import kz.assel.joshapp.models.Profile;

public abstract class Dao {
    public Object index() {
        return null;
    }

    public Object show(int id) {
        return null;
    }

    public void save(Product product){}

    public void update(int id, Product product) {}

    public boolean check(Profile profile) {
        return false;
    }
}
