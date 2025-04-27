import java.util.*;
import java.util.logging.*;
import java.io.*;
import java.security.SecureRandom;

public class StudentManagementTracker {
    public static Scanner sc = new Scanner(System.in);
    public static SecureRandom random = new SecureRandom();
    private static final Logger logger = Logger.getLogger(StudentManagementTracker.class.getName());
    private static final String ID = "0123456789";

    public static String genId() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(ID.length());
            id.append(ID.charAt(index));
        }
        return id.toString();
    }
    public static void enterStudentDetails() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Detail.txt", true))) {
            String[] detail = new String[4];
            System.out.print("Enter Student Name: ");
            detail[0] = sc.nextLine();
            System.out.print("Enter Roll Number: ");
            detail[1] = sc.nextLine();
            System.out.print("Enter Class and Section: ");
            detail[2] = sc.nextLine();
            detail[3] = genId();
            System.out.println("ID generated for the Student is: " + detail[3]);
            for (int i = 0; i < 4; i++) {
                writer.write(detail[i] + "\n");
            }
            System.out.println("Details written in File");
        } catch (IOException e) {
            System.out.println("An Error Occurred");
            logger.log(Level.SEVERE, "An Error Occurred", e);
        }
    }
    public static void enterMarks() {
        try  {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Marks.txt", true))) {
                System.out.print("Enter number of entries: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    sc.nextLine();
                    return;
                }
                int numOfEntries = sc.nextInt();
                if (numOfEntries <= 0) {
                    System.out.println("Number of entries must be greater than 0");
                    return;
                }
                String[] name = new String[numOfEntries];
                int[] marks = new int[numOfEntries];
                sc.nextLine();
                for (int i = 0; i < numOfEntries; i++) {
                    System.out.print("Enter Student Name: ");
                    name[i] = sc.nextLine();
                    System.out.print("Enter Student Marks: ");
                    marks[i] = sc.nextInt();
                    if (marks[i] < 0 || marks[i] > 100) {
                        System.out.println("Invalid marks entered! Must be between 0 and 100");
                        i--;
                        continue;
                    }
                    sc.nextLine();
                }
                for (int i = 0; i < numOfEntries; i++) {
                    writer.write((i+1) + ". " + name[i] + " : " + marks[i] + "\n");
                }
                System.out.println("Details written to file");
            } catch (IOException e) {
                System.out.println("An Error Occurred");
                logger.log(Level.SEVERE, "An error occurred", e);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input!");
            sc.nextLine();
        }
    }
    public static void main(String[] args) {
        while (true) {
            System.out.print("1. Enter Student Details \n2. Enter Student Marks \nEnter Your Choice: ");
            int userChoice = sc.nextInt();
            sc.nextLine();
            switch (userChoice) {
                case 1:
                    enterStudentDetails();
                    break;
                case 2:
                    enterMarks();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            System.out.println("Do you want to continue? ");
            String cont = sc.next();
            if (cont.equalsIgnoreCase("no")) {
                System.out.println("Program Terminated");
                break;
            }
        }
        sc.close();
    }
}
