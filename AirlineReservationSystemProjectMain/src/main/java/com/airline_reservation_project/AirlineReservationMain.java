/**
 **************************************CORE JAVA PROJECT*************************************** 
 *
 *******************************CSR CAPGEMINI TRAINING PROJECT**********************************
 * 
 ******************************EDUBRIDGE INDIA PRIVATE LIMITED**********************************
 *       
 *************************PROJECT TITLE: AIRLINE RESERVATION SYSTEM*****************************
 *                           
 **********************UNDER THE GUIDENCE OF TRAINER MRS.INDRAKA MALLI**************************                          
 *
 *                                                                         @DONE BY LOKESHWARI S
 * AirlineReservationMain class is having two major operations
 *this will decides who is going to perform the operation 
 *1.admin
  2.user
  
   case 1:
 *If it is admin choice means it gives two choice for admin
 *1.Admin Registration-->it allows to create new  registration for admin 
 *2.Admin Login-->it allows to login the admin account
 *
 *case 2:
 *If it is user choice means it gives two choice for user
 *1.User Registration-->it allows to registration for user
 *2.User Login-->it allows to login the user account
 */
package com.airline_reservation_project;
import java.util.Scanner;
//main class for airline reservation
public class AirlineReservationMain 
{
	//get input from user
	static Scanner sc=new Scanner(System.in);
	//main method
	public static void main(String[] args) 
	{
	while(true)
		{

			System.out.println("******WELCOME TO AIR INDIA AIRLINE RESERVATION******");
			System.out.println("Enter Your Choice:");
			System.out.println("1.ADMIN'S PROCESS");
			System.out.println("2.USER'S PROCESS");
			System.out.println("3.EXIT");
			//getting choice from user
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:System.out.println("You Are Choose ADMIN'S PROCESS");
				   while(true)
				  {
					System.out.println("Enter Your Choice:");
					System.out.println("1.ADMIN LOGIN");
					System.out.println("2.EXIT");
					int ch=sc.nextInt();
					switch(ch)
					{
					
					case 1:System.out.println("You Are Choose ADMIN LOGIN Process");
					//perform the login operation
					ReservationProcess.adminLogin();
					break;
					case 2:
					break;
					
					default:System.out.println("INVALID INPUT");
					}
					//for repetition purpose
					System.out.println("Do You Want to Continue the ADMIN LOGIN yes/no");
					//getting input
					String input=sc.next();
					if(input.equalsIgnoreCase("no"))//comparing input with the condition
					{
						break;
					}
				   }
                   break;
			
			
			case 2:System.out.println("You Are Choose ADMIN'S PROCESS");
				   while(true)
				   {
					System.out.println("Enter Your Choice:");
					System.out.println("1.USER REGISTRATION");
					System.out.println("2.USER LOGIN");
					System.out.println("3.EXIT");
					int ch=sc.nextInt();
					switch(ch)
					{
					case 1:System.out.println("You Are Choose USER REGISTRATION Process");
					//perform the registration operation
					ReservationProcess.passengerRegistraion();   
					break;
					case 2:System.out.println("You Are Choose USER LOGIN Process");
					//perform the login operation
					ReservationProcess.passengerLogin();
				    break;
		            case 3:
		            break;
					default:System.out.println("INVALID INPUT");
					}
					//for repetition purpose
					System.out.println("Do You Want To Continue the USER REGISTRATION or USER LOGIN Process yes/no");
					//getting input
					String input=sc.next();
					if(input.equalsIgnoreCase("no"))//comparing input with the condition
					{
						break;
					}
					
					}
                 break;
                 
			    case 3://this case for exit process
				break;

				default:System.out.println("INVALID INPUT");
			}//for repetition purpose
			System.out.println("Do You Want To Continue the ADMIN or USER  Process yes/no");
			//getting input
			String input=sc.next();
			if(input.equalsIgnoreCase("no"))//comparing input with the condition
			{
				break;
			}
			
			}
			}}
		
	
