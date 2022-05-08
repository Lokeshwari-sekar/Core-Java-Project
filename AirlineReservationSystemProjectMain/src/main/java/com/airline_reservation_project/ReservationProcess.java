/**
 * This class perform the operations are
 * 1.Passenger registration
 * 2.Admin Registration
 * 3.passanger login
 * 4.Admin login
 * 5.seat reservation
 * 6.seat cancellation
 */

package com.airline_reservation_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Scanner;

public class ReservationProcess
{
	//these are the static variables
	private static Connection con;
	private static Scanner sc=new Scanner(System.in);
	private static PreparedStatement pst;
	private static ResultSet rst;
	private static String email;
	private static String username;
	private static String password;
	private static int seatno;
	private static int ch;
	private static int t;
	private static int s;
	private static String tripname;
	private static String depaturetime;
	private static String arrivaltime;
	private static String seattype;
	private static long ticketamount;
	private static long inventoryfare;
	private static String tickettype;
	private static long totalamount;
	private static long accno;
	private static int pinno;
	                                                                                                                                                                                                         
//-----------------------------------------------------------------------------------------------------------------
	/*
	 * In this admin login process the back end we created one user name and password for the admin
	 * 
	 * That user name and pass word can be used to login admin login module
	 * 
	 * If the login process get successful means admin can perform display operations
	 * 
	 * 1.display single record -> it gives information about single seat number
	 * 
	 * 2.display seatreserved-> it gives result about how many seats are reserved
	 * 
	 */
	
