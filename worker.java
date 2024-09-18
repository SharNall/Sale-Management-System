package com.example.sale_management;

public class worker {
     private int workerID;
     private String name;
     private  String phone;
     private  String workertype;
     private double salary;

    public worker(String name, String phone, double salary,String workertype) {
        this.name = name;
        this.phone = phone;
        this.salary = salary;
        this.workertype=workertype;
    }

    public worker(int workerID, String name, String phone, double salary,String workertype) {
        this.workerID = workerID;
        this.name = name;
        this.phone = phone;
        this.salary = salary;
        this.workertype=workertype;
    }
    
    public String getWorkertype() {
		return workertype;
	}

	public void setWorkertype(String workertype) {
		this.workertype = workertype;
	}

	public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

