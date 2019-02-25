package com.stock.domain;

import java.util.Date;
import javax.persistence.*;

public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户id
     */
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 加工坊id
     */
    @Column(name = "workshop_id")
    private Integer workshopId;

    /**
     * 库存id
     */
    @Column(name = "stock_id")
    private Integer stockId;

    /**
     * 0：发货，1：退款
     */
    private String type;

    /**
     * 数量，单位公斤
     */
    private Float num;

    /**
     * 单价，单位分
     */
    private Float price;

    private String remarks;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "create_time")
    private Date createTime;

    private String status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取客户id
     *
     * @return customer_id - 客户id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户id
     *
     * @param customerId 客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取加工坊id
     *
     * @return workshop_id - 加工坊id
     */
    public Integer getWorkshopId() {
        return workshopId;
    }

    /**
     * 设置加工坊id
     *
     * @param workshopId 加工坊id
     */
    public void setWorkshopId(Integer workshopId) {
        this.workshopId = workshopId;
    }

    /**
     * 获取库存id
     *
     * @return stock_id - 库存id
     */
    public Integer getStockId() {
        return stockId;
    }

    /**
     * 设置库存id
     *
     * @param stockId 库存id
     */
    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    /**
     * 获取0：发货，1：退款
     *
     * @return type - 0：发货，1：退款
     */
    public String getType() {
        return type;
    }

    /**
     * 设置0：发货，1：退款
     *
     * @param type 0：发货，1：退款
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取数量，单位公斤
     *
     * @return num - 数量，单位公斤
     */
    public Float getNum() {
        return num;
    }

    /**
     * 设置数量，单位公斤
     *
     * @param num 数量，单位公斤
     */
    public void setNum(Float num) {
        this.num = num;
    }

    /**
     * 获取单价，单位分
     *
     * @return price - 单价，单位分
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置单价，单位分
     *
     * @param price 单价，单位分
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}