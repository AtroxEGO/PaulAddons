package me.atroxego.pauladdons.commands;

import me.atroxego.pauladdons.handlers.ConfigHandler;
import me.atroxego.pauladdons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ReloadConfig extends CommandBase {

    @Override
    public String getCommandName() {
        return "reloadpaulconfig";
    }
    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName();
    }
    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }
    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) throws CommandException {

        ConfigHandler.reloadConfig();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Utils.modPrefix + EnumChatFormatting.DARK_AQUA + " Config Reloaded!"));
    }
}
