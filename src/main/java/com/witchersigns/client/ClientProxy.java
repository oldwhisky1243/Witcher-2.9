package com.witchersigns.client;

import com.witchersigns.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {
    @Override public void init() {
        FMLCommonHandler.instance().bus().register(new ClientKeyHandler());
    }
}
