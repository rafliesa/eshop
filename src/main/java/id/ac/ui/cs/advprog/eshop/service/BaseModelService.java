package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface BaseModelService<T> {
    public T create(T model);
    public List<T> findAll();
    public T findById(String id);
    public T edit(String id, T model);
    public boolean delete(String id);
}
