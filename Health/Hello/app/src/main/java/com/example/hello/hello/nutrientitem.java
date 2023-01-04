package com.example.hello.hello;

public class nutrientitem {
    private String name;
    private String amt;

    public nutrientitem(String name,String amt){
        this.name = name;
        this.amt = amt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
