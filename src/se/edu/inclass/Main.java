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

        // System.out.println("Printing deadlines");
        // printDeadlines(tasksData);

        // System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        // printData(tasksData);

        // printDataWithStreams(tasksData); // Using streams to print the data...

        //-------- This thing is from lambdas ------------//
        // printDeadlineUsingStream(tasksData);
        // System.out.println("Total number of deadlines (using stream): " +
                // countDeadlineUsingStream(tasksData));
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
    // }
    // test1
        // System.out.println("Printing deadlines using streams");
        // tasks.parallelStream() // instead of Stream() to use multi-threading.
                // You have the parameter which you are checking if
                // it is instance of the deadline. Only if it is then
                // it is printed...
                // .filter((t) -> t instanceof Deadline) // filtering using lambda.
                // .forEach(System.out::println);
    }

}
