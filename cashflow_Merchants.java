package com.example.sale_management;

import java.util.Date;

public class cashflow_Merchants {
    private int DayID;//FOREIGN key from cashflow table
    private int merchantsID;//FOREIGN key from merchants table
    private float paidDebts;

    public cashflow_Merchants( int DayID, int merchantsID,float paidDebts) {
        this.paidDebts = paidDebts;
        this.DayID = DayID;
        this.merchantsID = merchantsID;
    }

	public float getPaidDebts() {
		return paidDebts;
	}

	public int getDayID() {
		return DayID;
	}

	public int getMerchantsID() {
		return merchantsID;
	}

	public void setPaidDebts(float paidDebts) {
		this.paidDebts = paidDebts;
	}

	public void setDayID(int date) {
		this.DayID = date;
	}

	public void setMerchantsID(int merchantsID) {
		this.merchantsID = merchantsID;
	}
    
}