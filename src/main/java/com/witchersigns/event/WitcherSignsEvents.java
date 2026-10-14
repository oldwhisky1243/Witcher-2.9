package com.witchersigns.event;

import com.witchersigns.sign.SignQuen;
import com.witchersigns.sign.SignYrden;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class WitcherSignsEvents {
    private final SignQuen quen = new SignQuen();
    private final SignYrden yrden = new SignYrden();
    @SubscribeEvent public void onHurt(LivingHurtEvent e) { quen.onHurt(e); }
    @SubscribeEvent public void onPlayerTick(TickEvent.PlayerTickEvent e) { quen.onPlayerTick(e); }
    @SubscribeEvent public void onWorldTick(TickEvent.WorldTickEvent e) { yrden.onWorldTick(e); }
}