	//LOGIN PROCESS FOR ADMIN
	public static void adminLogin() 
	{
		try 
		{
			con=DbConnect.getconnection();
			//checking user name and pass word
			while(true)
			{
				System.out.println("Enter The UserName");
				username=sc.next();
				System.out.println("Enter The PassWord");
				password=sc.next();

				String checkusername="select* from admin_registration where ad_name=?";
				pst=con.prepareStatement(checkusername);
				pst.setString(1,username);
				rst=pst.executeQuery();
				if(rst.next())
				{
					String checkpass="select * from admin_registration where ad_password =? ";
					pst=con.prepareStatement(checkpass);
					pst.setString(1,password);
					rst=pst.executeQuery();
					if(rst.next())
					{
						
							System.out.println("Login successfull");
							while(true)
							{
							System.out.println("1.diplay single record");
                            System.out.println("2.display seat reserved");
							//choice to do the seat reservation or cancellation process
							System.out.println("Enter Your choice...");
							int ch1=sc.nextInt();
							switch(ch1)
							{
							case 1:System.out.println("Your choosen display single records process");
							//perform the seat reservation operation
							ReservationProcess.displayRervation();
							break;
							case 2:System.out.println("Your choosen seat reserved process");
							//perform the seat canceling operation
							ReservationProcess.reservationState();
							break;
							default: System.out.println("Invalid Choice");
							
							}
							System.out.println("Do you want to continue the display process yes/no");
							String in=sc.next();
							if(in.equalsIgnoreCase("no"))
							{
								break;
							}
							
						}
					}
					else
					{
						System.out.println("Invalid PassWord");
						break;
					}
				}
				else
				{
					System.out.println("Invalid User Name");

				}
				break;

			}}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * User registration or passenger registration
	 * 
	 * Registration Modules-> passangerRegistraion() ->It takes informations from passenger and saves in a database.
	 *   
	 *   ->first it checks the registration is already done by the same mail id or not.
	 *    if it is not then ask further information regarding the registration...
	 *    
	 *    ->User name and password...while setting password confirmation also done.
	 *    
	 *    
	 *                          
	 *                  
	 */
	
	//USER REGISTRATION PROCESS
	public static void passengerRegistraion() 
	{ 
		try 
		{
			//Getting connection
			con=DbConnect.getconnection();
			//fetching email id from user registration
			String selectemail="select * from user_registration where pass_emailid=?";
			pst=con.prepareStatement(selectemail);
			System.out.println("Enter Your Email id");
			email=sc.next();
			pst.setString(1, email);
			rst=pst.executeQuery();
			if(!rst.next())
			{
				System.out.println("Enter Your User Name");
				username=sc.next();
				System.out.println("Enter Your Pass Word");
				password=sc.next();
				while(true) 
				{
					System.out.println("Re Enter the Pass Word");
					String conformpass=sc.next();
					//Checking both passwords are same or not
					if(conformpass.equals(password))
					{
						String regsql="insert into user_registration values(?,?,?,?)";
						pst=con.prepareStatement(regsql);
						pst.setString(1, email);
						pst.setString(2, username);
						pst.setString(3, password);
						pst.setString(4, conformpass);
						int i=pst.executeUpdate();
						if(i>0)
						{
							System.out.println("REGISTERED SUCCESSFULLY...");
							System.out.println("THANK YOU FOR YOUR REGISTRATION...");
							break;
						}}
					else{
						System.out.println("RECHECK the re entered pass word");
					}}//while loop closing
			}//if statement closing
			else
			{
				System.out.println("Already the Registration done by SAME EMAIL ID");
			}
		}catch (Exception e) 
		{
			e.printStackTrace();

		}}
	      
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*
	 * In this passenger login process the back end we created one user name and password for the user
	 * 
	 * That user name and pass word can be used to login user login module
	 * 
	 * If the login process get successful means user can perform operations
	 * operation are seat reservation,display  and cancellation
	 * 
	 * 1.seat reservation-> this module is used to reserved the seat 
	 * 
	 * 2.seat cancellation-> this module is used to canceling the reserved seat 
	 * 
	 */


	//LOGIN FOR PASSANGER
	public static void passengerLogin() 
	{
		try 
		{
			con=DbConnect.getconnection();
			//checking user name and pass word
			while(true)
			{
				System.out.println("Enter The UserName");
				username=sc.next();
				System.out.println("Enter The PassWord");
				password=sc.next();

				String checkusername="select* from user_registration where pass_name=?";
				pst=con.prepareStatement(checkusername);
				pst.setString(1,username);
				rst=pst.executeQuery();
				if(rst.next())
				{
					String checkpass="select * from user_registration where password =? ";
					pst=con.prepareStatement(checkpass);
					pst.setString(1,password);
					rst=pst.executeQuery();
					if(rst.next())
					{
						System.out.println("Login successfull");
						while(true)
						{
						System.out.println("1.Seat Reservation");
						System.out.println("2.Seat Cancellation");
						System.out.println("3.Exit");
						//choice to do the seat reservation or cancellation process
						System.out.println("Enter Your choice...");
						int ch1=sc.nextInt();
						switch(ch1)
						{
						case 1:System.out.println("Your choosen Seat Reservation Process");
						//perform the seat reservation operation
						ReservationProcess.seatReservation();
						break;
						case 2:System.out.println("Your choosen Seat Cancellation Process");
						//perform the seat canceling operation
						ReservationProcess.seatCancellation();
						break;
						case 3:
						break;
						default: System.out.println("Invalid Choice");
						break;
						}
						System.out.println("Do you want to continue the seat reservation or seat cancellation yes/no");
						String in=sc.next();
						if(in.equalsIgnoreCase("no"))
						{
							break;
						}
						
						}
					}
					
				}
				else
				{
					System.out.println("Invalid User Name");
					System.out.println("If You Are Already REGISTERED Then Go for once more chance");
					System.out.println("Otherwise Go for REGISTRATION");
					System.out.println("Do you want to go for ONE MORE CHANCE yes/no");
					//ALREADY REGISTERED THEN GIVING ONE MORE CHANCE
					String input=sc.next();
					if(input.equalsIgnoreCase("no"))//comparing input with the condition
					{
						break;
					}}break;}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//FOR USER PURPOSE SEAT RESERVATION PROCESS
	/**
	 * In this seat reservation process there are trip choosing option,timing option,seat type,ticket type,ticket class,luggage weight,payment
	 * these are the key modules are working in side this method.
	 * 
	 * while reservation process it also takes the personal information about the passenger.
	 * 
	 * Main thing in this process choosing the seat no it shows how many seats are reserved and the seat numbers also.
	 */
	
	public static void seatReservation() 
	{
		try
		{
			int totalNoOfSeats=50;
		    int avalseatcount=50;
			con=DbConnect.getconnection();
			String  com="select seatno from seat_reservation order by seatno";
			pst=con.prepareStatement(com);
			rst=pst.executeQuery();
			
			while(rst.next())
			{
				System.out.print(rst.getInt(1)+" ");
			}
			
			System.out.println("Already These Seats are RESERVED");
			String  count="select count(*) from seat_reservation";
			pst=con.prepareStatement(count);
			rst=pst.executeQuery();
			while(rst.next())
			{
				int reservedseat=rst.getInt(1);
				
				/**
				 * selecting seat no is depends on passenger convenient
				 * previously it shows how many seats are reserved and the seat numbers also
				 * 
				 * If may be the passenger enter the already reserved seat means
				 * it shows that seat number is already reserved choose other seat number and also gives one more change to the passenger
				 * 
				 * seat number is okay means it asking personal details about the passenger.
				 */
			
			//SELECTING SEAT NUMBER
			while(true) {
				
				
				System.out.println("Total number of seats"+totalNoOfSeats);
				System.out.println("available seat count"+(avalseatcount-reservedseat));
				System.out.println("Enter The Seat No");
				seatno=sc.nextInt();
				String sql1="select * from seat_reservation where seatno=?";
				pst=con.prepareStatement(sql1);
				pst.setInt(1, seatno);
				rst=pst.executeQuery();
				//entering personal details
				if(!rst.next())
				{   System.out.println("Enter Passanger Name");
				String name=sc.next();
				System.out.println("Enter Your MobileNo");
				String mobno=sc.next();
				System.out.println("Enter Your full Address");
				System.out.println("Door No");
				String doorno=sc.next();
				sc.nextLine();
				System.out.println("Enter the Street Name");
				String streetname=sc.nextLine();
				System.out.println("Enter Your Location");
				String location=sc.nextLine();
				System.out.println("Enter the Pincode");
				int pincode=sc.nextInt();
				//SELECTING TRIP PLAN
				/**
				 * Here next to personal details the airline having some different kind of trip plans
				 * 
				 * This is fully depends on the passenger requirements 
				 * what ever the choice is given from the passenger it will takes.
				 * 
				 *  If suppose the passenger choice is invalid means it shows invalid choice 
				 *  and it gives one more chance for the trip plan choosing process
				 *
				 */
				
				while(true) 
				{
					System.out.println("Which Trip you want to Choose");
					System.out.println("1.CHENNAI TO BANGALORE");
					System.out.println("2.CHENNAI TO HYDRABED");
					System.out.println("3.CHENNAI TO PUNE");
					ch=sc.nextInt();
					if(ch<4)
					{
						switch(ch)
						{
						case 1:System.out.println("Your selected Chennai to Bangalore option");
						tripname="CHENNAI TO BANGALORE";
						break;
						case 2:System.out.println("your selected Chennai to hydrabed option");
						tripname="CHENNAI TO HYDRABED";
						break;
						case 3:System.out.println("your selected Chennai to Pune option");
						tripname="CHENNAI TO PUNE";
						break;

						}break;
					}
					else
					{
						System.out.println("INVALID OPTION");
					}
				}
				//SELECTING TIMING
				
				/**
				 * After the trip plan next step is choosing the timing.
				 * 
				 * Generally the airline having some different kind of timing .
				 * 
				 * This also what ever the passenger convenient they will choose the option.
				 * If the passenger chosen the option it will takes.
				 * 
				 *If may the option is invalid it shows invalid option then
				 * gives one more chance to  go for choosing the timing option
				 */
				while(true) {
					System.out.println("Select which Time you Want..");

					System.out.println("1.DEPATURE TIME is 10.00AM and ARRIVAL TIME is 2.00PM");
					System.out.println("2.DEPATURE TIME is 11.00AM and ARRIVAL TIME is 3.10PM");
					System.out.println("3.DEPATURE TIME is 11.30AM and ARRIVAL TIME is 4.45PM");
					t=sc.nextInt();
					if(t<4)
					{
						switch(t)
						{
						case 1:System.out.println("your Depature time is 10.00AM ");
						depaturetime="10:00:00 AM";
						System.out.println("your Depature time is 10.00AM");
						arrivaltime="02:00:00 PM";

						break;
						case 2:System.out.println("your DEPATURE TIME is 11.00AM and ARRIVAL TIME is 3.10PM"); 
						depaturetime="11:00:00 AM";
						arrivaltime="03:10:00 PM";
						break;
						case 3:System.out.println("your DEPATURE TIME is 11.30AM and ARRIVAL TIME is 4.45PM ");
						depaturetime="11:30:00 AM";
						arrivaltime="04:45:00 PM";
						break;

						}break;
					}
					else
					{
						System.out.println("invalid option..."); 

					}}


				//SELECTING SEAT TYPE
				/**
				 * Next to timing option the seat type option is given.
				 * 
				 * Airline having two king of seats options
				 * 
				 * This one also the passenger requirement if the passenger 
				 * chosen the option it will taken for the further actions
				 * 
				 * If the passenger chosen the invalid option in the sense
				 *  it will give a chance to choose the seat type option.
				 */
				
				while(true) {
					System.out.println("select which type of seat you want");
					System.out.println("1.AISLE SEAT");
					System.out.println("2.WINDOW SEAT");
					s=sc.nextInt();
					if(s<3)
					{
						switch(s){
						case 1:System.out.println("your selected the type is AISLE SEAT (A and F rows)");
						seattype="aisel type";
						break;

						case 2:System.out.println("your selected the type is WINDOW SEAT(C and D rows)");
						seattype="window type";
						break; 
						}
						break;
					}
					else
					{
						System.out.println("invalid option...");

					}

				}
				//SELECTING TICKET TYPE
				/**
				 * Next to seat type option the ticket type is given to the passengers.
				 * 
				 * This option is also depends on the passenger needs.If the passenger chosen the option 
				 * it will taken for the further processes.
				 * 
				 * If the passenger chosen the invalid option in the sense
				 * it will give a chance to choose the seat type option.
				 */
				while(true)
				{
					
					System.out.println("1.FIRST CLASS ticket price is 9000");
					System.out.println("2.NORMAL CLASS ticket price is 4000");
					System.out.println("enter your choice");
					int ch=sc.nextInt();
					if(ch<3) 
					{
						switch(ch)
						{
						case 1:System.out.println("your selected FIRST CLASS ticket");
						tickettype="first class";
						ticketamount=6000;
						break;
						case 2:System.out.println("your selected NORMAL CLASS ticket");
						tickettype="normal class";
						ticketamount=4000;
						break;
						}break;
					}}
				//SELECTING LUGGAGE WEIGHT
				/**
				 * Next to ticket type option the luggage weight options is given to the passengers.
				 * 
				 * This option is more important comparing to other transport types this also giving the options to the passengers.
				 *  If the passenger chosen the option it will taken for the further processes.
				 * 
				 * If the passenger chosen the invalid option in the sense
				 * it will give a chance to choose the seat type option.
				 */
				while(true) 
				{
					
					System.out.println("1.Inventory spare is 8kg then fare is 1000");
					System.out.println("2.Inventory spare is 3kg then fare is 500");
					System.out.println("enter your choice");
					int fare=sc.nextInt();
					if(fare<3) 
					{
						switch(fare)
						{
						case 1:System.out.println("your selected 8kg");

						inventoryfare=1000;
						break;
						case 2:System.out.println("your selected 3kg");
						inventoryfare =500;
						break;
						}
						break;}
				}
				//PAYMENT PROCESS
				
				/**
				 * The payment process also very simple and also securely handled.
				 * 
				 * It also having checking processes taking informations from the passengers account number 
				 * and pin it two data's are correct then it do the payment process 
				 * 
				 * Here the account number or pin number is invalid means it gives the alert and gives one more chances.
				 * 
				 * May be both are correct but the balance is insufficient means it gives that insufficient message also.
				 * 
				 * If the payment is done then only it reserve that seat otherwise it not takes  all the information before the payment option.
				 */
				while(true)
				{
					totalamount=ticketamount+inventoryfare;
					System.out.println("GO TO PAYMENT");
					System.out.println("Enter your Account Number");
				    accno=sc.nextLong();
					System.out.println("Enter your Pin Number");
					pinno=sc.nextInt();
					String transacc="select accno from customers_account where accno=?";
					pst=con.prepareStatement(transacc);
					pst.setLong(1, accno);
					rst=pst.executeQuery();
					if(rst.next())
					{
						String transpin="select pinno from customers_account where pinno=?";
						pst=con.prepareStatement(transpin);
						pst.setLong(1, pinno);
						rst=pst.executeQuery();
						if(rst.next())
						{
							
							String ins="insert into seat_reservation values(?,?,?,?,?,?,?)";
							pst=con.prepareStatement(ins);
							pst.setInt(1, seatno);
							pst.setString(2, name);
							pst.setString(3, mobno);
							pst.setString(4, doorno);
							pst.setString(5, streetname);
							pst.setString(6, location);
							pst.setInt(7, pincode);
							int i=pst.executeUpdate();		
							if(i>0)
							{
								String seatch="insert into flight_timing_detail values(?,?,?,?,?,?,?,?)";
								pst=con.prepareStatement(seatch);
								pst.setInt(1, ch);
								pst.setString(2, tripname);
								pst.setInt(3, t);
								pst.setString(4, depaturetime);
								pst.setString(5, arrivaltime);
								pst.setInt(6, s);
								pst.setString(7, seattype);
								pst.setInt(8, seatno);
								int p=pst.executeUpdate();
								if(p>0)
								{
									String fare="insert into ticket_fare_detail values(?,?,?,?,?)";
									pst=con.prepareStatement(fare);
									pst.setString(1, tickettype);
									pst.setLong(2, ticketamount);
									pst.setLong(3, inventoryfare);
									pst.setLong(4, totalamount);
									pst.setInt(5, seatno);
									int k=pst.executeUpdate();
									if(k>0)
									{
										String bal="select balance from customers_account where (select balance from customers_account where accno=?)>0";
										pst=con.prepareStatement(bal);
										pst.setLong(1,accno);
										
										rst=pst.executeQuery();
										if(rst.next())
										{
											
											String withdraw="update customers_account set balance=balance-(select totalfare from ticket_fare_detail where seatno=?) where accno=? ";
											pst=con.prepareStatement(withdraw);
											pst.setLong(1,seatno);
											pst.setLong(2,accno);
											int t=pst.executeUpdate();
											if(t>0)
											{
												
												String deposite="insert into admin_account values(?,?)";
												pst=con.prepareStatement(deposite);
												pst.setLong(1, totalamount);
												pst.setInt(2, seatno);
												int l=pst.executeUpdate();
												if(l>0)
												{
													System.out.println("your seat is reserved");
													System.out.println("Do you want to See the Whole details about your reservation yes/no");
													String m=sc.next();
													if(m.equalsIgnoreCase("yes"))
													{
											
													ReservationProcess.displayReservationDetails();
													totalNoOfSeats=totalNoOfSeats-1;
													break;
													}
													else
													{
														break;
													}
												}
												break;
											}}
											else
											{
												System.out.println("Insufficient balance");
												String delfare="delete from ticket_fare_detail where seatno=?";
												pst=con.prepareStatement(delfare);
												pst.setInt(1, seatno);
												int df=pst.executeUpdate();
												if(df>0)
												{
													String deltime="delete from flight_timing_detail where seatno=?";
													pst=con.prepareStatement(deltime);
													pst.setInt(1, seatno);
													int dt=pst.executeUpdate();
													if(dt>0)
													{
														String delseat="delete from seat_reservation where seatno=?";
														pst=con.prepareStatement(delseat);
														pst.setInt(1, seatno);
														int ds=pst.executeUpdate();
														if(ds>0)
														{
															System.out.println("check your balance");
															break;
														}
													}


												}
											}}}}}
					}else
					{
						System.out.println("invalid account number or pin number");
						
					}
				}
				}else
				{
				System.out.println("This seat no is already reserved... ");
				
				}
				avalseatcount=totalNoOfSeats-1;
				break;
			}
				
			}
		}catch (Exception e) {
			e.printStackTrace();}}	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//CANCELLATION PROCESS
	
	/**
	 * This cancellation process is given to the passengers convenient.
	 * 
	 * If may be situation passenger not able to travel means it will gives the chance
	 *  to canceling the seat and conditions applied amount is refund.
	 *  
	 *  It asking the major informations regarding that passengers email id,
	 *   user name, password and seat number every informations are correct
	 *    then only it will cancel the seat other wise it will not cancel.
	 */
	public static void seatCancellation()
	{
		try 
		{
			con=DbConnect.getconnection();
			while(true)
			{
				System.out.println("enter your email id");
				email=sc.next();
				System.out.println("enter the user name");
				username=sc.next();
				System.out.println("enter the password");
				password=sc.next();
				System.out.println("enter the seat number");
				seatno=sc.nextInt();
				String chmail="select pass_emailid from user_registration where pass_emailid=?";
				pst=con.prepareStatement(chmail);
				pst.setString(1,email);
				rst=pst.executeQuery();
				if(rst.next())
				{
					String chuser="select pass_name from user_registration where pass_name=?";
					pst=con.prepareStatement(chuser);
					pst.setString(1,username);
					rst=pst.executeQuery();
					while(rst.next())
					{
						String chpass="select password from user_registration where password=?";
						pst=con.prepareStatement(chpass);
						pst.setString(1,password);
						rst=pst.executeQuery();
						if(rst.next())
						{
							
							    String canflight="delete from flight_timing_detail where seatno=?";
								pst=con.prepareStatement(canflight);
								pst.setInt(1,seatno);
								int m=pst.executeUpdate();

								if(m>0)
								{ 
									
									String canfare="delete from ticket_fare_detail where seatno=?";
									pst=con.prepareStatement(canfare);
									pst.setInt(1,seatno);
									int l=pst.executeUpdate();
									if(l>0)
									{

										String canadmin="delete from admin_account where seatno=? ";
										pst=con.prepareStatement(canadmin);
										pst.setInt(1,seatno);
										int p=pst.executeUpdate();
										if(p>0)
										{
											
										String canseat="delete from seat_reservation where seatno=?";
										pst=con.prepareStatement(canseat);
										pst.setInt(1,seatno);
										int j=pst.executeUpdate();

										if(j>0)
										{
										       System.out.println("Your cancellation is done");
												break;
											}}}
								break;}
							}else
							{
								System.out.println("your not reserved any seat....");
							}


					}}
				else
				{
					System.out.println("invalid email");
				}break;}
		}catch(Exception e){
			e.printStackTrace();
		}}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------


	//FOR ADMIN PROCESS DISPLAY RECORDS
	/**
	 * This module is used to display the information about the seat reservation.
	 * 
	 * If the admin want the see information about the specified seat number means admin can
	 * able to put the seat number what ever the seat number he need and it will show the highlighted informations about the passenger.
	 */
	public static void displayRervation()
	{
		try 
		{ 
			con=DbConnect.getconnection();
            System.out.println("Enter the seat no:");
            seatno=sc.nextInt();
			String de="select * from seat_reservation  where seatno=?";
			pst=con.prepareStatement(de);
			pst.setInt(1,seatno);
		    rst=pst.executeQuery();
			if(rst.next())
			{
				System.out.println("--------------------PERSONAL DETAIL--S---------------");
				System.out.println("_______________________________________________________________");
				System.out.println("Seatno\t\tName\t\tMobno\t\tPincode");
				System.out.println("_______________________________________________________________");
				System.out.println(rst.getInt(1)+"\t\t"+rst.getString(2)+"\t\t"+rst.getString(3)+"\t"+rst.getInt(7));
				System.out.println("_______________________________________________________________");
				System.out.println(            );
				
				String seatch="select *  from flight_timing_detail where seatno=?";
			    pst=con.prepareStatement(seatch);
			    pst.setInt(1, seatno);
			    rst=pst.executeQuery();
				while(rst.next())
				{
					System.out.println("------------------TRAVELLING DETAILS-------------");
					
					System.out.println("_______________________________________________________________");
					System.out.println("Traveling place\t\tSeattype\t");
					System.out.println("_______________________________________________________________");
					System.out.println(rst.getString(2)+"\t"+rst.getString(7)+"\t");
					System.out.println("_______________________________________________________________");
					System.out.println(            );
					String farech="select * from ticket_fare_detail where seatno=?";
				    pst=con.prepareStatement(farech);
				    pst.setInt(1, seatno);
					rst=pst.executeQuery();
					while(rst.next())
					{
						System.out.println("-----------------------FARE DETAILS-----------------------");
						System.out.println("_______________________________________________________________");
						System.out.println("Tickettype\tticketamount\tinventoryfare\ttotalamount");
						System.out.println("_______________________________________________________________");
						System.out.println(rst.getString(1)+"\t"+rst.getInt(2)+"\t\t"+rst.getInt(3)+"\t\t"+rst.getInt(4)+"\t");
						System.out.println("_______________________________________________________________");
						//System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					break;
					}
				}
			}
			else
			{
				System.out.println("seat no is not exist");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//FOR USER PROCESS-->DISPLAY RECORDS
	/**
	 * This module is used to display the information about the seat reservation.
	 * 
	 * After the seat reservation it will ask you want to know about your seat reservation informations
	 * If the passenger given yes means it show all the details about the reservation.
	 */
	public static void displayReservationDetails()
	{
		try 
		{ 
			con=DbConnect.getconnection();

			String de="select * from seat_reservation  where seatno=?";
			pst=con.prepareStatement(de);
			pst.setInt(1,seatno);
			rst=pst.executeQuery();
			while(rst.next())
			{
				System.out.println("----------------------------------------------PERSONAL DETAILS-----------------------------------------");
				System.out.println("_________________________________________________________________________________________________________________________");
				System.out.println("Seatno\t\tName\t\t\tMobno\t\tDoorno\t\tStreetname\t\t\tLocation\tPincode");
				System.out.println("_________________________________________________________________________________________________________________________ ");
				System.out.println(rst.getInt(1)+"\t\t"+rst.getString(2)+"\t\t"+rst.getString(3)+"\t\t"+rst.getString(4)+"\t\t"+rst.getString(5)+"\t"+rst.getString(6)+"\t\t"+rst.getInt(7)+"\t");
				System.out.println("__________________________________________________________________________________________________________________________ ");
				String seatch="select * from flight_timing_detail where seatno=?";
				pst=con.prepareStatement(seatch);
			    pst.setInt(1, seatno);
				rst=pst.executeQuery();
				while(rst.next())
				{ System.out.println(        );
					System.out.println("--------------------------TRAVELLING DETAILS-------------------------");
					System.out.println("______________________________________________________________________________________");
					System.out.println("Traveling place\t\tsdeparture time\t\tarrival time\t\tSeattype\t");
					System.out.println("____________________________________________________________________________________");
					System.out.println(rst.getString(2)+"\t\t"+rst.getString(4)+"\t\t"+rst.getString(5)+"\t\t"+rst.getString(7)+"\t");
					System.out.println("____________________________________________________________________________________");
					String farech="select * from ticket_fare_detail where seatno=?";
					pst=con.prepareStatement(farech);
					pst.setInt(1, seatno);
					rst=pst.executeQuery();
					while(rst.next())
					{
						System.out.println(        );
						System.out.println("-----------------------FARE DETAILS--------------------------------");
						System.out.println("_______________________________________________________________");
						System.out.println("Tickettype\tticketamount\tinventoryfare\ttotalamount");
						System.out.println("_______________________________________________________________");
						
						System.out.println(rst.getString(1)+"\t"+rst.getInt(2)+"\t\t"+rst.getInt(3)+"\t\t"+rst.getInt(4)+"\t");
						System.out.println("_______________________________________________________________");
						break;
					}
				}break;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//FOR ADMIN PROCESS RESERVED SEAT COUNT
		/**
		 * This is for admin purpose If may be seat is not available they are not given that reservation process enable.
		 * 
		 * If there is no seat is reserved means that time it gives the message no seats are reserved.
		 * 
		 * And also reserved seat calculation purpose.
		 */
		private static void reservationState() {
			try
			{
				con=DbConnect.getconnection();
				String sel="select count(*) from seat_reservation";
				pst=con.prepareStatement(sel);
				rst=pst.executeQuery();
				if(rst.next())
				{
					int reservedSeats=rst.getInt(1);
					System.out.println("Number of Seat are Reserved "+reservedSeats);
				}
				else
				{
					System.out.println("Till now no seat are reserved ");
				}


			}catch(Exception e)
			{
				e.printStackTrace();
			}

		}
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------



}




