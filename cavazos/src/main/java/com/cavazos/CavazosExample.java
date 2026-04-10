package com.cavazos;

import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;

public class CavazosExample {

  public static void main(String[] args) {
    String fileName = "commands.json";

    // read commands
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);

    Scanner scanner = new Scanner(System.in);

    boolean running = true;
    while (running) {
      printMenu();
      System.out.print("Enter command: ");
      String input = scanner.nextLine().trim().toLowerCase();

      switch (input) {
        case "i":
          System.out.println("Issue command not yet implemented.");
          break;
        case "l":
          System.out.println(commandArray);
          break;
        case "u":
          System.out.println("Undo command not yet implemented.");
          break;
        case "r":
          System.out.println("Redo command not yet implemented.");
          break;
        case "q":
          System.out.println("Goodbye!");
          running = false;
          break;
        default:
          System.out.println("Invalid command. Please try again.");
          break;
      }
      System.out.println();
    }

    scanner.close();
  }

  // print the menu
  public static void printMenu() {
    System.out.println("===== Cavazos Commander =====");
    System.out.println("i - Issue a random command");
    System.out.println("l - List all commands");
    System.out.println("u - Undo last command");
    System.out.println("r - Redo last command");
    System.out.println("q - Quit");
    System.out.println("=============================");
  }

  // randomly issue commands from General Cavazos
  public static void randomCommand(String[] commandArray, int numCommand) {
    Random rand = new Random();
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < numCommand; i++) {
      int randIndex = rand.nextInt(commandArray.length);
      System.out.printf("%04d\t%s\n", i, commandArray[randIndex]);
    }
  }

  // print command array
  public static void print(String[] commandArray) {
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < commandArray.length; i++) {
      System.out.printf("%04d\t%s\n", i, commandArray[i]);
    }
  }

  // get array of commands
  public static String[] getCommandArray(JSONArray commandArray) {
    String[] arr = new String[commandArray.size()];

    // get names from json object
    for (int i = 0; i < commandArray.size(); i++) {
      String command = commandArray.get(i).toString();
      arr[i] = command;
    }
    return arr;
  }
}
