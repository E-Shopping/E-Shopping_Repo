package org.model.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtility {

	private String oracleJdbcDriverName = "oracle.jdbc.driver.OracleDriver";
	private String oracleJdbcUrl = "jdbc:oracle:thin:@//Lenovo:1521/orcl";
	private String oracleJdbcUserName = "scott";
	private String oracleJdbcPassword = "tiger";
	private static Connection connection = null;

	public Connection getconnection() {
		try {
			Class.forName(oracleJdbcDriverName);
			connection = DriverManager.getConnection(oracleJdbcUrl, oracleJdbcUserName, oracleJdbcPassword);
			System.out.println("database created " + connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
			System.out.println("dbcloe method");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean testDataBaseConnection() {
		connection = getconnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select *  from dual");
			if (null != resultSet) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return false;
	}
}
