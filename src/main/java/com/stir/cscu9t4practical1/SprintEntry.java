package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry {

    private int reps;
    private int recovery;

    SprintEntry(String n, int d, int m, int y, int h, int min, int s, float dist, int rps, int rvy)
    {
        super(n, d, m, y, h, min, s, dist);
        this.reps = rps;
        this.recovery = rvy;
    }

    public int getRepetitions()
    {
        return reps;
    }

    public int getRecovery()
    {
        return recovery;
    }

    public String getEntry () {
        String result = getName()+" sprinted " + getRepetitions()+ "x" + Math.round(getDistance()*100) + "m in "
                +getHour()+":"+getMin()+":"+ getSec()
                + " with " + getRecovery() + " minutes recovery" + " on "
                +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
        return result;
    } //getEntry

    public float getDistance()
    {
        return super.getDistance()/100;
    }


}
