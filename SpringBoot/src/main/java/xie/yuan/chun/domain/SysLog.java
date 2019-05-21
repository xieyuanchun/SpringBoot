package xie.yuan.chun.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_log")
public class SysLog implements Serializable {
    @Id
    @Column(name = "log_id")
    private Long logId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行时长(毫秒)
     */
    private Long time;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 新增时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 编辑时间
     */
    @Column(name = "edit_time")
    private Date editTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return log_id
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取用户操作
     *
     * @return operation - 用户操作
     */
    public String getOperation() {
        return operation;
    }

    /**
     * 设置用户操作
     *
     * @param operation 用户操作
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    /**
     * 获取请求方法
     *
     * @return method - 请求方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置请求方法
     *
     * @param method 请求方法
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 获取请求参数
     *
     * @return params - 请求参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置请求参数
     *
     * @param params 请求参数
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * 获取执行时长(毫秒)
     *
     * @return time - 执行时长(毫秒)
     */
    public Long getTime() {
        return time;
    }

    /**
     * 设置执行时长(毫秒)
     *
     * @param time 执行时长(毫秒)
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * 获取IP地址
     *
     * @return ip - IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取新增时间
     *
     * @return add_time - 新增时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置新增时间
     *
     * @param addTime 新增时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取编辑时间
     *
     * @return edit_time - 编辑时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 设置编辑时间
     *
     * @param editTime 编辑时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}