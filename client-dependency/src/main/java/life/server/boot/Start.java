package life.server.boot;


import life.server.http.Session;
import life.server.prop.ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;
import java.util.TimerTask;

public class Start {

    @Autowired
    private ClientProperties properties;


    public Start(){

    }


    /**
     * 执行心跳
     */
    public void init(){
        Session.post(properties.getDefaultZone(),HeartbearData.heartbeatMap(properties.getAppName(),properties.getHostName()+":"+properties.getPort()));
        System.out.println("建立连接");
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Session.get(parse());
                System.out.println("发送心跳");
            }
        }, 0L, properties.getCycle());
    }


    public String parse(){
        String url = properties.getDefaultZone();
        String[] split = url.split("/");
        url = "http://"+split[2]+"/heartbeat?appName="+properties.getAppName();
        return url;
    }
}
