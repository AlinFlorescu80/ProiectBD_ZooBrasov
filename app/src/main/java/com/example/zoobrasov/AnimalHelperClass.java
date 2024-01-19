package com.example.zoobrasov;

public class AnimalHelperClass {

    String name, arealSiHabitat, durataDeViata, dimensiuni;

    public AnimalHelperClass(String name, String arealSiHabitat, String durataDeViata, String dimensiuni) {
        this.name = name;
        this.arealSiHabitat = arealSiHabitat;
        this.durataDeViata = durataDeViata;
        this.dimensiuni = dimensiuni;
    }

    public AnimalHelperClass(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArealSiHabitat() {
        return arealSiHabitat;
    }

    public void setArealSiHabitat(String arealSiHabitat) {
        this.arealSiHabitat = arealSiHabitat;
    }

    public String getDurataDeViata() {
        return durataDeViata;
    }

    public void setDurataDeViata(String durataDeViata) {
        this.durataDeViata = durataDeViata;
    }

    public String getDimensiuni() {
        return dimensiuni;
    }

    public void setDimensiuni(String dimensiuni) {
        this.dimensiuni = dimensiuni;
    }
}
