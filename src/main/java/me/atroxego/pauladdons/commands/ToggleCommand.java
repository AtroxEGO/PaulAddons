package me.atroxego.pauladdons.commands;

import me.atroxego.pauladdons.PaulAddons;
import me.atroxego.pauladdons.handlers.ConfigHandler;
import me.atroxego.pauladdons.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

import static me.atroxego.pauladdons.utils.Utils.getNextCult;

public class ToggleCommand extends CommandBase implements ICommand {
    public static boolean cultTimerToggled;

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
                cultTimerToggled = !cultTimerToggled;
                ConfigHandler.writeBooleanConfig("toggles", "cultTimerToggled", cultTimerToggled);
                getNextCult();
                player.addChatMessage(new ChatComponentText(Utils.modPrefix + EnumChatFormatting.DARK_AQUA + " Star Cult Timer: " + (cultTimerToggled ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off")));
                break;
        }
    }
}
