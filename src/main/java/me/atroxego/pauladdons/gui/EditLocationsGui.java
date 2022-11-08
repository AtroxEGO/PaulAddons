package me.atroxego.pauladdons.gui;

import me.atroxego.pauladdons.PaulAddons;
import me.atroxego.pauladdons.commands.MoveCommand;
import me.atroxego.pauladdons.commands.ScaleCommand;
import me.atroxego.pauladdons.gui.buttons.LocationButton;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;

public class EditLocationsGui extends GuiScreen {
    private String moving = null;
    private int lastMouseX = -1;
    private int lastMouseY = -1;

    private LocationButton timer;

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }
    @Override
    public void initGui() {
        super.initGui();
        timer = new LocationButton(0, MoveCommand.timerXY[0], MoveCommand.timerXY[1] + 5, 85 * ScaleCommand.timerScale, 18 * ScaleCommand.timerScale, ScaleCommand.timerScale,"3m30s",null,null);
        this.buttonList.add(timer);
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        mouseMoved(mouseX, mouseY);

        double timerScale = ScaleCommand.timerScale;
        double timerScaleReset = Math.pow(timerScale, -1);
        GL11.glScaled(timerScale, timerScale, timerScale);
        mc.getTextureManager().bindTexture(PaulAddons.BONZO_ICON);
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
        }
    }
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        moving = null;
    }
}
