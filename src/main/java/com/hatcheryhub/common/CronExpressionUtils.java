package com.hatcheryhub.common;

public class CronExpressionUtils {

    public static String dailyAtHourAndMinute(int hour, int minute) {
        validateHour(hour);
        validateMinute(minute);
        return String.format("0 %d %d ? * *", minute, hour);
    }
    
    public static String weeklyOnHourAndMinuteOnGivenDaysOfWeek(int hour, int minute, String[] daysOfWeek) {
        if (daysOfWeek == null || daysOfWeek.length == 0)
            throw new IllegalArgumentException("You must specify at least one day of week.");
        for (String dayOfWeek : daysOfWeek)
            validateDayOfWeek(Integer.parseInt(dayOfWeek));
        validateHour(hour);
        validateMinute(minute);
        String cronExpression = String.format("0 %d %d ? * %d", minute, hour, Integer.parseInt(daysOfWeek[0]));
        for (int i = 1; i < daysOfWeek.length; i++)
            cronExpression = cronExpression + "," + daysOfWeek[i];
        return cronExpression;
    }
    
    public static String monthlyOnDayAndHourAndMinute(int hour, int minute, int dayOfMonth) {
        validateHour(hour);
        validateMinute(minute);
        validateDayOfMonth(dayOfMonth);
        return String.format("0 %d %d %d * ?", minute, hour, dayOfMonth);
    }
    
    public static String yearlyOnHourAndMinuteAndMonthAndDayOfMonth(int hour, int minute, int dayOfMonth, int month) {
        validateHour(hour);
        validateMinute(minute);
        validateMonth(month);
        validateDayOfMonth(dayOfMonth);
        return String.format("0 %d %d %d %d ?", minute, hour, dayOfMonth, month);
    }
    
    
    public static void validateHour(int hour) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Invalid hour (must be >= 0 and <= 23).");
        }
    }

    public static void validateMinute(int minute) {
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Invalid minute (must be >= 0 and <= 59).");
        }
    }

    public static void validateSecond(int second) {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Invalid second (must be >= 0 and <= 59).");
        }
    }
    
    public static void validateDayOfWeek(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {throw new IllegalArgumentException("Invalid day of week.");
        }
    }
    
    public static void validateDayOfMonth(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day of month.");
        }
    }

    public static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month (must be >= 1 and <= 12.");
        }
    }
}
