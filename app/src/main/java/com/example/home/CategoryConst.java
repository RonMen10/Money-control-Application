package com.example.home;

public class CategoryConst {
    int category_id;
    int Amount;

    public String category_name;
    public String recurrency;
    public String date;
    public int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setRecurrencyOfBudget(String recurrency) {
        this.recurrency = recurrency;
    }

    public String getRecurrencyOfBudget() {
        return recurrency;
    }
    public void setDateOfBudget(String dateLocal) {
        this.date = dateLocal;
    }

    public String getDateOfBudget() {
        return date;
    }


}
