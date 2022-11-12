package me.atroxego.pauladdons.commands;

import me.atroxego.pauladdons.PaulAddons;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

import static me.atroxego.pauladdons.utils.Utils.getNextCult;

public class ToggleCommand extends CommandBase implements ICommand {
    public static boolean timerToggled;

    @Override
    public String getCommandName() {
        return "toggle";
    }
    @Override
    public String getCommandUsage(ICommandSender arg0) {

        return "/ " + getCommandName() + "<timer>";
    }
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args,"timer");
        }
        return null;
    }
    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        final EntityPlayer player = (EntityPlayer) arg0;

        if (arg1.length == 0) {
            player.addChatMessage(new ChatComponentText(PaulAddons.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }
        switch (arg1[0].toLowerCase()) {
            case "timer":
                timerToggled = !timerToggled;
                getNextCult();
                player.addChatMessage(new ChatComponentText(PaulAddons.MAIN_COLOUR + "Star Cult timer has been set to " + PaulAddons.SECONDARY_COLOUR + timerToggled + PaulAddons.MAIN_COLOUR + "."));
                break;
        }
    }
}
