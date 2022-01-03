package com.oems.home.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> listOfAll();

    void create(T t);

    Optional<T> get(String target);

    void update(T t, String target);

    void delete(String target);

}
