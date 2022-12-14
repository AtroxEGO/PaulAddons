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

public class ScaleCommand extends CommandBase {
    public static double cultTimerScale = 1;
    @Override
    public String getCommandName() {
        return "scale";
    }
    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <CultTimer> <size (0.1 - 10)>";
    }
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "CultTimer");
        }
        return null;
    }
    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        final EntityPlayer player = (EntityPlayer) arg0;

        if (arg1.length < 2) {
            player.addChatMessage(new ChatComponentText(PaulAddons.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }

        double scaleAmount = Math.floor(Double.parseDouble(arg1[1]) * 100.0) / 100.0;
        if (scaleAmount < 0.1 || scaleAmount > 10.0) {
            player.addChatMessage(new ChatComponentText(PaulAddons.ERROR_COLOUR + "Scale multipler can only be between 0.1x and 10x."));
            return;
        }

        switch (arg1[0].toLowerCase()) {
            case "culttimer":
                cultTimerScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "cultTimerScale", cultTimerScale);
                player.addChatMessage(new ChatComponentText( PaulAddons.MAIN_COLOUR +"Star Cult Timer has been scaled to " + PaulAddons.SECONDARY_COLOUR + cultTimerScale + "x"));
                break;
            default:
                player.addChatMessage(new ChatComponentText(PaulAddons.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
        }
    }
}
