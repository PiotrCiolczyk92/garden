package com.epam.training.garden.domain;

import java.util.Objects;

public class PlantType {
    private String name;
    private double area;
    private double waterAmount;

    public PlantType(String name, double area, double waterAmount) {
        this.name = name;
        this.area = area;
        this.waterAmount = waterAmount;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public double getWaterAmount() {
        return this.waterAmount;
    }

    @Override
    public String toString() {
        return "- " + this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantType plantType = (PlantType) o;
        return Double.compare(plantType.area, area) == 0 && Double.compare(plantType.waterAmount, waterAmount) == 0 && Objects.equals(name, plantType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area, waterAmount);
    }
}
