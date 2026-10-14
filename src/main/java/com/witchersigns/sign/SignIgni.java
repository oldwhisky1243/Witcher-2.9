package com.witchersigns.sign;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public final class SignIgni {
    private SignIgni() {}
    public static void cast(EntityPlayer p) {
        if (!Cooldowns.ready(p, 1, 30)) return;
        Vec3 look = p.getLook(1.0F);
        for (int i=1;i<=6;i++) {
            double x=p.posX+look.xCoord*i, y=p.posY+p.getEyeHeight()+look.yCoord*i, z=p.posZ+look.zCoord*i;
            SignEffects.burst(p.worldObj, "flame", x,y,z, 5, 0.18D);
            SignEffects.burst(p.worldObj, "smoke", x,y,z, 2, 0.12D);
        }
        List list = SignEffects.entitiesInFront(p, 4.5D, 2.0D);
        for (Object o : list) {
            Entity e=(Entity)o;
            if (e instanceof EntityLivingBase && SignEffects.inCone(p,e,4.5D,0.62D)) {
                ((EntityLivingBase)e).attackEntityFrom(net.minecraft.util.DamageSource.causePlayerDamage(p), 4.0F);
                e.setFire(3);
            }
        }
    }
}
