
package xyz.sk7z.main;

import net.minecraft.client.Minecraft;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@SideOnly(Side.CLIENT)

public class EntityCounterCore {

    @Instance
    public static EntityCounterCore instance;
    public static Logger logger;
    private Minecraft mc;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        this.mc = FMLClientHandler.instance().getClient();
        MinecraftForge.EVENT_BUS.register(new EntityCounter(mc));
        MinecraftForge.EVENT_BUS.register(this);
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
