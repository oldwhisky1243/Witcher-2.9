package com.witchersigns;

import com.witchersigns.sign.*;

public final class SignRegistry {
    public static final byte AARD = 0, IGNI = 1, QUEN = 2, YRDEN = 3, AXII = 4;
    private SignRegistry() {}
    public static void cast(byte id, net.minecraft.entity.player.EntityPlayer player) {
        if (player == null || player.worldObj.isRemote) return;
        switch (id) {
            case AARD: SignAard.cast(player); break;
            case IGNI: SignIgni.cast(player); break;
            case QUEN: SignQuen.cast(player); break;
            case YRDEN: SignYrden.cast(player); break;
            case AXII: SignAxii.cast(player); break;
            default: break;
        }
    }
}
