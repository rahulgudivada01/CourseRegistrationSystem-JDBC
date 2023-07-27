package com.digit.crsAPP.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.crsAPP.CRSApp;

public class ProfessorServices {
	private static PreparedStatement pstmt;
	private static ResultSet res;

	public static boolean login() {
		try {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Professor ID:");
			int Pid=sc.nextInt();
			System.out.println("Enter the password:");
			String password=sc.next();
			String sql = "select * from professor where pid=? and ppass=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setInt(1, Pid);
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

	private ResultSet resultSet;
	private PreparedStatement pstmt1;

	public void menu()
	{
		System.out.println("Enter Option: ");
		System.out.println("1. Check my Students\n"
				+ "2. Give Marks\n"
				+ "3. Change Password\n"
				+ "0. Exit\n");
		Scanner sc=new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			checkstudents();
			break;
		}
		case 2:{
			givemarks();
			break;
		}
		case 3:
		{
			changePassword();
			break;
		}
		case 0:{
			break;
		}

		}
	}

	public void changePassword() {
		try {
			String sql="update professor set ppass=? where pname=?and pid=?";
			int x;
			boolean checks=false;
			int count=0;
			pstmt=CRSApp.con.prepareStatement(sql);
			Scanner sc=new Scanner(System.in);
			while(checks==false)
			{
				System.out.println("Enter the Professor Name: ");
				String pname=sc.next();
				System.out.print("Enter Professor ID: ");
				int pid=sc.nextInt();
				pstmt.setString(2,pname);
				pstmt.setInt(3,pid);
				System.out.println("Enter the New Password: ");
				String pass=sc.next();
				System.out.println("Re-Enter your new Password: ");
				String pass1=sc.next();
				if(pass.equals(pass1))
				{
					pstmt.setString(1,pass);
					x=pstmt.executeUpdate();
					checks=true;
					if(x>0)
					{
						System.out.println("Data Updated");
						menu();
					}
					else
						System.out.println("Data not updated");
				}
				else if(count<3)
				{
					count++;
					System.out.println("Please Try Again");
					System.out.println();
				}
				else
				{
					System.out.println("Come again after some time.");
					menu();
				}
			}
		}catch(Exception e)
		{
			System.out.println("Exception Occured.");
		}

	}

	private void givemarks() {
		String sql="select sid,sname,email,user_name,marks from student where course=?";
		//step 4
		try {
			pstmt=CRSApp.con.prepareStatement(sql);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the course  ");
			pstmt.setString(1, sc.next());
			resultSet=pstmt.executeQuery();
			while(resultSet.next()==true)
			{
				System.out.println("Student ID: "+resultSet.getInt(1));
				System.out.println("Student Name: "+resultSet.getString(2));
				System.out.println("Email: "+resultSet.getString(3));
				System.out.println("Username: "+resultSet.getString(4));
				System.out.println("Marks: "+resultSet.getInt(5));

				System.out.println("----------------");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1="update student set marks=? where sid=?";
		try {
			pstmt1=CRSApp.con.prepareStatement(sql1);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the Marks you want to give: ");
			pstmt1.setInt(1, sc.nextInt());
			System.out.println("Enter the Student ID you want to give marks to: ");
			pstmt1.setInt(2,sc.nextInt());
			pstmt1.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		menu();


	}

	private void checkstudents() {
		String sql="select sid,sname,email,user_name,marks from student where course=?";
		try {
			pstmt=CRSApp.con.prepareStatement(sql);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the course  ");
			pstmt.setString(1, sc.next());
			resultSet=pstmt.executeQuery();
			while(resultSet.next()==true)
			{
				System.out.println("Student ID: "+resultSet.getInt(1));
				System.out.println("Student Name: "+resultSet.getString(2));
				System.out.println("Email: "+resultSet.getString(3));
				System.out.println("Username: "+resultSet.getString(4));
				System.out.println("Marks: "+resultSet.getInt(5));

				System.out.println("----------------");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();
	}
}
