package com.witchersigns.sign;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public final class SignAxii {
    private SignAxii() {}
    public static void cast(EntityPlayer p) {
        if (!Cooldowns.ready(p, 4, 80)) return;
        EntityLivingBase target = findTarget(p);
        if (target == null) {
            SignEffects.burst(p.worldObj,"magicCrit",p.posX+p.getLook(1.0F).xCoord*2,p.posY+p.getEyeHeight(),p.posZ+p.getLook(1.0F).zCoord*2,6,0.2D);
            return;
        }
        target.setAttackTarget(null);
        target.setRevengeTarget(null);
        target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,100,5,true));
        target.addPotionEffect(new PotionEffect(Potion.weakness.id,100,1,true));
        SignEffects.burst(p.worldObj,"magicCrit",target.posX,target.posY+target.height*0.6D,target.posZ,16,0.35D);
    }
    private static EntityLivingBase findTarget(EntityPlayer p) {
        List list=SignEffects.entitiesInFront(p,7.0D,2.0D);
        EntityLivingBase best=null; double bestDistance=Double.MAX_VALUE;
        for(Object o:list) {
            Entity e=(Entity)o;
            if(e instanceof EntityLivingBase && !(e instanceof EntityPlayer) && SignEffects.inCone(p,e,7.0D,0.92D)) {
                double d=p.getDistanceSqToEntity(e);
                if(d<bestDistance && p.canEntityBeSeen(e)) { best=(EntityLivingBase)e; bestDistance=d; }
            }
        }
        return best;
    }
}
