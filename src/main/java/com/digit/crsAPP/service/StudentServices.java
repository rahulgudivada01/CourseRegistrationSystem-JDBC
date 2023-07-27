package com.digit.crsAPP.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.crsAPP.CRSApp;

public class StudentServices {
	private static PreparedStatement pstmt;
	private static ResultSet res;
	public static boolean login() {
		try {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Student Username:");
			String username=sc.next();
			System.out.println("Enter the password:");
			String password=sc.next();
			String sql = "select * from student where user_name=? and spass=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setString(1, username);
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

	private Statement stmt;
	private ResultSet resultSet;
	private PreparedStatement pstmt1;
	private PreparedStatement pstmt2;
		public void menu()
		{
			System.out.println("Enter Option: ");
			System.out.println("1. Choose my Course\n"
					+ "2. Check my Marks\n"
					+ "3. Change my Passoword\n"
					+ "0. Exit\n");
			Scanner sc=new Scanner(System.in);
			int n = sc.nextInt();
			switch (n) {
			case 1: {
				MyCourse();
				break;
			}
			case 2:{
				CheckMarks();
				break;
			}
			case 3:{
				changePassword();
				break;
			}
			case 0:{
				break;
			}
			}
		}

		public void changePassword() {
			try
			{
				String sql="update student set spass=? where user_name=?";
				int x;
				boolean checks=false;
				int count=0;
				pstmt=CRSApp.con.prepareStatement(sql);
				Scanner sc=new Scanner(System.in);
				while(checks==false)
				{
					System.out.println("Enter the Student UserName: ");
					String uname=sc.next();
					pstmt.setString(2,uname);
					System.out.println("Enter the New Password: ");
					String pass=sc.next();
					System.out.println("Re-Enter your new Passowrd: ");
					String pass1=sc.next();
					if(pass.equals(pass1))
					{
						pstmt.setString(1,pass);
						x=pstmt.executeUpdate();
						checks=true;
						if(x>0)
						{
							System.out.println("Data Updated");
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

		public void CheckMarks() {
			String sql="select sid,sname,email,user_name,course,professor,marks from student where sid=?";
			//step 4
			try {
				pstmt=CRSApp.con.prepareStatement(sql);
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the ID: ");
				pstmt.setInt(1, sc.nextInt());
				resultSet=pstmt.executeQuery();
				while(resultSet.next()==true)
				{
					System.out.println("Student ID: "+resultSet.getInt(1));
					System.out.println("Student Name: "+resultSet.getString(2));
					System.out.println("Email: "+resultSet.getString(3));
					System.out.println("Username: "+resultSet.getString(4));
					System.out.println("Course: "+resultSet.getString(5));
					System.out.println("Professor: "+resultSet.getString(6));
					System.out.println("Marks: "+resultSet.getInt(7));					
					System.out.println("----------------");
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menu();

		}

		public void MyCourse() {
			System.out.println("Courses Available are: ");
			System.out.println();

			String sql1=" select c.cid,c.cname,p.pname from course c, professor p where c.cname=p.course";
			try {
				stmt=CRSApp.con.createStatement();
				//System.out.println("Enter the Course Name:");
				//Scanner sc=new Scanner(System.in);
				//String cname=sc.next();
				//stmt.setString(1, cname);
				resultSet=pstmt.executeQuery(sql1);
				System.out.print("Course ID:  ");
				System.out.print("Course Name:  ");
				System.out.println("Professor Name:");
				while(resultSet.next()==true)
				{	System.out.print(resultSet.getInt(1)+"           ");
					System.out.print(resultSet.getString(2)+ "           ");
					System.out.println(resultSet.getString(3)+"          ");
				}
				System.out.println();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println();
			Scanner sc=new Scanner(System.in);
			String sql3="update student set course=? where sid=?";
			String sql4="update student set professor=? where sid=?";
			//step 4
			try {
				pstmt1=CRSApp.con.prepareStatement(sql3);
				pstmt2=CRSApp.con.prepareStatement(sql4);

				System.out.println("Please Select  course: ");
				String course=sc.next();
				System.out.println("Please Select Professor: ");
				String professor=sc.next();
				pstmt1.setString(1, course);
				pstmt2.setString(1, professor);
				System.out.println("Enter the Student ID: ");
				int id=sc.nextInt();
				pstmt1.setInt(2,id);
				pstmt2.setInt(2,id);
				int y=pstmt1.executeUpdate();
				int z=pstmt2.executeUpdate();
				if(y>0&&z>0)
				{
					System.out.println("Course Added");
					System.out.println();
					menu();
				}
				else
				{
					System.out.println("Course not Added");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
