package me.atroxego.pauladdons;

import me.atroxego.pauladdons.commands.*;
import me.atroxego.pauladdons.gui.DisplayGui;
import me.atroxego.pauladdons.gui.EditLocationsGui;
import me.atroxego.pauladdons.gui.PaulAddonsGui;
import me.atroxego.pauladdons.handlers.ConfigHandler;
import me.atroxego.pauladdons.handlers.TextDisplayer;
import me.atroxego.pauladdons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
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
import org.lwjgl.opengl.GL11;

import static me.atroxego.pauladdons.commands.ScaleCommand.cultTimerScale;
import static me.atroxego.pauladdons.utils.Utils.*;

@Mod(modid = PaulAddons.modid)
public class PaulAddons {
    public static final String modid = "pauladdons";
    public static String guiToOpen = null;
    static KeyBinding[] keyBindings = new KeyBinding[1];
    public static final ResourceLocation FALLEN_STAR_HELMET_ICON = new ResourceLocation("pauladdons", "icons/fallen_star_helmet.png");
    public static String ERROR_COLOUR = EnumChatFormatting.RED.toString();
    public static final String MAIN_COLOUR = EnumChatFormatting.GREEN.toString();
    public static final String SECONDARY_COLOUR = EnumChatFormatting.DARK_GREEN.toString();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ConfigHandler.reloadConfig();
        keyBindings[0] = new KeyBinding("Open Gui", Keyboard.KEY_RCONTROL, "PaulAddons");
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
//    @SubscribeEvent(priority = EventPriority.HIGHEST)
//    public void onChat(ClientChatReceivedEvent event) {
//
//    }

    public void renderEverything() {
        if (Minecraft.getMinecraft().currentScreen instanceof EditLocationsGui) return;

        Minecraft mc = Minecraft.getMinecraft();

        if (ToggleCommand.cultTimerToggled) {
                double scale = 1;
                scale = cultTimerScale;
                double scaleReset = Math.pow(scale, -1);
                double timeNow = System.currentTimeMillis() / 1000;
                GL11.glScaled(scale, scale, scale);
                mc.getTextureManager().bindTexture(FALLEN_STAR_HELMET_ICON);
                Gui.drawModalRectWithCustomSizedTexture(MoveCommand.cultTimerXY[0], MoveCommand.cultTimerXY[1], 0, 0, 16, 16, 16, 16);
                String timerText = "";
                if (getTimeSecBetween(timeNow, nextCult) < 0 || cultActive) {
                timerText = EnumChatFormatting.GREEN + Utils.getTimeCultEnd(timeNow, nextCult);
            } else {
                timerText = Utils.getTimeBetween(timeNow, nextCult);
            }
            new TextDisplayer(mc, timerText, MoveCommand.cultTimerXY[0] + 20, MoveCommand.cultTimerXY[1] + 5, 1);
                GL11.glScaled(scaleReset, scaleReset, scaleReset);
        }
    }

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) throws InterruptedException {
        ClientCommandHandler.instance.registerCommand(new MoveCommand());
        ClientCommandHandler.instance.registerCommand(new ScaleCommand());
        ClientCommandHandler.instance.registerCommand(new PaulAddonsGuiCommand());
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());
//        ClientCommandHandler.instance.registerCommand(new NextCultCommand());
//        ClientCommandHandler.instance.registerCommand(new DateSetCommand());
        ClientCommandHandler.instance.registerCommand(new ReloadConfig());
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {

        if (keyBindings[0].isKeyDown()) {
            guiToOpen = "pauladdonsgui1";
        }
    }
}

