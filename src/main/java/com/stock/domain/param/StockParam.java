package com.stock.domain.param;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class StockParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 型号
     */
    private String name;

    /**
     * 类别
     */
    private String type;

    /**
     * 箱数
     */
    private Integer num;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 生产日期间隔
     */
    @Column(name = "produce_day")
    private Integer produceDay;

    /**
     * 当前天数
     */
    @Column(name = "now_day")
    private Integer nowDay;

    /**
     * 生产数量
     */
    @Column(name = "produce_num")
    private Integer produceNum;

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
     * 获取型号
     *
     * @return name - 型号
     */
    public String getName() {
        return name;
    }

    /**
     * 设置型号
     *
     * @param name 型号
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取类别
     *
     * @return type - 类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类别
     *
     * @param type 类别
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取箱数
     *
     * @return num - 箱数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置箱数
     *
     * @param num 箱数
     */
    public void setNum(Integer num) {
        this.num = num;
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

    /**
     * 获取生产日期间隔
     *
     * @return produce_day - 生产日期间隔
     */
    public Integer getProduceDay() {
        return produceDay;
    }

    /**
     * 设置生产日期间隔
     *
     * @param produceDay 生产日期间隔
     */
    public void setProduceDay(Integer produceDay) {
        this.produceDay = produceDay;
    }

    /**
     * 获取当前天数
     *
     * @return now_day - 当前天数
     */
    public Integer getNowDay() {
        return nowDay;
    }

    /**
     * 设置当前天数
     *
     * @param nowDay 当前天数
     */
    public void setNowDay(Integer nowDay) {
        this.nowDay = nowDay;
    }

    /**
     * 获取生产数量
     *
     * @return produce_num - 生产数量
     */
    public Integer getProduceNum() {
        return produceNum;
    }

    /**
     * 设置生产数量
     *
     * @param produceNum 生产数量
     */
    public void setProduceNum(Integer produceNum) {
        this.produceNum = produceNum;
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