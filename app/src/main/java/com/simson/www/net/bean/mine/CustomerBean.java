package com.simson.www.net.bean.mine;

public class CustomerBean {
    /**
     * card_amount : 0.00
     * points : 10
     * follows : 0
     * fans : 2
     * subscribes : 1
     * alopecias : 1
     * unreads : 0
     */

    private String card_amount;
    private int points;
    private int follows;
    private int fans;
    private int subscribes;
    private int alopecias;
    private int unreads;

    public String getCard_amount() {
        return card_amount;
    }

    public void setCard_amount(String card_amount) {
        this.card_amount = card_amount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(int subscribes) {
        this.subscribes = subscribes;
    }

    public int getAlopecias() {
        return alopecias;
    }

    public void setAlopecias(int alopecias) {
        this.alopecias = alopecias;
    }

    public int getUnreads() {
        return unreads;
    }

    public void setUnreads(int unreads) {
        this.unreads = unreads;
    }
}
