package com.ssafy.ltw.global.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static DBUtil instance = new DBUtil();
	private DBProperties dbProperties = null;
	private DBUtil() {
		// 개발 환경
		dbProperties = new DBLocalProperties();

		// 테스트 환경
		//dbProperties = new DBTestProperties();
		try {
			Class.forName(dbProperties.getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DBUtil getInstance() {
		return instance;
	}


	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbProperties.getURL(), dbProperties.getDBId(), dbProperties.getDBPwd());
	}

	public void close(AutoCloseable... autoCloseables) {
		for(AutoCloseable ac : autoCloseables) {
			if(ac != null) {
				try {
					ac.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
