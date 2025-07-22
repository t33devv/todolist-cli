package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        JsonHandler jsonHandler = new JsonHandler();
        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.println("Welcome to Tommy's Java Command Line Todolist Application!");
        System.out.println("------");

        while (true) {
            main.printDefault();
            int choice = 0;
            try {
                choice = sc.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Please enter a number between 1 and 6.");
                }
            } catch (Exception e) {
                System.out.println("Please enter an INTEGER you idiot.");
                sc.next();
                continue;
            }
            if (choice == 0) {
                break;
            } else {
                switch (choice) {
                    case 1:
                        main.viewTasks(jsonHandler);
                        break;
                    case 2:
                        main.createTask(jsonHandler);
                        break;
                    case 3:
                        main.markTaskComplete(jsonHandler);
                        break;
                    case 4:
                        main.markTaskIncomplete(jsonHandler);
                        break;
                    case 5:
                        main.deleteTask(jsonHandler);
                        break;
                    case 6:
                        System.exit(0);
                        break;
                }
            }
        }
    }

    void printDefault() {
        System.out.println("");
        System.out.println("1. View tasks");
        System.out.println("2. Create task");
        System.out.println("3. Mark task complete");
        System.out.println("4. Mark task incomplete");
        System.out.println("5. Delete task");
        System.out.println("6. Exit program");
        System.out.println("");
        System.out.print("Input: ");
    }

    void viewTasks(JsonHandler jsonHandler) throws IOException {
        System.out.println("");
        jsonHandler.readJson();
    }

    void createTask(JsonHandler jsonHandler) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print("Enter the name of the task you want to create: ");
        String name = sc.nextLine();
        jsonHandler.writeJson(name, false);
        System.out.println("");
        System.out.println("Successfully added task: " + name);
        System.out.println("");
    }

    void markTaskComplete(JsonHandler jsonHandler) throws IOException {
        System.out.println("");
        this.viewTasks(jsonHandler);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the # of the task you want to mark complete: ");
        int number = 0;
        try {
            number = sc.nextInt();
            if (number < 1 || number > jsonHandler.getMax()) {
                System.out.println("");
                System.out.println("Please enter a number between 1 and " + jsonHandler.getMax() + ".");
                return;
            }
        } catch (Exception e) {
            System.out.println("");
            System.out.println("Please enter a valid integer.");
            return;
        }

        number--;

        jsonHandler.writeJson(jsonHandler.getKey(number), true);
        System.out.println("");
        System.out.println("Successfully marked task '" + jsonHandler.getKey(number) + "' complete!");
        System.out.println("");
    }

    void markTaskIncomplete(JsonHandler jsonHandler) throws IOException{
        System.out.println("");
        this.viewTasks(jsonHandler);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the # of the task you want to mark incomplete:");
        int number = 0;
        try {
            number = sc.nextInt();
            if (number < 1 || number > jsonHandler.getMax()) {
                System.out.println("");
                System.out.println("Please enter a number between 1 and " + jsonHandler.getMax() + ".");
                return;
            }
        } catch (Exception e) {
            System.out.println("");
            System.out.println("Please enter a valid integer.");
            return;
        }

        number--;

        jsonHandler.writeJson(jsonHandler.getKey(number), false);
        System.out.println("");

        System.out.println("Successfully marked task '" + jsonHandler.getKey(number) + "' as incomplete!");
        System.out.println("");
    }

    void deleteTask(JsonHandler jsonHandler) throws IOException {
        System.out.println("");
        this.viewTasks(jsonHandler);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the # of the task you want to delete:");
        int number = 0;
        try {
            number = sc.nextInt();
            if (number < 1 || number > jsonHandler.getMax()) {
                System.out.println("");
                System.out.println("Please enter a number between 1 and " + jsonHandler.getMax() + ".");
            }
        } catch (Exception e) {
            System.out.println("");
            System.out.println("Please enter a valid integer.");
        }

        number--;

        System.out.println("");
        System.out.println("Successfully deleted task '" + jsonHandler.getKey(number) + "'");
        System.out.println("");

        jsonHandler.deleteKey(number);
    }
}