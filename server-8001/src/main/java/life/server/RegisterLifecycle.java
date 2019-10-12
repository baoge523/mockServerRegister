package life.server;

import life.server.dto.InstanceInfo;

public interface RegisterLifecycle {
    /**
     * 注册
     * @param info
     * @return
     */
    boolean register(InstanceInfo info);

    /**
     * 注销
     * @param name
     * @return
     */
    boolean cancel(String name);

    /**
     * 心跳
     * @return
     */
    boolean renew(String name);
}
