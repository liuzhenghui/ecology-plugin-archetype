#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package com.ecplugin.${packageName};

import com.ecplugin.core.EcPluginInitializerBase;
import com.ecplugin.core.config.Globals;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CusPluginInitializer extends EcPluginInitializerBase {
    @Override
    public void init() throws Exception {
        log.info("插件[${pluginName}]自定义初始化");
//        Globals.setDatabaseType("dameng");
    }
}
