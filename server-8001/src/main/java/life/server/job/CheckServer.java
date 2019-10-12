package life.server.job;

import life.server.RegisterCenter;
import life.server.dto.InstanceInfo;
import life.server.prop.CheckServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CheckServer{

    private final Logger logger = LoggerFactory.getLogger(CheckServer.class);

    /**
     * 注册中心
     */
    @Autowired
    private RegisterCenter center;

    @Autowired
    private CheckServerProperties properties;


    public void start(){
        System.out.println("定时器启动..");
        Timer timer = new Timer();
        timer.schedule(new CheckTask(),0L,properties.getCycle());
    }

     public class CheckTask extends TimerTask{
         @Override
         public void run() {
             logger.info("定时任务进行中..");
             ConcurrentHashMap<String, InstanceInfo> register = center.getRegister();  // 获取注册表
             Collection<InstanceInfo> values = register.values(); // 获取其集合
             List<String> names = values.stream()
                     .filter(InstanceInfo::isStatus)  // 按状态过滤，只要true的
                     .filter((e) -> System.currentTimeMillis() - e.getLastHeartbeatTime() > properties.getMaxTime()) //按心跳最大间隔过滤，间隔大于指定值的
                     .map(InstanceInfo::getAppName)  // 将名称映射出来
                     .collect(Collectors.toList());  // 转换成集合
             if(names != null && names.size() > 0){
                 names.stream().forEach((name) -> {
                     center.cancel(name);  // 遍历执行
                 });
             }
         }
     }

}
