package com.example.personaplayfront.Repo;

import com.example.personaplayfront.DAO.Dao;
import com.example.personaplayfront.Util.ConnectionUtil;

public abstract class DaoImpl<T> extends Dao<T> {

    protected static ConnectionUtil conn;

    public DaoImpl(Class<T> type, ConnectionUtil conn) {
        super(type);
        DaoImpl.conn = conn;
    }
    public DaoImpl(Class<T> type) {
        super(type);
        DaoImpl.conn = new ConnectionUtil();
    }
}
