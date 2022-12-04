package me.atroxego.pauladdons.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import static me.atroxego.pauladdons.handlers.dateInformation.getDateInformation;

public class Utils {
    static Minecraft mc = Minecraft.getMinecraft();
    public static int currentDay = 0;
    public static String currentTime = "";
    public static int currentHour = 0;
    public static int currentMinute = 0;
    public static double secondsTillCult = 0;
    public static double nextCult = 0;
    public static boolean cultActive = false;
    public static String modPrefix = EnumChatFormatting.BOLD.toString() + EnumChatFormatting.AQUA.toString() + "[Paul Addons]";
    private static boolean veryImportantBoolean = false;

    public static String getColouredBoolean(boolean bool){
        return bool ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off";
    }
    public static void getNextCult() {
        double timeNow = System.currentTimeMillis() / 1000;
        int nextCultInDays = 0;
        int nextCultInHours = 0;
        int nextCultInMinutes = 0;

            if (currentDay % 7 == 0 && currentHour < 6){
                cultActive = true;
                nextCultInDays = -1;
                nextCultInHours = 23 - currentHour;
                nextCultInMinutes = 60 - currentMinute;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Utils.modPrefix + EnumChatFormatting.DARK_AQUA + " Star Cult! Option 1"));
            }else {
            for (int i = 28; i>=7; i-=7){
                if (currentDay < i) {
                 cultActive = false;
                 nextCultInDays = i - 1 - currentDay;
                 nextCultInHours = 23 - currentHour;
                 nextCultInMinutes = 60 - currentMinute;
            }
        }
            }
        if ((currentDay >= 28 && currentHour >= 6) || currentDay > 28) {
            cultActive = false;
            nextCultInDays = 37 - currentDay;
            nextCultInHours = 23 - currentHour;
            nextCultInMinutes = 60 - currentMinute;
        }

        secondsTillCult = (nextCultInDays * 1200 + nextCultInHours * 50 + nextCultInMinutes * 0.83333333);
        nextCult = timeNow + secondsTillCult;
    }
    public static String getTimeBetween(double timeOne, double timeTwo) {
        double secondsBetween = Math.floor(timeTwo - timeOne);
        String timeFormatted;
        int days;
        int hours;
        int minutes;
        int seconds = 0;

        if (secondsBetween > 86400) {
            // More than 1d, display #d#h
            days = (int) (secondsBetween / 86400);
            hours = (int) (secondsBetween % 86400 / 3600);
            timeFormatted = days + "d" + hours + "h";
        } else if (secondsBetween > 3600) {
            // More than 1h, display #h#m
            hours = (int) (secondsBetween / 3600);
            minutes = (int) (secondsBetween % 3600 / 60);
            timeFormatted = hours + "h" + minutes + "m";
        } else {
            // Display #m#s
            minutes = (int) (secondsBetween / 60);
            seconds = (int) (secondsBetween % 60);
            timeFormatted = minutes + "m" + seconds + "s";
        }
        return timeFormatted;
    }
public static double getTimeSecBetween (double timeOne, double timeTwo){
        if ((timeTwo - timeOne < 0 && timeTwo - timeOne > -1) && !veryImportantBoolean) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Utils.modPrefix + EnumChatFormatting.DARK_AQUA + " Star Cult Active!"));
            veryImportantBoolean = true;
        }
        return timeTwo - timeOne;
}
    public static String getTimeCultEnd (double timeOne, double timeTwo){
        if ((currentDay + 1) % 7 == 0){
            currentDay++;
            currentHour = 0;
            currentMinute = 0;
        }
        if ((timeTwo+300) - timeOne < 0){
            getDateInformation();
            getNextCult();
            veryImportantBoolean = false;
            return "What Happened? API prob down -_-";
        } else return getTimeBetween(timeOne, timeTwo+300);
    }
}




