package com.witchersigns;

import com.witchersigns.network.SignPacketHandler;
import com.witchersigns.event.WitcherSignsEvents;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.SidedProxy;

@Mod(modid = WitcherSigns.MODID, name = "WitcherSigns", version = WitcherSigns.VERSION, acceptedMinecraftVersions = "[1.7.10]")
public class WitcherSigns {
    public static final String MODID = "witchersigns";
    public static final String VERSION = "1.0.0";
    @Mod.Instance(MODID) public static WitcherSigns instance;
    @SidedProxy(clientSide = "com.witchersigns.client.ClientProxy", serverSide = "com.witchersigns.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        SignPacketHandler.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(new WitcherSignsEvents());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new Object());
        proxy.init();
    }
}
