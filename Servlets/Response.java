package com.higradius;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// POJO class for the all elements of a row
public class Response {
	
	// Variables declaration
	private int field1;
	private long customerID, invoiceID;
	private String name, due_date, payment_date, notes, final_amount;
	private double amount;

	// Getter and setter for primary key - filed1
	public int getKey() {
		return field1;
	}
	
	public void setKey(int filed1) {
		if(filed1 >= 0)
			this.field1 = filed1;
	}
	
	// Getter and setter for name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	// Getter and setter for due date
	public String getDue_date() {
		return due_date;
	}
	
	public void setDue_date(String due_date) {
		if (due_date != null)
			this.due_date = due_date;
		
		// Handle date formatting
		String temp[] = this.due_date.split("-");
		int y = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		int d = Integer.parseInt(temp[2]);
		LocalDateTime val = LocalDateTime.of(y, m, d, 0, 0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		this.due_date = formatter.format(val);
	}

	// Getter and setter for payment date
	public String getPayment_date() {
		return payment_date;
	}
	
	public void setPayment_date(String payment_date) {
		if (payment_date != null) {
			this.payment_date = payment_date;
		
			// Handle date formatting
			String temp[] = this.payment_date.split("-");
			int y = Integer.parseInt(temp[0]);
			int m = Integer.parseInt(temp[1]);
			int d = Integer.parseInt(temp[2]);
			LocalDateTime val = LocalDateTime.of(y, m, d, 0, 0);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			this.payment_date = formatter.format(val);
		}
		else
			this.payment_date = "--";
	}

	// Getter and setter for notes
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	// Getter and setter for customer ID
	public long getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(long customerID) {
		if (customerID >= 0)
			this.customerID = customerID;
	}
	
	// Getter and setter for invoice ID
	public long getInvoiceID() {
		return invoiceID;
	}
	
	public void setInvoiceID(long invoiceID) {
		if (invoiceID >= 0)
			this.invoiceID = invoiceID;
	}
	
	// Setter for amount
	public void setAmount(double amount) {
		if (amount >= 0)
			this.amount = amount;
		
		// Handle amount to 'k' convention and round to 2 decimal places
		DecimalFormat d = new DecimalFormat("##.00");
		this.amount = this.amount / 1000;
		final_amount = d.format(this.amount) + 'K';
	}
	
	// Setter for all data
	public void setAll(int filed1, String name, String due_date, String payment_date,
			long customerID, long invoiceID, double amount, String notes) {
		
		// Calling setter methods for all
		setKey(filed1);
		setName(name);
		setDue_date(due_date);
		setPayment_date(payment_date);
		setCustomerID(customerID);
		setInvoiceID(invoiceID);
		setAmount(amount);
		setNotes(notes);
	}
}
