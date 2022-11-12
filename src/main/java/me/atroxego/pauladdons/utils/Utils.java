package me.atroxego.pauladdons.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class Utils {
    static Minecraft mc = Minecraft.getMinecraft();
    public static int currentDay = 0;
    public static String currentTime = "";
    public static int currentHour = 0;
    public static int currentMinute = 0;
    public static int secondsTillEvent = 0;

    public static String getColouredBoolean(boolean bool){
        return bool ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off";
    }
    public static String getNextCult() {
        String nextCult = null;
        int nextCultInDays = 0;
        double timeNow = System.currentTimeMillis() / 1000;

        int nextCultInHours = 0;
        int nextCultInMinutes = 0;
// DAY 28 HOUR 6 ISNT WORKING FOR NOW
        //COUNTDOWN ISNT WORKING
        for (int i = 28; i>=7; i-=7){
            if (currentDay == i && currentHour < 6){
                if (currentHour == 0) return EnumChatFormatting.GREEN + "Active for: 6h";
                else return EnumChatFormatting.GREEN + "Active for: " + (5 - currentHour) + "h"+(60-currentMinute)+"m";
            }else if (currentDay < i) {
                nextCultInDays = i - 1 - currentDay;
                nextCultInHours = 23 - currentHour;
                nextCultInMinutes = 60 - currentMinute;
            }
        }
        if (currentDay == 29){
            nextCultInDays = 36 - currentDay;
            nextCultInHours = 23 - currentHour;
            nextCultInMinutes = 60 - currentMinute;
        }
        if (currentDay == 30){
            nextCultInDays = 37 - currentDay;
            nextCultInHours = 23 - currentHour;
            nextCultInMinutes = 60 - currentMinute;
        }
        secondsTillEvent = (int) (nextCultInDays * 1200 + nextCultInHours * 50 + Math.round(nextCultInMinutes * 0.83));
//        String output = nextCultInDays + "d" + nextCultInHours + "h" + nextCultInMinutes + "m";
//        String output = String.valueOf(secondsTillEvent);
        String output = getTimeFromSeconds(secondsTillEvent);
//        countDownToEvent(secondsTillEvent);
        return "In: " + EnumChatFormatting.GREEN + output;
    }



//    private static void countDownToEvent(int secondsTillEvent) throws InterruptedException {
//        while (secondsTillEvent > 0){
//            Thread.sleep(1000);
//            secondsTillEvent--;
//            PaulAddons.timerText = getTimeFromSeconds(secondsTillEvent);
//        }
//    }

    public static String getTimeFromSeconds(int seconds){
        String dateString = null;

        int hoursToEvent = (int) seconds/3600;
        seconds -= hoursToEvent * 3600;
        int minutesToEvent = (int) seconds / 60;
        seconds -= minutesToEvent * 60;
        int secondsToEvent = seconds;
        dateString = hoursToEvent + "h" + minutesToEvent + "m" + secondsToEvent + "s";
        return dateString;
    }
}

