package com.railres.train;

import java.time.LocalDate;

import com.railres.Passenger;

public class Ticket {

	private Passenger passenger;
	private Train train;
	private String berthAlloted;
	private int seatNumber;
	private LocalDate date;
	private int id;
	//private int price;
	

	public Ticket(Passenger passenger, Train train, String berthAlloted, LocalDate date) {
		this.setPassenger(passenger);
		this.setTrain(train);
		this.setBerthAlloted(berthAlloted);
		this.setDate(date);
		this.setId();
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public String getBerthAlloted() {
		return berthAlloted;
	}
	public void setBerthAlloted(String berthAlloted) {
		this.berthAlloted = berthAlloted;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setId() {
		this.id = passenger.getPnr();
	}
	public int getId() {
		return id;
	}

	
	
//	public int getPrice() {
//		return price;
//	}
//	public void setPrice(int price) {
//		this.price = price;
//	}
//	
	

	public void generateTicket() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Ticket ["+ passenger.toString() + ", Train number : " + train.getNumber() +", Date : "+date+", Berth alloted : " + berthAlloted
				+ ", Seat number : " +berthAlloted+seatNumber + "]";
	}
	
	
}
