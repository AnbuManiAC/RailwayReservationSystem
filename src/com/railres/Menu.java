package com.railres;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.railres.dao.Database;
import com.railres.train.Ticket;
import com.railres.train.Train;
import com.railres.user.User;

public class Menu {

	public static void main(String[] args) {
		
		Database db = Database.getInstance();
		
//		Properties prop = new Properties();
//		String fileName = "railres.config";
//		try (FileInputStream fis = new FileInputStream(fileName)) {
//		    prop.load(fis);
//		} catch (FileNotFoundException ex) {
//		    System.out.println(ex); // FileNotFoundException catch is optional and can be collapsed
//		} catch (IOException ex) {
//		}
//		
//		System.out.println(prop.getProperty("name"));
		
		System.out.println("\n******************************");
		System.out.println("  Railway Reservation System");
		System.out.println("******************************");
		
		Scanner sc = new Scanner(System.in);
		String choice;
		
		home:
		while(true) {
			System.out.println("\n1. Register\n2. Login\n");
			System.out.println("\nEnter yout choice : ");
			choice = sc.next();
			switch(choice) {
			case "1":
				System.out.println("\n---Signup Page---\n");
				
				String name, pwd;
				getName :
				while(true) {
					System.out.println("\nEnter username : ");
					name = sc.next();
					if(!isValidName(name)) {
						System.out.println("\n# Username must be 3 character long and must only have letters #\n");
						continue getName;
					}
					break getName;
				}	
				
				getPwd:
				while(true) {
					System.out.println("\nEnter password : ");
					pwd = sc.next();
					if(pwd.length()<5) {
						System.out.println("\n# Password must be 5 characters long #\n");
						continue getPwd;
					}
					break getPwd;
				}
				User user = new User(name,pwd);
				db.getUsers().add(user);
				System.out.println("\nSuccessfully registered.\nLogin to continue\n");
				
			case "2":
				System.out.println("\n---Login Page---\n");
				
				String username, password;
				while(true) {
					System.out.println("\nEnter Username : ");
					username = sc.next();
					pwd:
					while(true) {
						System.out.println("\nEnter Password : ");
						password = sc.next();
						if(db.checkUser(username)) {
							if(db.isExistingUser(username, password)) {
								System.out.println("\nSuccessfully logged in\n");
								break home;
							}
							else {
								System.out.println("\nIncorrect password. Enter correct password to proceed #\n");
								continue pwd;
							}
						}
						else {
							System.out.println("\nUser doesn't exists.");
							continue home;
						}
					}
				}
			}
		}
			
		
		boolean loop = true;
		
		while(loop)
		{
			System.out.println("\n-----MENU-----");
			System.out.println("\n1. Show all trains\n2. Search Train\n3. Book Ticket\n4. Cancel Ticket\n5. Check Ticket Status\n6. Show tickets\n7. Exit\n\n");
			System.out.println("Enter your choice : ");
			choice = sc.next();
			switch(choice) {
			case "1":
				List<Train> trains = db.getTrains();
				if(trains.size()>0) {
					System.out.println("\nAvailable Trains : \n");
					for(Train train: trains) {
						System.out.println("["+train.getTrainInfo()+"]\n");
					}
				}
				else {
					System.out.println("\n----------No train available\n");
				}
				break;
				
			case "2":
				String source, destination;
				LocalDate date;
				
				getStation : 
				while(true) {
					System.out.println("\nEnter source : ");
					source = sc.next();
					if(!db.isStationAvailable(source)) {
						System.out.println("\nEnter valid station\n");
						continue getStation;
					}
					break;
				}
				
				getDest : 
				while(true) {
					System.out.println("\nEnter destination : ");
					destination = sc.next();
					if(!db.isStationAvailable(destination)) {
						System.out.println("\nEnter valid station\n");
						continue getDest;
					}
					break;
				}
				
				
				getDate :
				while(true) {
					System.out.println("\nEnter Date(yyyy-mm-dd) : ");
					String _date = sc.next();
					if(!isValidDate(_date)) {
						System.out.println("\n# Enter valid date / Enter date in specified format #");
						continue getDate;
					}
					if(!isLogicalDate(_date)) {
						System.out.println("\n# Date must not be in the past #");
						continue getDate;
					}
					date = LocalDate.parse(_date);
					break getDate;
					
				}
							
				ArrayList<Train> filteredTrains =  db.searchTrain(source,destination,date);
				if(filteredTrains.size()>0) {
					for(Train train: filteredTrains) {
						System.out.println(train.getTrainInfo());
						
					}
				}
				else {
					System.out.println("\nNo Trains availaible.");
				}
				
				
				break;
				
			case "3":
				String name, berthPreference, gender, bookingdate;
				int age, trainNumber;
				LocalDate bookingDate;
				Train train;
				
				getName :
				while(true) {
					System.out.println("\nEnter name : ");
					name = sc.next();
					if(!isValidName(name)) {
						System.out.println("\n# Enter valid name #");
						continue getName;
					}
					break;
				}
				
				getAge :
				while(true) {
					System.out.println("\nEnter age : ");
					age = sc.nextInt();
					if(age<=0 || age>125) {
						System.out.println("\n# Enter valid age #");
						continue getAge;
					}
					break;
				}
				
				getGender :
				while(true) {
					System.out.println("\nGender(Male/Female/Other/NA(prefer not to say) : ");
					gender = sc.next();
					if(!(gender.equals("Male") || gender.equals("Female") || gender.equals("Other") || gender.equals("NA"))) {
						System.out.println("\n# Enter Gender as mentioned #");
						continue getGender;
					}
					break;
				}

				
				getTrain :
				while(true) {
					System.out.println("\nEnter train number : ");
					trainNumber = sc.nextInt();
					train = db.getTrainFromNumber(trainNumber);
					if(train==null) {
						System.out.println("\n# Enter valid train number or use search train option #");
						continue getTrain;
					}
					break getTrain;
				}
				
				getDate :
				while(true) {
					System.out.println("\nEnter date of journey (yyyy-mm-dd) : ");
					bookingdate = sc.next();
					if(!isValidDate(bookingdate)) {
						System.out.println("\n# Enter valid date #");
						continue getDate;
					}
					if(!isLogicalDate(bookingdate)) {
						System.out.println("\n# Date must not be in the past #");
						continue getDate;
					}
					bookingDate = LocalDate.parse(bookingdate);
					break;
					
				}
				
				getBerth :
					while(true) {
						System.out.println("\nEnter berth preference(L for Lower/M for Middle/U for upper) : ");
						berthPreference = sc.next();
						if(!(berthPreference.equals("L") || berthPreference.equals("M") || berthPreference.equals("U"))) {
							System.out.println("\n# Enter valid berth / Enter as specified #");
							continue getBerth;
						}
						break;
						
					}
				
				
				Passenger passenger = new Passenger(name, age, gender, berthPreference);
				Ticket passengerTicket = Booking.bookTicket(passenger,train,bookingDate);
				if(passengerTicket!=null)
				{
					if(passengerTicket.getBerthAlloted().equals(passenger.getBerthPreference()))
						System.out.println("\nPreferred berth available\nSuccessfully booked\n");
					else if(passengerTicket.getBerthAlloted().equals("WL")){
						System.out.println("\nNo tickets available... You are in waiting list.\n");
					}
					else {
						System.out.println(String.format("\nPreferred berth unavailable...\nAlloted berth : %s\n",passengerTicket.getBerthAlloted()));

					}
					db.getPassenger().add(passenger);
					System.out.println("\n----------Here's your ticket\n");
					passengerTicket.generateTicket();
				}
				else {
					System.out.println("\n---Sorry! No tickets available---\n");
				}
				break;
				
			case "4":
				System.out.println("\nEnter PNR to cancle ticket : ");
				int pnr = sc.nextInt();
				if(Booking.cancelTicket(pnr)) {
					System.out.println("\nTicket cancelled successfully\n");
				}
				else {
					System.out.println("\nInvalid PNR! Enter valid PNR.\n");
				}
				break;
				
			case "5":
				System.out.println("\nEnter PNR : \n");
				int PNR = sc.nextInt();
				Ticket ticket = db.getTicketfromPnr(PNR);
				if(ticket==null) {
					System.out.println("\nTicket not found\n");
				}
				else {
					ticket.generateTicket();
				}
				break;
				
			case "6":
				List<Ticket> berthTicket = db.getBerthTicket();
				LinkedList<Ticket> racTicket = db.getRacTicket();
				LinkedList<Ticket> waitingList = db.getWaitingList();
				
				if(berthTicket.size()>0 || racTicket.size()>0 || waitingList.size()>0) {
				
					for(Ticket t:berthTicket) {
						t.generateTicket();
					}
					for(Ticket t:racTicket) {
						t.generateTicket();
					}
					for(Ticket t:waitingList) {
						t.generateTicket();
					}
					
				}
				else {
					System.out.println("\nNo ticktes found.");
				}
				break;
				
			case "7":
				loop = false;
				sc.close();
				break;
				
			default:
				System.out.println("\nEnter valid choice\n");
				break;
		
			}
		}

		
	}

	private static boolean isValidName(String name) {
		if(name.length()>2 && name.matches("[a-zA-z]+"))
			return true;
		return false;
	}
	

	private static boolean isValidDate(String date) {
		
		final Pattern DATE_PATTERN = Pattern.compile(
			      "^((2000|2400|2800|(2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$" 
			      + "|^((2[0-9]{3})-02-(0[1-9]|1[0-9]|2[0-8]))$"
			      + "|^((2[0-9]{3})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
			      + "|^((2[0-9]{3})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");

			    
		return DATE_PATTERN.matcher(date).matches();
	}
	private static boolean isLogicalDate(String date) {
		LocalDate _date = LocalDate.parse(date);
		LocalDate presentDate = LocalDate.now();
		if(_date.compareTo(presentDate)>=0) {
			return true;
		}
		return false;
	}
}
