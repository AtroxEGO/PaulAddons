package me.atroxego.pauladdons.gui;

import me.atroxego.pauladdons.PaulAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import me.atroxego.pauladdons.handlers.*;

public class DisplayGui extends GuiScreen {
    private GuiButton goBack;
    private GuiButton off;

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();

        goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
        off = new GuiButton(0, width / 2 - 210, (int) (height * 0.2), "Off");

        this.buttonList.add(off);
        this.buttonList.add(goBack);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        Minecraft mc = Minecraft.getMinecraft();

        String displayText;
        displayText = "PaulAddons 1/Test";
        int displayWidth = mc.fontRendererObj.getStringWidth(displayText);
        new TextDisplayer(mc, displayText, width / 2 - displayWidth / 2, 10, 1D);

        String catacombsTitleText = "Catacombs Dungeon";
        int catacombsTitleWidth = mc.fontRendererObj.getStringWidth(catacombsTitleText);
        new TextDisplayer(mc, catacombsTitleText, width / 2 - catacombsTitleWidth / 2, (int) (height * 0.6), 1D);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button == goBack) {
            PaulAddons.guiToOpen = "pauladdonsgui1";
        } else if (button == off) {
            setDisplay("off", true);
        }

    }

    public void setDisplay(String display, boolean forceNoSession) {
        display += "_session";
    }
}
