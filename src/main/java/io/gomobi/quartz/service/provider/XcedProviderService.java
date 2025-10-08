package io.gomobi.quartz.service.provider;

import io.gomobi.quartz.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class XcedProviderService implements ProviderService {


    @Override
    public boolean saveAll() {
        return false;
    }
}
