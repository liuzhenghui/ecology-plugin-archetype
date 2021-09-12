package com.ecplugin.mingmei;

import com.ecplugin.core.EcPluginInitializerBase;
import com.ecplugin.core.config.Globals;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CusPluginInitializer extends EcPluginInitializerBase {
    @Override
    public void init() throws Exception {
        log.info("插件[明美二开插件]自定义初始化");
//        Globals.setDatabaseType("dameng");
    }
}
