package com.witchersigns.sign;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockGlass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;

public final class SignAard {
    private SignAard() {}
    public static void cast(EntityPlayer p) {
        if (!ready(p, 20)) return;
        Vec3 look = p.getLook(1.0F);
        List list = SignEffects.entitiesInFront(p, 4.0D, 2.3D);
        for (Object o : list) {
            Entity e = (Entity)o;
            if (e instanceof EntityLivingBase && SignEffects.inCone(p, e, 4.5D, 0.35D)) {
                e.addVelocity(look.xCoord * 1.45D, 0.28D + Math.max(0, look.yCoord)*0.35D, look.zCoord * 1.45D);
                e.velocityChanged = true;
                SignEffects.burst(p.worldObj, "crit", e.posX, e.posY + e.height*0.5D, e.posZ, 5, 0.25D);
            }
        }
        for (int i=1;i<=4;i++) {
            int x = (int)Math.floor(p.posX + look.xCoord*i);
            int y = (int)Math.floor(p.posY + p.getEyeHeight() + look.yCoord*i);
            int z = (int)Math.floor(p.posZ + look.zCoord*i);
            Block b = p.worldObj.getBlock(x,y,z);
            if (b == Blocks.glass || b == Blocks.glass_pane || b == Blocks.leaves || b == Blocks.leaves2) {
                if (p.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) p.worldObj.func_147480_a(x,y,z,true);
            }
        }
        SignEffects.burst(p.worldObj, "cloud", p.posX+look.xCoord*2, p.posY+p.getEyeHeight()+look.yCoord*2, p.posZ+look.zCoord*2, 12, 0.35D);
    }
    private static boolean ready(EntityPlayer p, int ticks) { return Cooldowns.ready(p, 0, ticks); }
}
