package me.atroxego.pauladdons.commands;

import me.atroxego.pauladdons.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import static me.atroxego.pauladdons.api.dateInformation.getDateInformation;

public class NextCultCommand extends CommandBase {
    @Override
    public String getCommandName(){
        return "test";
    }
    @Override
    public String getCommandUsage(ICommandSender arg0){
        return "/" + getCommandName();
    }
    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }

    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        getDateInformation();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Next Cult in: " + Utils.getTimeFromSeconds(Utils.secondsTillEvent)));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Current Day: " + Utils.currentDay));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Current Hour: " + Utils.currentHour));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Current Minute: " + Utils.currentMinute));
    }
}
