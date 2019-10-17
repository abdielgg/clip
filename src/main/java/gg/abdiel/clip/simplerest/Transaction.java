package gg.abdiel.clip.simplerest;

import java.time.LocalDateTime;

public class Transaction {
	private String id;
	private double amount;
	private String descrption;
	private LocalDateTime date;
	
	

	public Transaction(String id, String descrption) {
		super();
		this.id = id;
		this.descrption = descrption;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
