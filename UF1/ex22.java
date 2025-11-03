import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex22 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userInput = "";
        try (FileOutputStream fos = new FileOutputStream("usertext.txt")) {
            while (!userInput.equalsIgnoreCase("quit")) {
                System.out.print("Introdueix un string (excriu 'quit' per finalitzar): ");
                userInput = input.nextLine();
                if (!userInput.equalsIgnoreCase("quit"))
                    fos.write((userInput + System.lineSeparator()).getBytes());
            }
        } catch (IOException e) {
            System.out.println("Error escribin en l'arxiu: " + e.getMessage());
            return;
        } finally {
            input.close();
        }

        try (FileInputStream fis = new FileInputStream("usertext.txt")) {
            int b;
            StringBuilder sb = new StringBuilder();
            while ((b = fis.read()) != -1)
                sb.append((char) b);
            System.out.println("\nContingut de l'arxiu:");
            System.out.println(sb.toString());
        } catch (IOException e) {
            System.out.println("Error llegin l'arxiu: " + e.getMessage());
        }
    }
}
