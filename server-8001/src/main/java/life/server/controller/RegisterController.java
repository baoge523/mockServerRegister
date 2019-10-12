package life.server.controller;


import life.server.RegisterCenter;
import life.server.dto.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private RegisterCenter center;

    @RequestMapping("/register")
    public void serverRegister(@RequestBody Map<String,String> map){
        String appName = map.get("appName");
        String address = map.get("address");
        System.out.println("调用注册方法。。");
        InstanceInfo info = new InstanceInfo(appName,address);
        center.register(info);
    }

}
