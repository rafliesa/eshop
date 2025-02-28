package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface BaseModelrepository<T> {
    public T create(T product);
    public Iterator<T> findAll();
    public T findById(String id);
    public T edit(String id, T newModel);
    public boolean delete(String id);
}
