package com.example.sophia.ywaitgroup7;

/**
 * Created by MacintoshHD on 12/4/17.
 */


public class Record {
    public String userName;
    public long loginTime;
    public int waitTime, waitPeople;

    public Record() {
    }

    public Record(String userName, int waitTime, int waitPeople, long loginTime) {
        this.userName = userName;
        this.waitTime = waitTime;
        this.waitPeople = waitPeople;
        this.loginTime = loginTime;
    }
}


