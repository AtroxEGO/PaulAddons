package me.atroxego.pauladdons.gui;

import me.atroxego.pauladdons.PaulAddons;
import me.atroxego.pauladdons.commands.ToggleCommand;
import me.atroxego.pauladdons.handlers.*;
import me.atroxego.pauladdons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PaulAddonsGui extends GuiScreen {
    private final int page;

    private GuiButton closeGUI;
    private GuiButton backPage;
    private GuiButton nextPage;
    private GuiButton editLocations;
    private GuiButton changeDisplay;
    private GuiButton timer;
    private GuiButton githubLink;
    public PaulAddonsGui(int page){
        this.page = page;
    }
    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }
    @Override
    public void initGui(){
        super.initGui();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();

        closeGUI = new GuiButton(0, width / 2 - 100, (int) (height * 0.9), "Close");
        backPage = new GuiButton(0, width / 2 - 100, (int) (height * 0.8), 80, 20, "< Back");
        nextPage = new GuiButton(0, width / 2 + 20, (int) (height * 0.8), 80, 20, "Next >");
        editLocations = new GuiButton(0, 2, 5, 100, 20, "Edit Locations");
        githubLink = new GuiButton(0, 2, height - 50, 80, 20, "GitHub");

        changeDisplay = new GuiButton(0, width / 2 - 100, (int) (height * 0.1), "Change Display Settings");
        timer = new GuiButton(0, width / 2 - 100, (int) (height * 0.2), "Bonzo's Mask Timer: " + Utils.getColouredBoolean(ToggleCommand.timerToggled));

        switch (page){
            case 1:
                this.buttonList.add(changeDisplay);
                this.buttonList.add(timer);
                this.buttonList.add(nextPage);
                break;
            case 2:
                this.buttonList.add(timer);
                this.buttonList.add(backPage);
                break;
        }
        this.buttonList.add(githubLink);
        this.buttonList.add(closeGUI);
        this.buttonList.add(editLocations);
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        String pageText = "Page: " + page + "/2";
        int pageWidth = mc.fontRendererObj.getStringWidth(pageText);
        new TextDisplayer(mc, pageText, width/2- pageWidth / 2, 10,1D);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    public void actionPerformed(GuiButton button) {
        if (button == closeGUI) Minecraft.getMinecraft().thePlayer.closeScreen();
        else if (button == nextPage) PaulAddons.guiToOpen = "pauladdonsgui" + (page + 1);
        else if (button == backPage) PaulAddons.guiToOpen = "pauladdonsgui" + (page - 1);
        else if (button == editLocations) PaulAddons.guiToOpen = "editlocations";
        else if (button == githubLink) {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/AtroxEGO"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        } else if (button == changeDisplay) PaulAddons.guiToOpen = "displaygui";
        else if (button == timer) {
            ToggleCommand.timerToggled = !ToggleCommand.timerToggled;
            timer.displayString = "Bonzo's Mask Timer: " + Utils.getColouredBoolean(ToggleCommand.timerToggled);
        }
    }
}
