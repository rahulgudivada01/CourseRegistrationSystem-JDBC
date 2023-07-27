package com.digit.crsAPP.service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.crsAPP.CRSApp;
import com.digit.crsAPP.beans.Course;
import com.digit.crsAPP.beans.Professor;
import com.digit.crsAPP.beans.Student;
public class AdminServices 
{

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private PreparedStatement pstmt1;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private ResultSet resultSet2;

	public void menu() {
		// TODO Auto-generated method stub
		System.out.println("Select Option:");
		System.out.println("1. Add course\n"
				+ "2. Add Student\n"
				+ "3. Add Professor\n"
				+ "4. Remove Course\n"
				+ "5. Remove Professor\n"
				+ "6. Remove Student\n"
				+ "7. View All Students\n"
				+ "8. View All Courses\n"
				+ "9. View All Professors\n"
				+ "0. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			addCourse();
			break;
		}
		case 2:{
			addstudent();
			break;
		}
		case 3: {
			addprofessor();
			break;
		}
		case 4:{
			removecourses();
			break;
		}
		case 5:{
			removeprofessors();
			break;
		}
		case 6:{
			removestudent();
		}
		case 7:{
			viewallstudents();
			break;
		}
		case 8: {
			viewallcourses();
			break;
		}
		case 9:{
			viewallprofessors();
		}
		case 0:{
			break;
		}
		}
	}

	public void removestudent() {
		String sql="delete from student where sid=?";
		try {
			pstmt=CRSApp.con.prepareStatement(sql);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the Student ID to be deleted: ");
			pstmt.setInt(1,sc.nextInt());
			pstmt.executeUpdate();
			System.out.println("Student Deleted Successfully.");
			System.out.println();
			menu();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void viewallstudents() {
		String sql="select sid,sname,user_name,course,professor,marks from student";
		try {
			stmt=CRSApp.con.createStatement();
			resultSet=stmt.executeQuery(sql);
			while(resultSet.next()==true)
			{
				System.out.println("Student ID: "+resultSet.getInt(1));
				System.out.println("Student Name: "+resultSet.getString(2));
				System.out.println("Email: "+resultSet.getString(3));
				System.out.println("Course: "+resultSet.getString(4));
				System.out.println("Professor: "+resultSet.getString(5));
				System.out.println("Marks: "+resultSet.getInt(6));
				System.out.println("----------------");
			}
			System.out.println();
			menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addstudent() {
		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Student ID: ");
			int sid=sc.nextInt();
			System.out.println("Enter Student Name: ");
			String sname=sc.next();
			System.out.println("Enter Password: ");
			String pass=sc.next();
			System.out.println("Enter Email: ");
			String email=sc.next();
			System.out.println("Enter username: ");
			String username=sc.next();
			Student s=new Student(sid,sname,email,username);
			s.setSpass(pass);
			String sql = "insert into student(sid,sname,spass,email,user_name) values(?,?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setInt(1, s.getSid());
			pstmt.setString(2, s.getSname());
			pstmt.setString(3,s.getSpass());
			pstmt.setString(4, s.getEmail());
			pstmt.setString(5,s.getUser_name());
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Student Added is :"+s.getSname());
				System.out.println();
				menu();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void viewallprofessors() {
		String sql="select pid,pname,exp,course from professor";
		try {
			stmt=CRSApp.con.createStatement();
			resultSet=stmt.executeQuery(sql);
			while(resultSet.next()==true)
			{
				System.out.println("Professor ID: "+resultSet.getInt(1));
				System.out.println("Professor Name: "+resultSet.getString(2));
				System.out.println("Experience: "+resultSet.getInt(3));
				System.out.println("Course: "+resultSet.getString(4));
				System.out.println("----------------");
			}
			System.out.println();
			menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void removeprofessors() {
		String sql="delete from professor where pid=?";
		try {
			pstmt=CRSApp.con.prepareStatement(sql);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the Professor ID to be deleted: ");
			pstmt.setInt(1,sc.nextInt());
			int x=pstmt.executeUpdate();
			System.out.println();
			System.out.println("Professor Deleted Successfully.");
			menu();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addprofessor() {

		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Professor ID: ");
			int pid=sc.nextInt();
			System.out.println("Enter Professor Name: ");
			String pname=sc.next();
			System.out.println("Enter Password: ");
			String ppass=sc.next();
			System.out.println("Enter Experience: ");
			int exp=sc.nextInt();
			Professor p=new Professor(pid,pname,exp);
			p.setPpass(ppass);
			String sql = "insert into professor(pid,pname,ppass,exp) values(?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setInt(1, p.getPid());
			pstmt.setString(2, p.getPname());
			pstmt.setString(3,p.getPpass());
			pstmt.setInt(4, p.getExp());

			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Professor Added is :"+p.getPname());
				System.out.println();
			}
			System.out.println("Courses Available are: ");
			System.out.println();

			String sql1="select* from course";
			try {
				stmt=CRSApp.con.createStatement();
				resultSet=stmt.executeQuery(sql1);
				while(resultSet.next()==true)
				{
					System.out.print(resultSet.getInt(1)+" ");
					System.out.print(resultSet.getString(2)+ " ");
					System.out.println();
				}
				System.out.println();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println();
			String sql4="update professor set cid=? where pid=?";
			String sql3="update professor set course=? where pid=?";
			//step 4
			pstmt1=CRSApp.con.prepareStatement(sql3);
			pstmt2=CRSApp.con.prepareStatement(sql4);
			System.out.println("Please Select the course: ");
			pstmt1.setString(1, sc.next());
			System.out.println("Please select the course ID: ");
			pstmt2.setString(1, sc.next());
			System.out.println("Enter the Professor ID: ");
			int n=sc.nextInt();
			pstmt1.setInt(2,n);
			pstmt2.setInt(2,n);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removecourses() {
		String sql="delete from course where cid=?";
		try {
			pstmt=CRSApp.con.prepareStatement(sql);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the Course ID to be deleted: ");
			pstmt.setInt(1,sc.nextInt());
			pstmt.executeUpdate();
			System.out.println();
			System.out.println("Course Deleted.");
			menu();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void viewallcourses() {
		String sql="select* from course";
		try {
			stmt=CRSApp.con.createStatement();
			resultSet=stmt.executeQuery(sql);
			while(resultSet.next()==true)
			{
				System.out.println("Course ID: "+resultSet.getInt(1));
				System.out.println("Course Name: "+resultSet.getString(2));
				System.out.println("Fees: "+resultSet.getInt(3));
				System.out.println("Duration Month: "+resultSet.getInt(4));
				System.out.println("----------------");
			}
			menu();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCourse() {
		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Course ID: ");
			int cid=sc.nextInt();
			System.out.println("Enter Course Name: ");
			String cname=sc.next();
			System.out.println("Enter Fees: ");
			int fees=sc.nextInt();
			System.out.println("Enter Duration Months: ");
			int dur_months=sc.nextInt();
			Course c = new Course(cid,cname,fees,dur_months);
			String sql = "insert into course values(?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setInt(1, c.getCid());
			pstmt.setString(2, c.getCname());
			pstmt.setInt(3, c.getFees());
			pstmt.setInt(4, c.getDur_months());

			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Course Added is :"+c.getCname());
				menu();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
