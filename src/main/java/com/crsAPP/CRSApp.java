package com.crsAPP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import com.digit.crsAPP.beans.Users;
import com.digit.crsAPP.service.AdminServices;
import com.digit.crsAPP.service.ProfessorServices;
import com.digit.crsAPP.service.StudentServices;

public class CRSApp {
	public static Connection con;
	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		String url  = "jdbc:mysql://localhost:3306/crs";
		String user = "root";
		String pwd  = "Rahulg01*";
		// Step:2
		con = DriverManager.getConnection(url, user, pwd);
		boolean check=true;
		do {
			System.out.println("Select the Type of User:");
			System.out.println("1. Admin\n"
					+ "2. Professor\n"
					+ "3. Student\n"
					+ "4. Exit\n");
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			switch (n) {
			case 1: {
				int c=0;
				boolean checks=false;
				while(checks==false)
				{

					boolean b = Users.login();
					if(b==true) 
					{
						checks=true;
						System.out.println("Admin Login Success");
						AdminServices adsrv = new AdminServices();
						adsrv.menu();
					}
					else if(c<3)
					{
						System.out.println("Login Failed. Please Try Again.");
						c++;
					}
					else
					{
						System.out.println("Please try again after sometime.");
					}
				}
				break;
			}
			case 2:{
				int c=0;
				boolean checks=false;
				while(checks==false)
				{
					boolean b = ProfessorServices.login();
					if(b==true) 
					{
						checks=true;
						System.out.println("Professor Login Success");
						ProfessorServices ps = new ProfessorServices();
						ps.menu();
					}
					else if(c<3)
					{
						System.out.println("Login Failed. Please Try Again.");
						c++;
					}
					else
					{
						System.out.println("Please try again after sometime.");
					}
				}
				break;
				
			}
			case 3:{
				int c=0;
				boolean checks=false;
				while(checks==false)
				{
					boolean b=StudentServices.login();
					if(b==true) 
					{
						checks=true;
						System.out.println("Student Login Success");
						StudentServices ss = new StudentServices();
						ss.menu();
					}
					else if(c<3)
					{
						System.out.println("Login Failed. Please Try Again.");
						c++;
					}
					else
					{
						System.out.println("Please try again after sometime.");
					}
				}
				break;
			}
			case 0:{
				break;
			}
			}

		}while(check);
	}
}

