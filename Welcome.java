import java.util.Scanner;

public class Welcome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        
        System.out.println("Your name is " + firstName + " " + lastName);
        
        scanner.close();
    }
}