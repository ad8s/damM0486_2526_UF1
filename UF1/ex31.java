import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ex31 {
    public static void main(String[] args) {
        String file = "text.bin";
        Random random = new Random();
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            int codi = random.nextInt(3) + 1;
            for (int i = 0; i < 10; i++) {
                dos.writeInt(codi);
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < 3; j++) {
                    char word = (char) ('a' + random.nextInt(26));
                    dos.writeChar(word);
                    sb.append(word);
                }
                System.out.println(codi + ":" + sb);
                codi += random.nextInt(3) + 1;
            }
            System.out.println("s'ha generat l'arxiu: " + file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
