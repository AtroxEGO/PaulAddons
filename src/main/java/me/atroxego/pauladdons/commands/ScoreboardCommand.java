package me.atroxego.pauladdons.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class ScoreboardCommand extends CommandBase {
    @Override
    public String getCommandName(){
        return "getsb";
    }
    @Override
    public String getCommandUsage(ICommandSender arg0){
        return "/" + getCommandName() + " scoreboard line";
    }
    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "1", "2", "3");
        }
        return null;
    }

    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
    }
}
