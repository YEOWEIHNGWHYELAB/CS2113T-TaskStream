package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        // Unsorted...
        System.out.println("Printing deadlines");
        printDeadlines(tasksData);
        System.out.println("");

        // Sorted...
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        printDeadlineUsingStream(tasksData);
        System.out.println("");

        // Filter Task By String -> Will only return 2 items...
        ArrayList<Task> filteredList = filterTaskByString(tasksData, "11");
        printData(filteredList);
        System.out.println("");
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

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlineUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                // Basically you sort first before you print it out...
                .sorted((a, b) -> a.getDescription().toLowerCase(Locale.ROOT).compareTo(b.getDescription().toLowerCase())) // Comparator thingy
                .forEach(System.out::println);
    }

    public static  ArrayList<Task> filterTaskByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList =(ArrayList<Task>) tasks.stream() // Must typecast since it returns an array instead.
                            .filter((t) -> t.getDescription().contains(filterString))
                            .collect(Collectors.toList());

        return filteredList;
    }
    // test1
}
