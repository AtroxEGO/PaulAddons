package me.atroxego.pauladdons.commands;

import me.atroxego.pauladdons.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class DateSetCommand extends CommandBase {
    public String getCommandName() {
        return "dateset";
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        final EntityPlayer player = (EntityPlayer) arg0;

        Utils.currentDay = Integer.parseInt(arg1[0]);
        Utils.currentHour = Integer.parseInt(arg1[1]);
        Utils.currentMinute = Integer.parseInt(arg1[2]);
    }
}
