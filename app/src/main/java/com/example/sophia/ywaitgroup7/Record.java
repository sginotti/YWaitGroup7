package com.example.sophia.ywaitgroup7;

/**
 * Created by MacintoshHD on 12/4/17.
 */

public class Record {
    public String userName, waitPeople, loginTime;
    public int waitTime;

    public Record() {
    }

    public Record(String userName, int waitTime, String waitPeople, String loginTime) {
        this.userName = userName;
        this.waitTime = waitTime;
        this.waitPeople = waitPeople;
        this.loginTime = loginTime;
    }
}
