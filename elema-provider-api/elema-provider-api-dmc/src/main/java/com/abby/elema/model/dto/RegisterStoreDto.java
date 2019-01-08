package com.abby.elema.model.dto;

import java.io.Serializable;

/**
 * @author: Abby
 */
public class RegisterStoreDto implements Serializable {

    private String name;
    private String location;
    private String pic;
    private int seller_id;
    private String seller_name;
    private String notifications;
    private int score;
    private int monthly_sales;
    private String status;

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setLocation(String location){
        this.location=location;
    }

    public String getLocation(){
        return location;
    }

    public void setPic(String pic){
        this.pic=pic;
    }

    public String getPic(){
        return pic;
    }

    public void setSellerId(int id){
        seller_id=id;
    }

    public int getSeller_id(){
        return seller_id;
    }

    public void setSellerName(String name){
        seller_name=name;
    }

    public String getSellerName(){
        return seller_name;
    }

    public void setNotifications(String notifications){
        this.notifications=notifications;
    }

    public String getNotifications(){
        return notifications;
    }

    public void setScore(int score){
        this.score=score;
    }

    public int getScore(){
        return score;
    }

    public void setMonthlySales(int sales){
        monthly_sales=sales;
    }

    public int getMonthlySales(){
        return monthly_sales;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getStatus(){
        return status;
    }
}
