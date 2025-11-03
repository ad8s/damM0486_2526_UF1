import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ex21 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Introdueix el nom del fitxer: ");
        String fileName = input.nextLine();

        int count = 0;

        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'a') {
                        count++;
                    }
                }
            }

            sc.close();
            System.out.println("La lletra 'a' apareix " + count + " vegades al fitxer.");
        } catch (FileNotFoundException e) {
            System.out.println("Introdueix un fitxer valid!");
        }
    }
}
