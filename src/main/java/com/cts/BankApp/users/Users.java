package com.cts.BankApp.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Users")
 
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String accountNumber;
	private String bankName;
	private long balance;
	public Users() {};
	 public Users(int id, String accountNumber, String bankName, long balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", accountNumber=" + accountNumber + ", bankName=" + bankName + ", balance="
				+ balance + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}

}