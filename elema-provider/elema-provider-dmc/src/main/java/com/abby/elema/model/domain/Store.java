package com.abby.elema.model.domain;

public class Store {
    private Integer id;

    private String name;

    private String localton;

    private String pic;

    private Integer sellerId;

    private String sellerName;

    private String notifications;

    private Integer score;

    private Integer mothlySales;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalton() {
        return localton;
    }

    public void setLocalton(String localton) {
        this.localton = localton;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMothlySales() {
        return mothlySales;
    }

    public void setMothlySales(Integer mothlySales) {
        this.mothlySales = mothlySales;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}