import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex32 {
    public static void main(String[] args) {
        String fileName = "text.bin";
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix un codi (ex: 6): ");
        int codeFound = sc.nextInt();
        boolean found = false;
        boolean finish = false;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            while (!found && !finish) {
                try {
                    int code = dis.readInt();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 3; i++) {
                        sb.append(dis.readChar());
                    }

                    if (code == codeFound) {
                        System.out.println("S'ha trobat el codi: " + sb);
                        found = true;
                    }
                } catch (EOFException e) {
                    finish = true;
                }
            }
            if (!found) {
                System.out.println("No s'ha trobat el codi");
            }
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer: " + e.getMessage());
        } finally {
            sc.close();
        }

    }
}