package me.atroxego.pauladdons;

import me.atroxego.pauladdons.commands.MoveCommand;
import me.atroxego.pauladdons.commands.PaulAddonsGuiCommand;
import me.atroxego.pauladdons.commands.ScaleCommand;
import me.atroxego.pauladdons.commands.ToggleCommand;
import me.atroxego.pauladdons.gui.*;
import me.atroxego.pauladdons.handlers.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = PaulAddons.modid)
public class PaulAddons {
    public static final String modid = "pauladdons";
    public static String guiToOpen = null;
    static KeyBinding[] keyBindings = new KeyBinding[1];
    public static final ResourceLocation BONZO_ICON = new ResourceLocation("/dsm", "icons/bonzo.png");
    public static String ERROR_COLOUR = EnumChatFormatting.RED.toString();
    public static final String MAIN_COLOUR = EnumChatFormatting.GREEN.toString();
    public static final String SECONDARY_COLOUR = EnumChatFormatting.DARK_GREEN.toString();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        keyBindings[0] = new KeyBinding("Open Gui", Keyboard.KEY_M, "PaulAddons");

        for (KeyBinding keyBinding : keyBindings) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (guiToOpen != null) {
            Minecraft mc = Minecraft.getMinecraft();
            if (guiToOpen.startsWith("pauladdonsgui")) {
                int page = Character.getNumericValue(guiToOpen.charAt(guiToOpen.length() - 1));
                mc.displayGuiScreen(new PaulAddonsGui(page));
            } else {
                switch (guiToOpen) {
                    case "displaygui":
                        mc.displayGuiScreen(new DisplayGui());
                        break;
                    case "editlocations":
                        mc.displayGuiScreen(new EditLocationsGui());
                }
            }
            guiToOpen = null;
        }
    }

    @SubscribeEvent
    public void renderPlayerInfo(final RenderGameOverlayEvent.Post event) {
        if (!(Minecraft.getMinecraft().ingameGUI instanceof GuiIngameForge)) return;
        if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE && event.type != RenderGameOverlayEvent.ElementType.JUMPBAR)
            return;

        renderEverything();
    }

    public void renderEverything() {
        if (Minecraft.getMinecraft().currentScreen instanceof EditLocationsGui) return;

        Minecraft mc = Minecraft.getMinecraft();

        if (ToggleCommand.timerToggled) {
//                double scale = ScaleCommand.timerScale;
//                double scaleReset = Math.pow(scale, -1);
//                GL11.glScaled(scale, scale, scale);
                mc.getTextureManager().bindTexture(BONZO_ICON);
                Gui.drawModalRectWithCustomSizedTexture(MoveCommand.timerXY[0], MoveCommand.timerXY[1], 0, 0, 16, 16, 16, 16);

            String bonzoText = EnumChatFormatting.GREEN + "READY";
            new TextDisplayer(mc, bonzoText, MoveCommand.timerXY[0] + 20, MoveCommand.timerXY[1] + 5, 1);

//                GL11.glScaled(scaleReset, scaleReset, scaleReset);

        }
    }

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new MoveCommand());
        ClientCommandHandler.instance.registerCommand(new ScaleCommand());
        ClientCommandHandler.instance.registerCommand(new PaulAddonsGuiCommand());
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());

    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {

        if (keyBindings[0].isKeyDown()) {
            guiToOpen = "pauladdonsgui1";
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Gui was supposed to open rn"));
        }
    }
}

