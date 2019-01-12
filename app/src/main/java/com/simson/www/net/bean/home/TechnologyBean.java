package com.simson.www.net.bean.home;

public class TechnologyBean {

    /**
     * technology_id : 124
     * hospital_id : 1
     * technology_name : FUE
     * unit_price : 1.00
     */
    public boolean isCheck;
    private String technology_id;
    private String hospital_id;
    private String technology_name;
    private String technology_type;//1部位种植；2毛囊种植;
    private String planting_number;//technology_type为1，planting_number为1个；technology_type为2，planting_number为多少个毛囊
    private double unit_price;

    public String getTechnology_type() {
        return technology_type;
    }

    public void setTechnology_type(String technology_type) {
        this.technology_type = technology_type;
    }

    public String getPlanting_number() {
        return planting_number;
    }

    public void setPlanting_number(String planting_number) {
        this.planting_number = planting_number;
    }

    public String getTechnology_id() {
        return technology_id;
    }

    public void setTechnology_id(String technology_id) {
        this.technology_id = technology_id;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getTechnology_name() {
        return technology_name;
    }

    public void setTechnology_name(String technology_name) {
        this.technology_name = technology_name;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}
