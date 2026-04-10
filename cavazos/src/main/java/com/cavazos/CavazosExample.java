package com.cavazos;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import org.json.simple.JSONArray;

public class CavazosExample {

  public static void main(String[] args) {
    String fileName = "commands.json";

    // read commands
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);

    Scanner scanner = new Scanner(System.in);
    Stack<String> undoStack = new Stack<>();
    Stack<String> redoStack = new Stack<>();

    boolean running = true;
    while (running) {
      printMenu();
      System.out.print("Enter command: ");
      String input = scanner.nextLine().trim().toLowerCase();

      switch (input) {
        case "i":
          String issued = issueCommand(commandArray);
          System.out.println("General Cavazos says: " + issued);
          undoStack.push(issued);
          redoStack.clear();
          break;
        case "l":
          print(commandArray);
          break;
        case "u":
          if (undoStack.isEmpty()) {
            System.out.println("No commands to undo.");
          } else {
            String undone = undoStack.pop();
            redoStack.push(undone);
            System.out.println("Undone: " + undone);
          }
          break;
        case "r":
          if (redoStack.isEmpty()) {
            System.out.println("No commands to redo.");
          } else {
            String redone = redoStack.pop();
            undoStack.push(redone);
            System.out.println("Redone: " + redone);
          }
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
  public static String issueCommand(String[] commandArray) {
    Random rand = new Random();
    int randIndex = rand.nextInt(commandArray.length);
    return commandArray[randIndex];
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
