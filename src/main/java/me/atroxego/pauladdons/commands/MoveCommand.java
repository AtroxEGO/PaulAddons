package me.atroxego.pauladdons.commands;

import me.atroxego.pauladdons.PaulAddons;
import me.atroxego.pauladdons.handlers.ConfigHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class MoveCommand extends CommandBase {
    public static int[] cultTimerXY = {0, 0};

    @Override
    public String getCommandName() {
        return "move";
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <timer> <x> <y>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "timer");
        }
        return null;
    }
    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        final EntityPlayer player = (EntityPlayer)arg0;
        if (arg1.length < 2) {
            player.addChatMessage(new ChatComponentText(PaulAddons.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }
        switch (arg1[0].toLowerCase()) {
            case "timer":
                cultTimerXY[0] = Integer.parseInt(arg1[1]);
                cultTimerXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "cultTimerX", cultTimerXY[0]);
                ConfigHandler.writeIntConfig("locations", "cultTimerY", cultTimerXY[1]);
                player.addChatMessage(new ChatComponentText(PaulAddons.MAIN_COLOUR + "Timer has been moved to " + PaulAddons.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                break;
            default:
                player.addChatMessage(new ChatComponentText("Usage: " + getCommandUsage(arg0)));
        }
    }
}
