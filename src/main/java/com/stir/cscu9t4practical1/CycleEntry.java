package com.stir.cscu9t4practical1;

public class CycleEntry extends Entry {

    private String terrain;
    private String tempo;

    CycleEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String ter, String tmp)
    {
        super(n, d, m, y, h, min, s, dist);
        this.terrain = ter;
        this.tempo = tmp;
    }

    public String getTerrain()
    {
        return terrain;
    }

    public String getTempo()
    {
        return tempo;
    }
}
