package com.simson.www.net.bean.mine;

import java.util.List;

public class OrderBean {

    /**
     * order_id : HT201809291538200117426632252534
     * customer_id : 2018090115357871316905625
     * transaction_money : 888.00
     * transaction_point : 0
     * transaction_type : 1
     * transaction_status : 1
     * delivery_id :
     * create_time : 2018-09-29
     * items : [{"item_id":"2018007","price":"888.00","point":0,"buy_num":1,"is_point":0,"item_name":"【头顶加密种植】【私信领红包 送保险】无痕种植，个性定制，美丽从\"头\"做起","item_icon":"http://58.213.14.195:8081/upload/item/kunmingtoudingjiami/1.jpg","original_price":"1065.60","present_price":"888.00","is_delivery":0}]
     */

    private String order_id;
    private String customer_id;
    private double transaction_money;
    private int transaction_point;
    private int transaction_type;
    private int transaction_status;
    private String delivery_id;
    private String create_time;
    private List<ItemsBean> items;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public double getTransaction_money() {
        return transaction_money;
    }

    public void setTransaction_money(double transaction_money) {
        this.transaction_money = transaction_money;
    }

    public int getTransaction_point() {
        return transaction_point;
    }

    public void setTransaction_point(int transaction_point) {
        this.transaction_point = transaction_point;
    }

    public int getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(int transaction_type) {
        this.transaction_type = transaction_type;
    }

    public int getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(int transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * item_id : 2018007
         * price : 888.00
         * point : 0
         * buy_num : 1
         * is_point : 0
         * item_name : 【头顶加密种植】【私信领红包 送保险】无痕种植，个性定制，美丽从"头"做起
         * item_icon : http://58.213.14.195:8081/upload/item/kunmingtoudingjiami/1.jpg
         * original_price : 1065.60
         * present_price : 888.00
         * is_delivery : 0
         */

        private String item_id;
        private String price;
        private int point;
        private int buy_num;
        private int is_point;
        private String item_name;
        private String item_icon;
        private String original_price;
        private String present_price;
        private int is_delivery;
        /**
         * order_item_id : 9e7b249dc12c432eaa820e3a48694d07
         * is_technology : 1
         */

        private String order_item_id;
        private int is_technology;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(int buy_num) {
            this.buy_num = buy_num;
        }

        public int getIs_point() {
            return is_point;
        }

        public void setIs_point(int is_point) {
            this.is_point = is_point;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_icon() {
            return item_icon;
        }

        public void setItem_icon(String item_icon) {
            this.item_icon = item_icon;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getPresent_price() {
            return present_price;
        }

        public void setPresent_price(String present_price) {
            this.present_price = present_price;
        }

        public int getIs_delivery() {
            return is_delivery;
        }

        public void setIs_delivery(int is_delivery) {
            this.is_delivery = is_delivery;
        }

        public String getOrder_item_id() {
            return order_item_id;
        }

        public void setOrder_item_id(String order_item_id) {
            this.order_item_id = order_item_id;
        }

        public int getIs_technology() {
            return is_technology;
        }

        public void setIs_technology(int is_technology) {
            this.is_technology = is_technology;
        }
    }
}
