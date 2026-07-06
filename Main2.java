import java.util.Scanner;

abstract class Student {
    protected String name;

    public Student(String name) {
        this.name = name;
    }

    public abstract void display();
}

class CollegeStudent extends Student {
    public CollegeStudent(String name) {
        super(name);
    }

    public void display() {
        System.out.println("College Student Name: " + name);
    }
}

class MyThread extends Thread {
    private Student student;

    public MyThread(Student student) {
        this.student = student;
    }

    public void run() {
        student.display();
    }
}

public class Main2 {

    static final int INITIAL_CAPACITY = 5;
    static Student[] students = new Student[INITIAL_CAPACITY];
    static int count = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    if (count >= INITIAL_CAPACITY) {
                        System.out.println("Student array is full. Cannot add more students.");
                    } else {
                        System.out.print("Enter student name: ");
                        String name = sc.nextLine();

                        students[count] = new CollegeStudent(name);

                        MyThread t1 = new MyThread(students[count]);
                        t1.start();

                        try {
                            t1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        count++;
                    }
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No students to display.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            MyThread t = new MyThread(students[i]);
                            t.start();

                            try {
                                t.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}