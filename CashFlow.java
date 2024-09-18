package com.example.sale_management;


public class CashFlow {
    private int DayID;
	private String date;//primary key
    private int debtOnUS;
    private int paidDebts;
    private int totalSalaries;
    private int totalCash;
    private int totalIncome;
	public CashFlow(int DayID,String date, int debtOnUS, int paidDebts, int totalSalaries, int totalCash, int totalIncome) {
		super();
		this.DayID=DayID;
		this.date = date;
		this.debtOnUS = debtOnUS;
		this.paidDebts = paidDebts;
		this.totalSalaries = totalSalaries;
		this.totalCash = totalCash;
		this.totalIncome = totalIncome;
	}
	
	public CashFlow(String date, int debtOnUS, int paidDebts, int totalSalaries, int totalCash, int totalIncome) {
		super();
		this.date = date;
		this.debtOnUS = debtOnUS;
		this.paidDebts = paidDebts;
		this.totalSalaries = totalSalaries;
		this.totalCash = totalCash;
		this.totalIncome = totalIncome;
	}

	public int getDayID() {
		return DayID;
	}

	public void setDayID(int dayID) {
		DayID = dayID;
	}


	public String getDate() {
		return date;
	}
	public int getDebtOnUS() {
		return debtOnUS;
	}
	public int getPaidDebts() {
		return paidDebts;
	}
	public int getTotalSalaries() {
		return totalSalaries;
	}
	public int getTotalCash() {
		return totalCash;
	}
	public int getTotalIncome() {
		return totalIncome;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDebtOnUS(int debtOnUS) {
		this.debtOnUS = debtOnUS;
	}
	public void setPaidDebts(int paidDebts) {
		this.paidDebts = paidDebts;
	}
	public void setTotalSalaries(int totalSalaries) {
		this.totalSalaries = totalSalaries;
	}
	public void setTotalCash(int totalCash) {
		this.totalCash = totalCash;
	}
	public void setTotalIncome(int totalIncome) {
		this.totalIncome = totalIncome;
	}
    
}
