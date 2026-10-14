package com.witchersigns.client;

import com.witchersigns.network.SignPacketHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class ClientKeyHandler {
    private static final KeyBinding AARD = new KeyBinding("key.witchersigns.aard", Keyboard.KEY_Z, "key.categories.witchersigns");
    private static final KeyBinding IGNI = new KeyBinding("key.witchersigns.igni", Keyboard.KEY_X, "key.categories.witchersigns");
    private static final KeyBinding QUEN = new KeyBinding("key.witchersigns.quen", Keyboard.KEY_C, "key.categories.witchersigns");
    private static final KeyBinding YRDEN = new KeyBinding("key.witchersigns.yrden", Keyboard.KEY_V, "key.categories.witchersigns");
    private static final KeyBinding AXII = new KeyBinding("key.witchersigns.axii", Keyboard.KEY_B, "key.categories.witchersigns");
    public ClientKeyHandler() {
        ClientRegistry.registerKeyBinding(AARD); ClientRegistry.registerKeyBinding(IGNI);
        ClientRegistry.registerKeyBinding(QUEN); ClientRegistry.registerKeyBinding(YRDEN);
        ClientRegistry.registerKeyBinding(AXII);
    }
    @SubscribeEvent public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (AARD.isPressed()) cast((byte)0);
        if (IGNI.isPressed()) cast((byte)1);
        if (QUEN.isPressed()) cast((byte)2);
        if (YRDEN.isPressed()) cast((byte)3);
        if (AXII.isPressed()) cast((byte)4);
    }
    private void cast(byte sign) {
        if (SignPacketHandler.CHANNEL != null) SignPacketHandler.CHANNEL.sendToServer(new SignPacketHandler.CastMessage(sign));
    }
}
