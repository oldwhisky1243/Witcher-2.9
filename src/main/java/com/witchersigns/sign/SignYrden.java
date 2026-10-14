package com.witchersigns.sign;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public final class SignYrden {
    private static final List<Zone> ZONES = new ArrayList<Zone>();
    public SignYrden() {}
    public static void cast(EntityPlayer p) {
        if (!Cooldowns.ready(p, 3, 100)) return;
        Vec3 look=p.getLook(1.0F);
        double x=p.posX+look.xCoord*2.0D, z=p.posZ+look.zCoord*2.0D;
        ZONES.add(new Zone(p.worldObj.provider.dimensionId,x, p.posY, z, p.worldObj.getTotalWorldTime()+160L));
        SignEffects.burst(p.worldObj,"magicCrit",x,p.posY+0.05D,z,18,1.0D);
    }
    @SubscribeEvent public void onWorldTick(TickEvent.WorldTickEvent e) {
        if (e.phase != TickEvent.Phase.END || e.world.isRemote) return;
        Iterator<Zone> it=ZONES.iterator();
        while(it.hasNext()) {
            Zone z=it.next();
            if (z.dimension != e.world.provider.dimensionId || e.world.getTotalWorldTime()>z.end) { if(e.world.provider.dimensionId==z.dimension) it.remove(); continue; }
            if (e.world.getTotalWorldTime()%10L==0L) SignEffects.burst(e.world,"magicCrit",z.x,z.y+0.04D,z.z,12,0.85D);
            List entities=e.world.getEntitiesWithinAABB(EntityLivingBase.class, net.minecraft.util.AxisAlignedBB.getBoundingBox(z.x-2.2D,z.y-1.0D,z.z-2.2D,z.x+2.2D,z.y+2.5D,z.z+2.2D));
            for(Object o:entities) {
                EntityLivingBase mob=(EntityLivingBase)o;
                double dx=mob.posX-z.x, dz=mob.posZ-z.z;
                if(dx*dx+dz*dz<=4.84D) mob.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,25,4,true));
            }
        }
    }
    private static class Zone {
        final int dimension; final double x,y,z; final long end;
        Zone(int d,double x,double y,double z,long end){this.dimension=d;this.x=x;this.y=y;this.z=z;this.end=end;}
    }
}
