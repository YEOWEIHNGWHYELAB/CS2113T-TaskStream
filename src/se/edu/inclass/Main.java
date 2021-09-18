package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        printDeadlineUsingStream(tasksData);
        System.out.println("Total number of deadlines (using stream): " +
                countDeadlineUsingStream(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlineUsingStream(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .count(); // And aggregat that just count... And it returns long so must typecast...
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Printing data by looping");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    // Printing using Streams instead.
    public static void printDataWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data using stream");
        tasks.stream() // Convert data to stream.
                .forEach(System.out::println); // Terminal method that consume all the things done in stream earlier. And do note that there is no () at the println since you are passing it to the forEach()...
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        /** // This is similar to the printDeadlineUsingStream method.
        ArrayList<Task> deadlines = new ArrayList<>();
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                deadlines.add(t);
                System.out.println(t);
            }
        }*/

        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlineUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadlines using streams");
        tasks.stream()
                // You have the parameter which you are checking if
                // it is instance of the deadline. Only if it is then
                // it is printed...
                .filter((t) -> t instanceof Deadline) // filtering using lambda.
                .forEach(System.out::println);
    }
}
