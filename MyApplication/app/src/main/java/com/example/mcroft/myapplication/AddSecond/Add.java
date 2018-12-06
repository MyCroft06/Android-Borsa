package com.example.mcroft.myapplication.AddSecond;

public class Add {

    private String money;
    private String typ;

    public Add(String money, String typ) {
        this.money = money;
        this.typ = typ;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
