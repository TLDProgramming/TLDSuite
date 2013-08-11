package com.github.thelonedevil.TLDCommonlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBase {
	private Lib plugin;

	public DataBase(Lib instance) {
		this.plugin = instance;
	}

	Connection connect(String path) throws SQLException, ClassNotFoundException, NullPointerException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + path);
		return connection;
	}

	/**
	 * 
	 * @param connection
	 *            , the connection to a database, {@link #connect(String)}
	 * @param timeout
	 *            , the timeout for the query
	 *            {@link Statement#setQueryTimeout(int)}
	 * @return Returns a {@link Statement}
	 * @throws SQLException
	 */
	public Statement state(Connection connection, int timeout) throws SQLException {
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(timeout); // set timeout to 30 sec.
		return statement;
	}

	/**
	 * 
	 * @param connection
	 *            , the connection to a database, {@link #connect(String)}
	 * @return Returns a {@link Statement}
	 * @throws SQLException
	 */
	public Statement state(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30); // set timeout to 30 sec.
		return statement;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column_name1
	 * @param data_type1
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table, String column_name1, String data_type1) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table, String column_name1, String data_type1, String column_name2, String data_type2) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @param column_name3
	 * @param data_type3
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @param column_name3
	 * @param data_type3
	 * @param column_name4
	 * @param data_type4
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3, String column_name4,
			String data_type4) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3 + ", "
				+ column_name4 + " " + data_type4 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @param column_name3
	 * @param data_type3
	 * @param column_name4
	 * @param data_type4
	 * @param column_name5
	 * @param data_type5
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3, String column_name4,
			String data_type4, String column_name5, String data_type5) throws SQLException {
		statement.executeUpdate("create table if not exists " + table + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3 + ", "
				+ column_name4 + " " + data_type4 + ", " + column_name5 + " " + data_type5 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet rs(Statement statement, String query) throws SQLException {
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getStrings(Statement statement, String table, String column) throws SQLException {
		String query = "SELECT " + column + " FROM" + table;
		ResultSet rs1 = rs(statement, query);
		List<String> object = new ArrayList<String>();
		while (rs1.next()) {
			object.add(rs1.getString(column));
		}
		rs1.close();
		return object;

	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, List<String>> getStrings(Statement statement, String table, String column1, String column2) throws SQLException {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String query = "SELECT " + column1 + ", " + column2 + " FROM " + table;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getString(column1));
			list2.add(rs1.getString(column2));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, List<String>> getStrings(Statement statement, String table, String column1, String column2, String column3) throws SQLException {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + " FROM " + table;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getString(column1));
			list2.add(rs1.getString(column2));
			list3.add(rs1.getString(column3));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @param column4
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, List<String>> getStrings(Statement statement, String table, String column1, String column2, String column3, String column4) throws SQLException {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + ", " + column4 + " FROM " + table;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getString(column1));
			list2.add(rs1.getString(column2));
			list3.add(rs1.getString(column3));
			list4.add(rs1.getString(column4));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		map.put(column4, list4);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @param column4
	 * @param column5
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, List<String>> getStrings(Statement statement, String table, String column1, String column2, String column3, String column4, String column5) throws SQLException {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + ", " + column4 + ", " + column5 + " FROM " + table;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new ArrayList<String>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getString(column1));
			list2.add(rs1.getString(column2));
			list3.add(rs1.getString(column3));
			list4.add(rs1.getString(column4));
			list5.add(rs1.getString(column5));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		map.put(column4, list4);
		map.put(column5, list5);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public static List<Integer> getInts(Statement statement, String table, String column) throws SQLException {
		List<Integer> object = new ArrayList<Integer>();
		String query = "SELECT " + column + " from " + table;
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			object.add(rs1.getInt(column));
		}
		rs1.close();
		return object;

	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, List<Integer>> getInts(Statement statement, String table, String column1, String column2) throws SQLException {
		HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		String query = "SELECT " + column1 + ", " + column2 + " FROM " + table;
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getInt(column1));
			list2.add(rs1.getInt(column2));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, List<Integer>> getInts(Statement statement, String table, String column1, String column2, String column3) throws SQLException {
		HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + " FROM " + table;
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getInt(column1));
			list2.add(rs1.getInt(column2));
			list3.add(rs1.getInt(column3));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @param column4
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, List<Integer>> getInts(Statement statement, String table, String column1, String column2, String column3, String column4) throws SQLException {
		HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + ", " + column4 + " FROM " + table;
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		List<Integer> list4 = new ArrayList<Integer>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getInt(column1));
			list2.add(rs1.getInt(column2));
			list3.add(rs1.getInt(column3));
			list4.add(rs1.getInt(column4));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		map.put(column4, list4);
		return map;
	}
	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @param column4
	 * @param column5
	 * @return
	 * @throws SQLException
	 */
	public static HashMap<String, List<Integer>> getInts(Statement statement, String table, String column1, String column2, String column3, String column4, String column5) throws SQLException {
		HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + ", " + column4 + ", " + column5 + " FROM " + table;
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		List<Integer> list4 = new ArrayList<Integer>();
		List<Integer> list5 = new ArrayList<Integer>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getInt(column1));
			list2.add(rs1.getInt(column2));
			list3.add(rs1.getInt(column3));
			list4.add(rs1.getInt(column4));
			list5.add(rs1.getInt(column5));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		map.put(column4, list4);
		map.put(column5, list5);
		return map;
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
