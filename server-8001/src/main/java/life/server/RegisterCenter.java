package life.server;

import life.server.dto.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class RegisterCenter implements RegisterLifecycle {

    private final Logger logger = LoggerFactory.getLogger(RegisterCenter.class);

    private final ConcurrentHashMap<String,InstanceInfo> register = new ConcurrentHashMap<>();

    @Override
    public boolean register(InstanceInfo info) {
        if(register.containsKey(info.getAppName())){

            /**
             * 服务存在，并且状态为正在运行中，无需重复注册
             */
            InstanceInfo iInfo = register.get(info.getAppName());
            if(iInfo.isStatus()){
                logger.info("名称为{}的服务已经存在了",info.getAppName());
                return false;
            }else{
                /**
                 * 服务注册过，但是因为某种原因，被服务器注销了，现在又重新注入进来
                 */
                iInfo.setLastHeartbeatTime(System.currentTimeMillis());
                iInfo.setStatus(Boolean.TRUE);
                logger.info("重新发现 {} 服务,并将其注入到服务器中",info.getAppName());
                return true;
            }

        }
        info.setLastHeartbeatTime(System.currentTimeMillis());
        register.put(info.getAppName(),info);
        logger.info("发现 {} 服务,并将其注入到服务器中",info.getAppName());
        return true;
    }

    @Override
    public boolean cancel(String name) {
        if(!register.containsKey(name)){
            logger.info("名称为{}的服务不存在，无法注销",name);
            return false;
        }
        InstanceInfo info = register.get(name);

        info.setStatus(Boolean.FALSE);
        logger.info("注销 {} 服务",name);
        return true;
    }

    @Override
    public boolean renew(String name) {
        if(!register.containsKey(name)){
            logger.info("名称为{}的服务不存在，无法心跳更新",name);
            return false;
        }
        InstanceInfo info = register.get(name);
        info.setLastHeartbeatTime(System.currentTimeMillis());
        logger.info("{} 发来心跳信息",name);
        return true;
    }

    public ConcurrentHashMap<String, InstanceInfo> getRegister() {
        return register;
    }
}
