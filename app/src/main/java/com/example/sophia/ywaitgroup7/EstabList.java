package com.example.sophia.ywaitgroup7;

/**
 * Created by sophia on 12/9/2017.
 */

public class EstabList {
    private String name;
    private String dnum;
    private String wnum;
    private int pic;

    public EstabList(String name, String dnum, String wnum, int pic) {
        this.name = name;
        this.dnum = dnum;
        this.wnum = wnum;
        this.pic = pic;
    }
    public String getName() {
        return this.name;
    }
    public String getDnum(){
        return this.dnum;
    }
    public String getWnum(){
        return this.dnum;
    }
    public int getPic() {
        return pic;
    }

}
