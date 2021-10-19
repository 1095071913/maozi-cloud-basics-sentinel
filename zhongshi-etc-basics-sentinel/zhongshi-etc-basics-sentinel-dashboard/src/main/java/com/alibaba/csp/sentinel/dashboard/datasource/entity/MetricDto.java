package com.alibaba.csp.sentinel.dashboard.datasource.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 2230
 *
 */
@Entity
@Table(name = "sentinel_metric")
@Data
public class MetricDto implements Serializable {

    private static final long serialVersionUID = 7200023615444172715L;

    /**id，主键*/
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**创建时间*/
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**修改时间*/
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**应用名称*/
    @Column(name = "app")
    private String app;

    /**统计时间*/
    @Column(name = "timestamp")
    private Date timestamp;

    /**资源名称*/
    @Column(name = "resource")
    private String resource;

    /**通过qps*/
    @Column(name = "pass_qps")
    private Long passQps;

    /**成功qps*/
    @Column(name = "success_qps")
    private Long successQps;

    /**限流qps*/
    @Column(name = "block_qps")
    private Long blockQps;

    /**发送异常的次数*/
    @Column(name = "exception_qps")
    private Long exceptionQps;

    /**所有successQps的rt的和*/
    @Column(name = "rt")
    private Double rt;

    /**本次聚合的总条数*/
    @Column(name = "_count")
    private Integer count;

    /**资源的hashCode*/
    @Column(name = "resource_code")
    private Integer resourceCode;

   // get  set 方法省略

}