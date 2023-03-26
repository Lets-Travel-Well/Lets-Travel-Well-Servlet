package com.ssafy.ltw.global.util.db;

public class DBLocalProperties implements DBProperties{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ltw_local?serverTimezone=UTC";
    private static final String DB_ID = "ssafy";
    private static final String DB_PWD = "ssafy";

    @Override
    public String getDriver() {
        return DRIVER;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public String getDBId() {
        return DB_ID;
    }

    @Override
    public String getDBPwd() {
        return DB_PWD;
    }
}
