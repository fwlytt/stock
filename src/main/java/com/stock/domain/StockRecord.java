package com.stock.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import javax.persistence.*;

@Table(name = "stock_record")
public class StockRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stock_id")
    private Integer stockId;

    /**
     * 出入库记录；0：入库；1：出库
     */
    private String type;

    /**
     * 出入库数量
     */
    private Integer num;

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
     * @return stock_id
     */
    public Integer getStockId() {
        return stockId;
    }

    /**
     * @param stockId
     */
    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    /**
     * 获取出入库记录；0：入库；1：出库
     *
     * @return type - 出入库记录；0：入库；1：出库
     */
    public String getType() {
        return type;
    }

    /**
     * 设置出入库记录；0：入库；1：出库
     *
     * @param type 出入库记录；0：入库；1：出库
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取出入库数量
     *
     * @return num - 出入库数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置出入库数量
     *
     * @param num 出入库数量
     */
    public void setNum(Integer num) {
        this.num = num;
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
}