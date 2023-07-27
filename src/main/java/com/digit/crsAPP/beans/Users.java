package com.digit.crsAPP.beans;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.crsAPP.CRSApp;
public class Users {
	static String user_name;
	static String password;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	public Users(String user_name, String password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	public static boolean login() {
		try {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the user_name:");
			user_name=sc.next();
			System.out.println("Enter the password:");
			password=sc.next();
			String sql = "select * from users where user_name=? and password=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			if(res!=null) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
