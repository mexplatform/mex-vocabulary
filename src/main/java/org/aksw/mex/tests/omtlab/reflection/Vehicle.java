package org.aksw.mex.tests.omtlab.reflection;

/**
 * Created by dnes on 14/12/15.
 */
public class Vehicle {


    //All Fields

    private String name = "";
    public int price = 0;
    protected String about = "";
    public String companyName = "";

    //All Constructor

    public Vehicle() {
        System.out.println("Vehicle()");
    }

    public Vehicle(String vName) {
        this.name = vName;
        System.out.println("Vehicle(String vName) :"+vName);
    }

    public Vehicle(String carName,int price) {
        this.name = carName;
        this.price = price;
        System.out.println("Vehicle(String carName,int price)"+carName+","+price);
    }


    // All Methods

    public void setName(String name,int price)
    {
        this.name = name;
        this.price = price;
        System.out.println("Name and Price is Set :::>>"+name+" and "+price);
    }

    public void setCompanyName(String cname)
    {
        this.companyName = cname;
        System.out.println("Company Name is Set :>>"+companyName);
    }

    public int getPrice()
    {
        return price;
    }



    public void printDetails()
    {
        System.out.println("Vehicle Name :"+name);
        System.out.println("Vehicle Price :"+price);
        System.out.println("Vehicle about :"+about);
        System.out.println("Vehicle CompanyName :"+companyName);
    }

}
