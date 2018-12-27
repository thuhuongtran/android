package com.vtcac.thuhuong.lab3_lesson3.exc3;

public class AlarmDay {
    public String type;
    public String hour;
    public String alarmNums;
    public String countdownTimer;
    public boolean onOff;

    public AlarmDay(String type, String hour, String alarmNums, String countdownTimer, boolean onOff) {
        this.type = type;
        this.hour = hour;
        this.alarmNums = alarmNums;
        this.countdownTimer = countdownTimer;
        this.onOff = onOff;
    }

    public String getType() {
        return type;
    }

    public String getHour() {
        return hour;
    }

    public String getAlarmNums() {
        return alarmNums;
    }

    public String getCountdownTimer() {
        return countdownTimer;
    }

    public boolean isOnOff() {
        return onOff;
    }
}