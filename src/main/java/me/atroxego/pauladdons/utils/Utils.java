package me.atroxego.pauladdons.utils;

import net.minecraft.util.EnumChatFormatting;

public class Utils {

    public static String getColouredBoolean(boolean bool){
        return bool ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off";
    }
}
