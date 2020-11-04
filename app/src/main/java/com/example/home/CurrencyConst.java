package com.example.home;

public class CurrencyConst {

    int CurrencyID;
    String Currency;


    public int getCurrencyID()
    {
        return CurrencyID;
    }
    public void setCurrencyID(int currencyid)
    {
        this.CurrencyID=currencyid;
    }
    public String getCurrencyName()
    {
         return Currency;
    }
    public void setCurrencyName(String currency)
    {
        this.Currency=currency;
    }
}