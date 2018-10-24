package com.simson.www.net.bean.home;

public class TechnologyBean {

    /**
     * technology_id : 124
     * hospital_id : 1
     * technology_name : FUE
     * unit_price : 1.00
     */

    private String technology_id;
    private String hospital_id;
    private String technology_name;
    private String unit_price;

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

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }
}
