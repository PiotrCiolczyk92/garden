package com.epam.training.garden;

import com.epam.training.garden.domain.GardenProperties;
import com.epam.training.garden.service.GardenService;
import com.epam.training.garden.service.Result;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        GardenProperties garden = new GardenProperties();
        GardenService gardenService = new GardenService();
        Map<String, Integer> userBasket = new HashMap<>();

        //app
        System.out.println("***Welcome to Garden Planner***\n");

        // user garden info
        System.out.println("Please enter your garden properties.");
        System.out.print("Size (square meter): ");
        try {
            garden.setArea(userInput.nextDouble());
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("\nIllegal input format.");
            throw new InputMismatchException();
        }
        System.out.print("Water supply (in liter): ");
        try {
            garden.setWaterSupply(userInput.nextDouble());
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("\nIllegal input format.");
            throw new InputMismatchException();
        }
        gardenService.setGardenProperties(garden);

        // plant list
        System.out.println("\nKnown plant types:");
        gardenService.getPlantTypes()
                .forEach(System.out::println);

        // user choice
        System.out.println("Please enter the plants you would like to put in your garden." +
                " Press enter when you are done.");
        String userChoice = userInput.nextLine();
        do {
            userChoice = userInput.nextLine();
            if(!userChoice.isEmpty()) {
                String[] plantAndCount = userChoice.split(",");
                userBasket.put(plantAndCount[0], Integer.valueOf(plantAndCount[1]));
            }
        } while (!userChoice.isEmpty());

        // result
        System.out.println("***Result***");
        Result result = gardenService.evaluatePlan(userBasket);

        System.out.println("Required area: " + result.getArea());
        System.out.println("Water need: " + result.getWaterAmount());

        if(garden.hashCode() < result.hashCode()) {
            System.out.println("Plan is NOT feasible in your garden! :(");
            if(garden.getArea() < result.getArea()) {
                System.out.println("- Not enough area.");
            }
            if(garden.getWaterSupply() < result.getWaterAmount()) {
                System.out.println("- Not enough water.");
            }
        } else
            System.out.println("Plan is feasible in your garden! :)");

    }
}
