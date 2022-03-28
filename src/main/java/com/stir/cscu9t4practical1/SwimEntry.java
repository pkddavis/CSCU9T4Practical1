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
        String where = poolType;
        if (where.equals("pool"))
        {
            where = "in a " + where;
        }
        return where;
    }

    public String getEntry () {
        String result = getName()+" swam " + getDistance() + " km "+ getWhere()+ " in "
                +getHour()+":"+getMin()+":"+ getSec() + " on "
                +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
        return result;
    } //getEntry

}
