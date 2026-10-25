
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    double assignmentPoints = 0;
    int assignmentCount = 0;

    double examPoints = 0;
    int examCount = 0;

    int choice = 0;

    do {
        System.out.println("\n----- SEMESTER GRADE TRACKER -----");
        System.out.println("1. Add assignment result");
        System.out.println("2. Add exam result");
        System.out.println("3. View current grade");
        System.out.println("4. Exit");
        System.out.print("\nChoose an option: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            scanner.next();
            continue;
        }

        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter assignment score: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input.");
                    scanner.next();
                    break;
                }
                double assignmentScore = scanner.nextDouble();

                // Валидация балла (от 0 до 100)
                if (assignmentScore < 0 || assignmentScore > 100) {
                    System.out.println("Invalid score. Enter a value from 0 to 100.");
                    break;
                }

                assignmentPoints += assignmentScore;
                assignmentCount++;
                System.out.println("Assignment score added.");
                break;

            case 2:
                System.out.print("Enter exam score: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input.");
                    scanner.next();
                    break;
                }
                double examScore = scanner.nextDouble();

                // Валидация балла (от 0 до 100)
                if (examScore < 0 || examScore > 100) {
                    System.out.println("Invalid score. Enter a value from 0 to 100.");
                    break;
                }

                examPoints += examScore;
                examCount++;
                System.out.println("Exam score added.");
                break;

            case 3:
                // Проверка на пустые данные (защита от деления на ноль)
                if (assignmentCount == 0 && examCount == 0) {
                    System.out.println("\nNo results available yet.");
                    break;
                }

                System.out.println("\n----- CURRENT RESULTS -----");

                double assignmentAverage = 0;
                double examAverage = 0;
                double overallAverage = 0;

                if (assignmentCount > 0) {
                    assignmentAverage = assignmentPoints / assignmentCount;
                    System.out.println("Assignments completed: " + assignmentCount);
                    System.out.printf("Assignment average: %.1f\n", assignmentAverage);
                } else {
                    System.out.println("Assignments completed: 0");
                    System.out.println("Assignment average: 0.0");
                }

                System.out.println();

                if (examCount > 0) {
                    examAverage = examPoints / examCount;
                    System.out.println("Exams completed: " + examCount);
                    System.out.printf("Exam average: %.1f\n", examAverage);
                } else {
                    System.out.println("Exams completed: 0");
                    System.out.println("Exam average: 0.0");
                }

                System.out.println();

                // Расчет общего среднего с учетом наличия категорий

                jasmin:
                if (assignmentCount > 0 && examCount > 0) {
                    overallAverage = (assignmentAverage * 0.50) + (examAverage * 0.50);
                } else if (assignmentCount > 0) {
                    overallAverage = assignmentAverage;
                } else {
                    overallAverage = examAverage;
                }

                System.out.printf("Overall average: %.1f\n", overallAverage);

                // Определение буквы оценки (Grade)
                char grade;
                if (overallAverage >= 90) {
                    grade = 'A';
                } else if (overallAverage >= 80) {
                    grade = 'B';
                } else if (overallAverage >= 70) {
                    grade = 'C';
                } else if (overallAverage >= 60) {
                    grade = 'D';
                } else {
                    grade = 'F';
                }

                System.out.println("Grade: " + grade);

                // Статус (Passing / Failing)
                String status = (overallAverage >= 60) ? "Passing" : "Failing";
                System.out.println("Status: " + status);
                break;

            case 4:
                System.out.println("Exiting Grade Tracker. Goodbye!");
                break;

            default:
                System.out.println("Invalid option. Please choose between 1 and 4.");
                break;
        }
    } while (choice != 4);

    scanner.close();
}
}