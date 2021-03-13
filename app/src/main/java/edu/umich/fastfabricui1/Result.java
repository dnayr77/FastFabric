package edu.umich.fastfabricui1;

import android.media.Image;

public class Result {
    private String cost, name;
    private String fabricImage;
    private int index;

    public Result(String fabricImage, String name, String cost, int index) {
        this.fabricImage = fabricImage;
        this.name = name;
        this.cost = cost;
        this.index = index;
    }

    public int getIndex() { return index; }

    public String getImage() {
        return fabricImage;
    }

    public String getName() { return name; }

    public String getCost() { return cost; }

}
