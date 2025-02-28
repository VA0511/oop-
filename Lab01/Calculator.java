import java.util.Scanner;

public class Calculator {
    public static void main(String args[]){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            String StrNum1 = scanner.nextLine();
            System.out.print("Enter second number: ");
            String StrNum2 = scanner.nextLine();

            double num1 = Double.parseDouble(StrNum1);
            double num2 = Double.parseDouble(StrNum2);

            double sum = num1 + num2;
            double difference = num1 - num2;
            double product = num1 * num2;
            double quotient = 0;

            if(num2 == 0) {
                System.out.println("Error.");
            } else {
                quotient = num1 / num2;
            }

            System.out.println("Sum: " + sum);
            System.out.println("Difference: " + difference);
            System.out.println("Product: " + product);
            System.out.println("Quotient: " + quotient);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
