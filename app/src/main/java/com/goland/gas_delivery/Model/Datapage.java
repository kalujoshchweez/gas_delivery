package com.goland.gas_delivery.Model;

public class Datapage {
    String Name;
    String number;
    String id;

    public Datapage(){

    }

    public Datapage(String name, String number, String id) {
        Name = name;
        this.number = number;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
