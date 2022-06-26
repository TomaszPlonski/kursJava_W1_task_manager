package pl.tomaszplonski.warsztaty;

import pl.coderslab.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class TaskManager {
    public static void main(String[] args) {

        String[][] tasks = loadData();
        menu(tasks);

    }

    public static String[][] loadData(){
        String[][] tasks = {{}};
        boolean firstLine = true;
        try(Scanner scanner = new Scanner(new File("tasks.csv"))){
            while(scanner.hasNextLine()){
                String[] line = scanner.nextLine().split(", ");
                if(firstLine){
                    tasks[0]=line;
                } else {
                    tasks = Arrays.copyOf(tasks, tasks.length + 1);
                    tasks[tasks.length - 1] = line;
                }
                firstLine = false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File with tasks not found");
        }

        return tasks;
    }

    public static void menu(String[][] tasks){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.println(ConsoleColors.RESET + "add\n" +
                "remove\n" +
                "list\n" +
                "exit");

        switch (scanner.nextLine()){
            case "add":
                menu(add(tasks));
                break;
            case "remove":
                menu(remove(tasks));
                break;
            case "list":
                list(tasks);
                menu(tasks);
                break;
            case "exit":
                saveFile(tasks);
                break;
            default:
                System.out.println("powrót do menu");
                menu(tasks);
        }

    }

    public static String[][] add(String[][] tasks){
        String[] newLine = new String[3];

        System.out.println("Please add task desription:");
        Scanner scanner = new Scanner(System.in);
        newLine[0] = scanner.nextLine();

        System.out.println("Please add task due data:");
        newLine[1] = scanner.nextLine();

        String line;
        do {
            System.out.println("Is your task important: true/false");
            line = scanner.nextLine();
        } while (!(line.equals("true") || line.equals("false")));

        newLine[2] = line;
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = newLine;

        return tasks;

    }

    public static void list(String[][] tasks){
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(i + " : " + tasks[i][0] + " " + tasks[i][1] + " " + tasks[i][2]);
            }
        }

    public static String[][] remove(String[][] tasks){
        System.out.println("Please select number to remove:");
        int input;
        while(true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 0 && input < tasks.length) {
                    for (int i = 0, j = 0; i < tasks.length - 1; i++, j++) {
                        if(i == input){
                            j++;
                        }
                        System.out.println(i +" " + j);
                        tasks[i] = tasks[j];
                    }
                    tasks = Arrays.copyOf(tasks, tasks.length - 1);
                    break;
                } else System.out.println("Wrong number");
            } else System.out.println("This is not valid number");
        }
        return tasks;
    }

    public static void saveFile(String[][] tasks){

        try (PrintWriter writer = new PrintWriter("tasks.csv")){
            for (String[] task : tasks) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < task.length; i++) {
                    sb.append(task[i]);
                    if(i != 2){
                        sb.append(", ");
                    }
                }
                writer.println(sb);
            }
        } catch (FileNotFoundException e){
            System.out.println("Brak pliku");
        }

    }

}

