package com.witchersigns.network;

import com.witchersigns.SignRegistry;
import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public final class SignPacketHandler {
    public static SimpleNetworkWrapper CHANNEL;
    private SignPacketHandler() {}
    public static void init() {
        CHANNEL = NetworkRegistryHolder.create();
        CHANNEL.registerMessage(CastHandler.class, CastMessage.class, 0, Side.SERVER);
    }
    private static final class NetworkRegistryHolder {
        static SimpleNetworkWrapper create() {
            return cpw.mods.fml.common.network.NetworkRegistry.INSTANCE.newSimpleChannel("WitcherSigns");
        }
    }
    public static class CastMessage implements IMessage {
        private byte sign;
        public CastMessage() {}
        public CastMessage(byte sign) { this.sign = sign; }
        public void fromBytes(ByteBuf buf) { sign = buf.readByte(); }
        public void toBytes(ByteBuf buf) { buf.writeByte(sign); }
    }
    public static class CastHandler implements IMessageHandler<CastMessage, IMessage> {
        public IMessage onMessage(CastMessage message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if (player != null) SignRegistry.cast(message.sign, player);
            return null;
        }
    }
}
