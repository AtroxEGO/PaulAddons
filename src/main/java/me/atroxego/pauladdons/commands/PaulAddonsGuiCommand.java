package me.atroxego.pauladdons.commands;

import me.atroxego.pauladdons.PaulAddons;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class PaulAddonsGuiCommand extends CommandBase {
    @Override
    public String getCommandName(){
        return "pauladdons";
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
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException{
        PaulAddons.guiToOpen = "pauladdonsgui1";

    }
}
