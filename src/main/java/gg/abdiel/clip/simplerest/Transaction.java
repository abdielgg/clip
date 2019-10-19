package gg.abdiel.clip.simplerest;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column
	private String id;
	@Column
	private double amount;
	@Column
	private String description;
	@Column
	private LocalDateTime date;
//	@ManyToOne(optional=false)
//    @JoinColumn(name="userId",referencedColumnName="id")
//	private User user;
	
	
	
	public Transaction(String id, String descrption) {
		super();
		this.id = id;
		this.description = descrption;
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
		return description;
	}

	public void setDescrption(String descrption) {
		this.description = descrption;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
