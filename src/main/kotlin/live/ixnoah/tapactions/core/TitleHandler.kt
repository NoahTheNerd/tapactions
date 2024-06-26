package live.ixnoah.tapactions.core

import net.minecraft.network.play.server.S45PacketTitle
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

object TitleHandler {
    fun handleTitle(packetIn: S45PacketTitle, ci: CallbackInfo) {
        if (packetIn.type.toString() != "TITLE") return
        var messageContent = packetIn.message.unformattedText
        if (!messageContent.startsWith("␁")) return

        ci.cancel()

        messageContent = messageContent.replace("␁", "")
        var components = messageContent.split('␟').filter { data -> data.isNotEmpty() }


        val actionName = components.getOrNull(0) ?: "void"
        components = components.drop(1)

        val actionParams = mutableMapOf<String, String>()

        components.map { paramData ->
            val keyValue : List<String?> = paramData.replaceFirst('=', '␃').split('␃')
            actionParams[keyValue.getOrNull(0) ?: "void"] = keyValue.getOrNull(1) ?: "void"
        }

        ActionManager.runAction(actionName, actionParams, true)
    }
}