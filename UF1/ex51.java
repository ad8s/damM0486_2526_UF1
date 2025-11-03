import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ex51 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MIDA_REGISTRE = 174;

        try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw")) {
            long midaFitxer = fitxer.length();
            int numRegistres = (int) (midaFitxer / MIDA_REGISTRE);
            for (int i = 0; i < numRegistres; i++) {
                int id = fitxer.readInt();
                String nom = readChars(fitxer, 40).trim();
                String codi = readChars(fitxer, 3).trim();
                String capital = readChars(fitxer, 40).trim();
                int poblacio = fitxer.readInt();
                System.out.printf("%d: %s (%s), Capital: %s, Població: %d\n", id, nom, codi, capital, poblacio);
            }
            System.out.printf("Introdueix l'índex del registre (1..%d): ", numRegistres);
            int index = sc.nextInt();
            sc.nextLine();
            long posicio = (index - 1) * MIDA_REGISTRE;
            fitxer.seek(posicio);
            int id = fitxer.readInt();
            System.out.println("Quin camp vols modificar? (nom / codi / capital / poblacio): ");
            String camp = sc.nextLine().toLowerCase();
            System.out.print("Introdueix el nou valor: ");
            String nouValor = sc.nextLine();
            switch (camp) {
                case "nom":
                    fitxer.seek(posicio + 4);
                    escriuString(fitxer, nouValor, 40);
                    break;
                case "codi":
                    fitxer.seek(posicio + 4 + 80);
                    escriuString(fitxer, nouValor, 3);
                    break;
                case "capital":
                    fitxer.seek(posicio + 4 + 80 + 6);
                    escriuString(fitxer, nouValor, 40);
                    break;
                case "poblacio":
                    fitxer.seek(posicio + 4 + 80 + 6 + 80);
                    fitxer.writeInt(Integer.parseInt(nouValor));
                    break;
                default:
                    System.out.println("Camp no reconegut.");
            }
            fitxer.seek(0);
            for (int i = 0; i < numRegistres; i++) {
                id = fitxer.readInt();
                String nom = readChars(fitxer, 40).trim();
                String codi = readChars(fitxer, 3).trim();
                String capital = readChars(fitxer, 40).trim();
                int poblacio = fitxer.readInt();
                System.out.printf("%d: %s (%s), Capital: %s, Població: %d\n", id, nom, codi, capital, poblacio);
            }
            System.out.println("Modificació feta correctament!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void escriuString(RandomAccessFile fitxer, String text, int mida) throws IOException {
        StringBuilder b = new StringBuilder(text);
        b.setLength(mida);
        fitxer.writeChars(b.toString());
    }

    private static String readChars(RandomAccessFile fitxer, int nChars) throws IOException {
        StringBuilder b = new StringBuilder();
        char ch;
        for (int i = 0; i < nChars; i++) {
            ch = fitxer.readChar();
            if (ch != '\0')
                b.append(ch);
        }
        return b.toString();
    }

}
