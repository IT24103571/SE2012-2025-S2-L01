import java.util.HashMap;
import java.util.Scanner;

public class Marks {
    private static HashMap<Integer, int[]> studentMarks = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String[] SUBJECTS = { "Mathematics", "Chemistry", "Physics" };

    public static void main(String[] args) {
        System.out.println("Student Marks Management System");
        System.out.println("Available commands:");
        System.out.println("1. add [studentID]");
        System.out.println("2. update [studentID] [subjectID]");
        System.out.println("3. average_s [studentID]");
        System.out.println("4. average [subjectID]");
        System.out.println("5. total [studentID]");
        System.out.println("6. grades");
        System.out.println("7. exit");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            try {
                switch (parts[0]) {
                    case "add":
                        handleAdd(parts);
                        break;
                    case "update":
                        handleUpdate(parts);
                        break;
                    case "average_s":
                        handleStudentAverage(parts);
                        break;
                    case "average":
                        handleSubjectAverage(parts);
                        break;
                    case "total":
                        handleTotal(parts);
                        break;
                    case "grades":
                        handleGrades();
                        break;
                    case "exit":
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("nvalid command!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleAdd(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: add [studentID]");
            return;
        }
        int studentId = Integer.parseInt(parts[1]);
        if (studentMarks.containsKey(studentId)) {
            System.out.println("Student already exists!");
            return;
        }

        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter mark for " + SUBJECTS[i] + ": ");
            marks[i] = scanner.nextInt();
        }
        scanner.nextLine(); // consume newline
        studentMarks.put(studentId, marks);
        System.out.println("Student added successfully!");
    }

    private static void handleUpdate(String[] parts) {
        if (parts.length != 3) {
            System.out.println("Usage: update [studentID] [subjectID]");
            return;
        }
        int studentId = Integer.parseInt(parts[1]);
        int subjectId = Integer.parseInt(parts[2]) - 1;

        if (!studentMarks.containsKey(studentId)) {
            System.out.println("Student not found!");
            return;
        }
        if (subjectId < 0 || subjectId >= 3) {
            System.out.println("Subject ID must be 1, 2, or 3");
            return;
        }

        System.out.print("Enter new mark for " + SUBJECTS[subjectId] + ": ");
        int newMark = scanner.nextInt();
        scanner.nextLine(); // consume newline
        studentMarks.get(studentId)[subjectId] = newMark;
        System.out.println("Mark updated successfully!");
    }

    private static void handleStudentAverage(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: average_s [studentID]");
            return;
        }
        int studentId = Integer.parseInt(parts[1]);
        if (!studentMarks.containsKey(studentId)) {
            System.out.println("Student not found!");
            return;
        }

        int[] marks = studentMarks.get(studentId);
        double sum = 0;
        for (int mark : marks) sum += mark;
        System.out.printf("Average for student %d: %.2f%n", studentId, sum / 3);
    }

    private static void handleSubjectAverage(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: average [subjectID]");
            return;
        }
        int subjectId = Integer.parseInt(parts[1]) - 1;
        if (subjectId < 0 || subjectId >= 3) {
            System.out.println("Subject ID must be 1, 2, or 3");
            return;
        }

        double sum = 0;
        int count = 0;
        for (int[] marks : studentMarks.values()) {
            sum += marks[subjectId];
            count++;
        }
        if (count == 0) {
            System.out.println("No students available.");
            return;
        }
        System.out.printf("Average for %s: %.2f%n", SUBJECTS[subjectId], sum / count);
    }

    private static void handleTotal(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: total [studentID]");
            return;
        }
        int studentId = Integer.parseInt(parts[1]);
        if (!studentMarks.containsKey(studentId)) {
            System.out.println("Student not found!");
            return;
        }

        int[] marks = studentMarks.get(studentId);
        int total = 0;
        for (int mark : marks) total += mark;
        System.out.printf("Total marks for student %d: %d%n", studentId, total);
    }

    private static void handleGrades() {
        if (studentMarks.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        System.out.printf("%-10s %-12s %-12s %-12s%n", "StudentID", "Mathematics", "Chemistry", "Physics");
        System.out.println("----------------------------------------------------------");

        for (Integer studentId : studentMarks.keySet()) {
            int[] marks = studentMarks.get(studentId);
            String mathGrade = getGrade(marks[0]);
            String chemGrade = getGrade(marks[1]);
            String physGrade = getGrade(marks[2]);

            System.out.printf("%-10d %-12s %-12s %-12s%n", studentId, mathGrade, chemGrade, physGrade);
        }
    }

    private static String getGrade(int mark) {
        if (mark >= 90) return "Grade A";
        else if (mark >= 80) return "Grade B";
        else if (mark >= 70) return "Grade C";
        else if (mark >= 60) return "Grade D";
        else return "Fail";
    }
}
