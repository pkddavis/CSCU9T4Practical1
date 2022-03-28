package com.stir.cscu9t4practical1;

public class SwimEntry extends Entry{

    private String poolType;

    SwimEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String type)
    {
        super(n, d, m, y, h, min, s, dist);
        this.poolType = type;
    }

    public String getWhere()
    {
        return poolType;
    }

}
