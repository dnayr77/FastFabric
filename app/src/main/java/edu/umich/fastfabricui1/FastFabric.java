package edu.umich.fastfabricui1;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class FastFabric extends Application {

    private List<Integer> likedFabric = new ArrayList<>();
    private List<Integer> dislikedFabric = new ArrayList<>();
    private List<String> prefMaterial = new ArrayList<>();
    private List<Double> prefCost = new ArrayList<>();
    private List<String> prefStrength = new ArrayList<>();
    //List function to get liked fabrics.
    public List<Integer> getLikedFabric() {
        return likedFabric;
    }

    public List<Integer> getDislikedFabric() {
        return dislikedFabric;
    }

    public void addLike(Integer index) {
        likedFabric.add(index);
    }

    public void addDislike(Integer index) {
        dislikedFabric.add(index);
    }

    public void addStrength(String strength) {
        prefStrength.add(strength);
    }
    public void addMaterial(String material) {
        prefStrength.add(material);
    }
    public void addCost(Double cost) {
        prefCost.add(cost);
    }
}

