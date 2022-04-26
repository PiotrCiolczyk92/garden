package com.epam.training.garden.service;

import java.util.Objects;

public class Result {
    private double area;
    private double waterAmount;
    private boolean areaOk;
    private boolean waterOk;

    public Result() {};

    public Result(double area, double waterAmount, boolean areaOk, boolean waterOk) {
        this.area = area;
        this.waterAmount = waterAmount;
        this.areaOk = areaOk;
        this.waterOk = waterOk;
    }

    public double getArea() {
        return area;
    }

    public double getWaterAmount() {
        return waterAmount;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setWaterAmount(double waterAmount) {
        this.waterAmount = waterAmount;
    }

    public void setAreaOk(boolean areaOk) {
        this.areaOk = areaOk;
    }

    public void setWaterOk(boolean waterOk) {
        this.waterOk = waterOk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Double.compare(result.area, area) == 0
                && Double.compare(result.waterAmount, waterAmount) == 0
                && areaOk == result.areaOk && waterOk == result.waterOk;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, waterAmount);
    }

    @Override
    public String toString() {
        return "Result{" +
                "area=" + area +
                ", waterAmount=" + waterAmount +
                ", areaOk=" + areaOk +
                ", waterOk=" + waterOk +
                '}';
    }
}
