package me.atroxego.pauladdons.gui;

import me.atroxego.pauladdons.PaulAddons;
import me.atroxego.pauladdons.commands.MoveCommand;
import me.atroxego.pauladdons.commands.ScaleCommand;
import me.atroxego.pauladdons.gui.buttons.LocationButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class EditLocationsGui extends GuiScreen {
    private String moving = null;
    private int lastMouseX = -1;
    private int lastMouseY = -1;
    private GuiButton backGUI;
    private LocationButton timer;

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }
    @Override
    public void initGui() {
        super.initGui();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();
        timer = new LocationButton(0, MoveCommand.timerXY[0], MoveCommand.timerXY[1] + 5, 85 * ScaleCommand.timerScale, 18 * ScaleCommand.timerScale, ScaleCommand.timerScale,"     17m21s",null,null);
        backGUI = new GuiButton(0, 2, height-25,60,20,"Back");
        this.buttonList.add(timer);
        this.buttonList.add(backGUI);
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        mouseMoved(mouseX, mouseY);

        double timerScale = ScaleCommand.timerScale;
        double timerScaleReset = Math.pow(timerScale, -1);
        GL11.glScaled(timerScale, timerScale, timerScale);
        mc.getTextureManager().bindTexture(PaulAddons.FALLEN_STAR_HELMET_ICON);
        Gui.drawModalRectWithCustomSizedTexture(MoveCommand.timerXY[0], MoveCommand.timerXY[1], 0, 0, 16, 16, 16, 16);
        GL11.glScaled(timerScaleReset, timerScaleReset, timerScaleReset);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    private void mouseMoved(int mouseX, int mouseY) {
        int xMoved = mouseX - lastMouseX;
        int yMoved = mouseY - lastMouseY;
        if (moving != null) {
            switch (moving) {
                case "timer":
                    MoveCommand.timerXY[0] += xMoved;
                    MoveCommand.timerXY[1] += yMoved;
                    timer.xPosition = MoveCommand.timerXY[0];
                    timer.yPosition = MoveCommand.timerXY[1];
                    break;
            }
            this.buttonList.clear();
            initGui();
        }
        lastMouseX = mouseX;
        lastMouseY = mouseY;
    }
    @Override
    public void actionPerformed(GuiButton button) {
        if (button instanceof LocationButton) {
            if (button == timer) {
                moving = "timer";
            }
        } else if (button == backGUI) {
            PaulAddons.guiToOpen = "pauladdonsgui1";
        }
    }
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        moving = null;
    }
}
