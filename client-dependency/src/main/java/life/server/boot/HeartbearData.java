package life.server.boot;

import java.util.HashMap;
import java.util.Map;

public class HeartbearData {
    public static Map<String,String> heartbeatMap(String appName,String address){
        Map<String,String> map = new HashMap<>();
        map.put("appName",appName);
        map.put("address",address);
        return map;
    }
}
