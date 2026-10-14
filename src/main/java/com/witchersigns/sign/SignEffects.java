package com.witchersigns.sign;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public final class SignEffects {
    private SignEffects() {}
    public static Vec3 look(EntityPlayer p) {
        return p.getLook(1.0F);
    }
    public static List entitiesInFront(EntityPlayer p, double range, double radius) {
        Vec3 look = look(p);
        double cx = p.posX + look.xCoord * range * 0.5D;
        double cy = p.posY + p.getEyeHeight() + look.yCoord * range * 0.5D;
        double cz = p.posZ + look.zCoord * range * 0.5D;
        AxisAlignedBB box = AxisAlignedBB.getBoundingBox(cx-radius, cy-radius, cz-radius, cx+radius, cy+radius, cz+radius);
        return p.worldObj.getEntitiesWithinAABBExcludingEntity(p, box);
    }
    public static boolean inCone(EntityPlayer p, Entity target, double range, double minDot) {
        Vec3 look = look(p);
        double dx = target.posX - p.posX;
        double dy = target.posY + target.height * 0.5D - (p.posY + p.getEyeHeight());
        double dz = target.posZ - p.posZ;
        double len = Math.sqrt(dx*dx + dy*dy + dz*dz);
        if (len <= 0.001D || len > range) return false;
        double dot = (dx*look.xCoord + dy*look.yCoord + dz*look.zCoord) / len;
        return dot >= minDot;
    }
    public static void particle(World world, String name, double x, double y, double z, double vx, double vy, double vz) {
        if (world instanceof WorldServer) ((WorldServer)world).func_147487_a(name, x, y, z, 1, vx, vy, vz, 0.02D);
    }
    public static void burst(World world, String name, double x, double y, double z, int count, double spread) {
        if (world instanceof WorldServer) ((WorldServer)world).func_147487_a(name, x, y, z, count, spread, spread, spread, 0.02D);
    }
    public static boolean living(Entity e) { return e instanceof EntityLivingBase; }
}
