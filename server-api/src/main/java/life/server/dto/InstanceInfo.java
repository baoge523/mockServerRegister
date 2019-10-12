package life.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 客户端注册时：需要的信息
 */
@Data
public class InstanceInfo implements Serializable {

    /**
     * 服务名称-客服端名称
     */
    private String appName;
    /**
     * 注册地址 ip:port
     */
    private String address;
    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private String port;

    /**
     *  当前服务状态
     *  false： 注册过，但现在不提供服务
     *  true ： 正在提供服务
     */
    private boolean status;

    /**
     * 服务上次心跳时间
     */
    private long lastHeartbeatTime;


    public InstanceInfo(String appName,String address){
        parseAddress(address);
        this.address = address;
        this.appName = appName;
        this.status = true;
    }

    private void parseAddress(String address){
        String[] split = address.split(":");
        this.ip = split[0];
        this.port = split[1];
    }



}
