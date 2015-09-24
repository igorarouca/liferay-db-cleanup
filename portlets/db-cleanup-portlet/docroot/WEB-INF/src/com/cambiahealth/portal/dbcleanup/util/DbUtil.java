package com.cambiahealth.portal.dbcleanup.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
public final class DbUtil {

	public static void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public static void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	public static void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}

	public static void closeQuietly(Connection conn) {
		try {
			close(conn);
		}
		catch (SQLException ignored) { // NOPMD
		}
	}

	public static void closeQuietly(
		Connection conn, Statement stmt, ResultSet rs) {

		try {
			closeQuietly(rs);
		}
		finally {
			try {
				closeQuietly(stmt);
			} finally {
				closeQuietly(conn);
			}
		}

	}

	public static void closeQuietly(ResultSet rs) {
		try {
			close(rs);
		}
		catch (SQLException ignored) { // NOPMD
		}
	}

	public static void closeQuietly(Statement stmt) {
		try {
			close(stmt);
		}
		catch (SQLException ignored) { // NOPMD
		}
	}

	private DbUtil() {
	}

}