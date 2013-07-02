package com.github.thelonedevil.TLDCommonlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	private Lib lib;

	public DataBase(Lib instance) {
		this.lib = instance;
	}

	Connection connect(String path) throws SQLException, ClassNotFoundException, NullPointerException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + path);
		return connection;
	}

	public Statement state(Connection connection, int timeout) throws SQLException {
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(timeout); // set timeout to 30 sec.
		return statement;
	}

	public static ResultSet rs(Statement statement, String query) throws SQLException {
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}

	void createTable(Statement statement, String table, String column_name1, String data_type1) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ")");
	}

	void createTable(Statement statement, String table, String column_name1, String data_type1, String column_name2, String data_type2) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ")");
	}

	void createTable(Statement statement, String table, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3 + ")");
	}

	void createTable(Statement statement, String table, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3, String column_name4,
			String data_type4) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3 + ", "
				+ column_name4 + " " + data_type4 + ")");
	}

	/*
	 * statement.executeUpdate("drop table if exists person");
	 * statement.executeUpdate
	 * ("create table"+table+" (id integer, name string)");
	 * statement.executeUpdate("insert into person values(1, 'leo')");
	 * statement.executeUpdate("insert into person values(2, 'yui')"); ResultSet
	 * rs = statement.executeQuery("select * from person"); while(rs.next()) {
	 * // read the result set System.out.println("name = " +
	 * rs.getString("name")); System.out.println("id = " + rs.getInt("id")); } }
	 * 
	 * finally { try { if(connection != null) connection.close(); }
	 * catch(SQLException e) { // connection close failed.
	 * System.err.println(e); } } return null; }
	 */

}
