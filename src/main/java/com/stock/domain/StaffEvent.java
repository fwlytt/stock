package com.stock.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "staff_event")
public class StaffEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "staff_id")
    private Integer staffId;

    /**
     * 事件类型；0：开工时间；1：工资发放；2：休息天数
     */
    private String type;

    /**
     * 事件时间
     */
    @Column(name = "event_date")
    private String eventDate;

    /**
     * 工资或休息天数
     */
    @Column(name = "event_num")
    private Integer eventNum;

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

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取事件类型；0：开工时间；1：工资发放；2：休息天数
     *
     * @return type - 事件类型；0：开工时间；1：工资发放；2：休息天数
     */
    public String getType() {
        return type;
    }

    /**
     * 设置事件类型；0：开工时间；1：工资发放；2：休息天数
     *
     * @param type 事件类型；0：开工时间；1：工资发放；2：休息天数
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取事件时间
     *
     * @return event_date - 事件时间
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * 设置事件时间
     *
     * @param eventDate 事件时间
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate == null ? null : eventDate.trim();
    }

    /**
     * 获取工资或休息天数
     *
     * @return event_num - 工资或休息天数
     */
    public Integer getEventNum() {
        return eventNum;
    }

    /**
     * 设置工资或休息天数
     *
     * @param eventNum 工资或休息天数
     */
    public void setEventNum(Integer eventNum) {
        this.eventNum = eventNum;
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