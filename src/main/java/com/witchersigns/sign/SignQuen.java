package com.witchersigns.sign;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public final class SignQuen {
    private static final Map<UUID, Long> SHIELDS = new HashMap<UUID, Long>();
    public SignQuen() {}
    public static void cast(EntityPlayer p) {
        if (!Cooldowns.ready(p, 2, 80)) return;
        SHIELDS.put(p.getUniqueID(), Long.valueOf(p.worldObj.getTotalWorldTime()+200L));
        SignEffects.burst(p.worldObj, "portal", p.posX, p.posY+1.0D, p.posZ, 24, 0.7D);
    }
    @SubscribeEvent public void onHurt(LivingHurtEvent e) {
        if (!(e.entityLiving instanceof EntityPlayer)) return;
        EntityPlayer p=(EntityPlayer)e.entityLiving;
        Long end=SHIELDS.get(p.getUniqueID());
        if (end != null && p.worldObj.getTotalWorldTime() <= end.longValue() && e.ammount > 0) {
            e.ammount=0;
            SHIELDS.remove(p.getUniqueID());
            SignEffects.burst(p.worldObj, "portal", p.posX, p.posY+1.0D, p.posZ, 30, 0.9D);
        }
    }
    @SubscribeEvent public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase != TickEvent.Phase.END || e.player.worldObj.isRemote) return;
        Long end=SHIELDS.get(e.player.getUniqueID());
        if (end != null) {
            if (e.player.worldObj.getTotalWorldTime() > end.longValue()) SHIELDS.remove(e.player.getUniqueID());
            else if (e.player.worldObj.getTotalWorldTime()%8L==0L) SignEffects.burst(e.player.worldObj,"portal",e.player.posX,e.player.posY+1.0D,e.player.posZ,3,0.3D);
        }
    }
}
