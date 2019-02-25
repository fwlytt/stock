package com.stock.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 进货商品名
     */
    @Column(name = "purchase_name")
    private String purchaseName;

    /**
     * 进货商名
     */
    private String channel;

    /**
     * 数量
     */
    private Float num;

    /**
     * 单价
     */
    private Float price;

    /**
     * 备注
     */
    private String remarks;

    @JSONField(format="yyyy-MM-dd hh:mm")
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取进货商品名
     *
     * @return purchase_name - 进货商品名
     */
    public String getPurchaseName() {
        return purchaseName;
    }

    /**
     * 设置进货商品名
     *
     * @param purchaseName 进货商品名
     */
    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName == null ? null : purchaseName.trim();
    }

    /**
     * 获取进货商名
     *
     * @return channel - 进货商名
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置进货商名
     *
     * @param channel 进货商名
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Float getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Float num) {
        this.num = num;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}