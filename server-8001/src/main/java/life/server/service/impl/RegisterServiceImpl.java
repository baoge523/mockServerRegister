package life.server.service.impl;

import life.server.RegisterCenter;
import life.server.dto.InstanceInfo;
import life.server.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterCenter center;

    @Override
    public boolean register(InstanceInfo info) {
        return center.register(info);
    }

    @Override
    public boolean cancel(String name) {
        return center.cancel(name);
    }

    @Override
    public boolean renew(String name) {
        return center.renew(name);
    }
}
