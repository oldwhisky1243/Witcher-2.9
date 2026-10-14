package com.witchersigns.sign;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

final class Cooldowns {
    private static final Map<String, Long> LAST = new HashMap<String, Long>();
    private Cooldowns() {}
    static boolean ready(EntityPlayer p, int sign, int ticks) {
        String key=p.getUniqueID().toString()+":"+sign;
        long now=p.worldObj.getTotalWorldTime();
        Long previous=LAST.get(key);
        if(previous!=null && now-previous.longValue()<ticks) return false;
        LAST.put(key,Long.valueOf(now));
        if(LAST.size()>4096) LAST.clear();
        return true;
    }
}
