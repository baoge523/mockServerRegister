package life.server.controller;

import life.server.RegisterCenter;
import life.server.dto.HeartbeatResponse;
import life.server.dto.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Callable;

@RestController
public class IndexController {


    @Autowired
    private RegisterCenter center;

    @RequestMapping("/show")
    public Map<String,InstanceInfo> show(){
        return center.getRegister();
    }

    @RequestMapping("/ok")
    public String ok(){
        System.out.println("ok 了");
        return "ok";
    }

    /**
     * 心跳
     */
    @RequestMapping("/heartbeat")
    public HeartbeatResponse heartbeat(final String appName){
        System.out.println(appName+" 服务发来心跳");
        HeartbeatResponse response = null;
        try {
            response = new Callable<HeartbeatResponse>(){
                @Override
                public HeartbeatResponse call() throws Exception {
                    boolean renew = center.renew(appName);
                    if(renew){
                        return new HeartbeatResponse("success",HttpStatus.OK.value());
                    }
                    return new HeartbeatResponse("fail",HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            }.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


}
