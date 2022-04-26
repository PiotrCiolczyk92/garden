package com.epam.training.garden.service;

import com.epam.training.garden.domain.GardenProperties;
import com.epam.training.garden.domain.PlantType;

import java.util.*;

public class GardenService {
    GardenProperties gardenProperties;

    public List<PlantType> getPlantTypes() {
        List<PlantType> plantTypes = new ArrayList<>();
        plantTypes.add(new PlantType("Corn", 0.4, 10));
        plantTypes.add(new PlantType("Pumpkin", 2, 5));
        plantTypes.add(new PlantType("Grape", 3, 5));
        plantTypes.add(new PlantType("Tomato", 0.3, 10));
        plantTypes.add(new PlantType("Cucumber", 0.4, 10));
        return plantTypes;
    }

    public void setGardenProperties(GardenProperties gardenProperties) {
        this.gardenProperties = gardenProperties;
    }

    public Result evaluatePlan(Map<String, Integer> items) {
        Result result = new Result(0, 0, false, false);
        double totalArea = 0;
        double totalWaterSupply = 0;
        PlantType plant = null;
        Set<String> userPlants = items.keySet();
        for (String userPlantName : userPlants) {
            plant = findPlantTypeByName(userPlantName);
            if(plant != null) {
                totalArea += plant.getArea() * items.get(userPlantName);
                totalWaterSupply += plant.getArea() * items.get(userPlantName) * plant.getWaterAmount();
            } else
                throw new IllegalArgumentException("Unknown plant: " + userPlantName + ".");
        }
        if(totalArea < gardenProperties.getArea()) {
            result.setAreaOk(true);
        }
        if (totalWaterSupply < gardenProperties.getWaterSupply()) {
            result.setWaterOk(true);
        }
        result.setArea(totalArea);
        result.setWaterAmount(totalWaterSupply);
        return result;
    }

    private PlantType findPlantTypeByName(String name) {
        if (getPlantTypes().stream().anyMatch(plant -> plant.getName().equals(name))) {
            return getPlantTypes().stream().filter(plant ->
                    plant.getName().equals(name)).findFirst().get();
        } else
            return null;
    }


}
