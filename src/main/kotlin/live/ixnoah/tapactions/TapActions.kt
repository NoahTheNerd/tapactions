package live.ixnoah.tapactions

import live.ixnoah.tapactions.actions.GeneralActions
import live.ixnoah.tapactions.actions.HudActions
import live.ixnoah.tapactions.actions.WorldActions
import live.ixnoah.tapactions.commands.CreateActionCommand
import live.ixnoah.tapactions.events.ClientTick
import live.ixnoah.tapactions.events.WorldLoad
import net.minecraft.client.Minecraft
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = "tapactions", useMetadata = true)
class TapActions {
    companion object ModInfo {
        const val MOD_ID = "tapactions"
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        try {
            val resource: net.minecraft.client.resources.IResource = Minecraft.getMinecraft().resourceManager
                .getResource(net.minecraft.util.ResourceLocation("test:test.txt"))
            org.apache.commons.io.IOUtils.copy(resource.inputStream, java.lang.System.out)
        } catch (e: java.io.IOException) {
            throw java.lang.RuntimeException(e)
        }
        // Commands
        ClientCommandHandler.instance.registerCommand(CreateActionCommand())

        // Events
        MinecraftForge.EVENT_BUS.register(WorldLoad())
        MinecraftForge.EVENT_BUS.register(ClientTick())

        // Deploy actions
        GeneralActions.deploy()
        WorldActions.deploy()
        HudActions.deploy()
    }
}
