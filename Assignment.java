/*
author: Nina Burger
*/

import java.io.*;
import java.util.*;

class StudentsList {
    private int index;
    private String studentNumber;
    private String firstName;
    private String lastName;

    public StudentsList(int index, String studentNumber, String firstName, String lastName) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.index = index;
    }

    public String getStudentNumber() {return studentNumber;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getIndex() {return index;}

    @Override
    public String toString() {
        return studentNumber + " " + firstName + " " + lastName;
    }
}

public class Assignment {
    public static void main(String[] args) throws FileNotFoundException {

        Vector<StudentsList> students = new Vector<>();

        File f = new File("/Users/nveins/Documents/ADT/classroaster.csv");
        Scanner scan = new Scanner(f);

        for(int i=0; i<7; i++){
            scan.nextLine();
        }

        Scanner charScan = new Scanner(System.in);
        StringTokenizer token;

        System.out.println(" 'n' Sort by student number");
        System.out.println(" 'f' Sort by first name");
        System.out.println(" 'l' Sort by last name");
        System.out.println(" 's' Search the student by first name");
        System.out.print("Please select the operator (n, f, l or s): ");
        char option = charScan.next().charAt(0);

        while (scan.hasNextLine()) {
            String dataLine = scan.nextLine();
            token = new StringTokenizer(dataLine, ",");

            int index = Integer.parseInt(token.nextToken().trim());
            String SID = token.nextToken().trim();
            String firstName = token.nextToken().trim();
            String lastName = token.hasMoreTokens() ? token.nextToken().trim() : "";
            StudentsList student = new StudentsList(index, SID, firstName, lastName);
            students.add(student);
        }

        switch (option) {
            case 'n':
                sortSID(students);
                break;
            case 'f':
                sortFirstname(students);
                break;
            case 'l':
                sortLastname(students);
                break;
            case 's':
                searchFirstname(students);
                break;
            default:
                System.out.println("invalid choice");
                break;
        }
    }

    public static void sortSID(Vector<StudentsList> students) {
        students.sort(Comparator.comparing(StudentsList::getStudentNumber));
        for (StudentsList student : students) {
            System.out.println(student);
        }
    }

    public static void sortFirstname(Vector<StudentsList> students) {
        students.sort(Comparator.comparing(StudentsList::getFirstName));
        for (StudentsList student : students) {
            System.out.println(student);
        }
    }

    public static void sortLastname(Vector<StudentsList> students) {
        students.sort(Comparator.comparing(StudentsList::getLastName));
        for (StudentsList student : students) {
            System.out.println(student);
        }
    }
    public static void searchFirstname(Vector<StudentsList> students) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String searchName = input.nextLine();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equalsIgnoreCase(searchName)) {
                System.out.println("Found at index: " + i);
                System.out.println(students.get(i));
                return;
            }
        }
        System.out.println("not found.");
    }
}