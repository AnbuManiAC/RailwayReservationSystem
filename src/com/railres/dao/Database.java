package com.railres.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.railres.*;
import com.railres.train.*;

public class Database {
	
	
	private List<Train> trains = new ArrayList<>();
	private List<Station> stations = new ArrayList<>();
	
	private List<Passenger> passenger = new ArrayList<>();
	
	private List<Ticket> berthTicket = new ArrayList<>();
	private LinkedList<Ticket> racTicket = new LinkedList<>();
	private LinkedList<Ticket> waitingList = new LinkedList<>();
		
	private Database()
	{
		stations.add(new Station("Chennai"));
		stations.add(new Station("Mumbai"));
		stations.add(new Station("Banglore"));

		trains.add(new Train(1,"Chennai Express",new Station("Chennai"),new Station("Mumbai")));
		trains.add(new Train(2,"Cheran Express",new Station("Chennai"),new Station("Banglore")));

	}
	
	private static Database instance = null;
	
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public List<Train> getTrains(){
		return trains;
	}
	
	public List<Station> getStattions(){
		return stations;
	}
	
	public List<Passenger> getPassenger() {
		return passenger;
	}


	public List<Ticket> getBerthTicket() {
		return berthTicket;
	}




	public LinkedList<Ticket> getRacTicket() {
		return racTicket;
	}



	public LinkedList<Ticket> getWaitingList() {
		return waitingList;
	}



	public ArrayList<Train> searchTrain(String source, String destination, LocalDate date) {
		ArrayList<Train> searchResult = new ArrayList<>();
		for(Train train : trains) {
			if(train.getSource().getName().equalsIgnoreCase(source) && train.getDestination().getName().equalsIgnoreCase(destination) && train.getAvailability().containsKey(date)) {
				searchResult.add(train);
			}
		}
		return searchResult;
	}
	
	public Train getTrainFromNumber(int trainNumber) {
		for(Train t: trains) {
			if(trainNumber == t.getNumber())
				return t;
		}
		return null;
	}
	
	public Passenger getPassengerFromPnr(int pnr) {
		for(Passenger p:passenger) {
			if(pnr == p.getPnr())
				return p;
		}
		return null;
	}
	
	public Availability checkAvailability(Train train,LocalDate date) {
		return train.getAvailability().get(date);
	}
	
	public Ticket getTicketfromPnr(int pnr) {
		for(Ticket t:berthTicket) {
			if(t.getId()==pnr) {
				return t;
			}
		}
		for(Ticket t:racTicket) {
			if(t.getId()==pnr) {
				return t;
			}
		}
		for(Ticket t:waitingList) {
			if(t.getId()==pnr) {
				return t;
			}
		}
		return null;
	}
	
	
	public boolean isStationAvailable(String station) {
		for(Station s:stations) {
			if(s.getName().equalsIgnoreCase(station))
				return true;
		}
		
		return false;
	}
	

	
	
	
}
