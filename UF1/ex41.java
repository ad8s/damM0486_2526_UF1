import java.io.*;
import java.util.Scanner;

public class ex41 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el teu user: ");
        String nom = sc.nextLine();
        System.out.println("Introdueix el teu password: ");
        String pass = sc.nextLine();
        String fileName = nom + ".usr";
        File userFile = new File(fileName);
        if (userFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
                User user = (User) ois.readObject();
                if (user.pass.equals(pass)) {
                    System.out.println("Accés correcte al sistema");
                } else {
                    System.err.println("Accés no concedit: La contrasenya no es correcte");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.err.println("No s'ha trobat l'usuari, vols registrarte? (si/no)");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("sí") || input.equals("si")) {
                User newUser = new User(nom, pass);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(newUser);
                    System.out.println("Usuari registrat correctement!");
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.println("Registre cancel·lat");
            }
        }
        sc.close();
    }

    public static class User implements Serializable {
        String nom;
        String pass;

        public User(String nom, String pass) {
            this.nom = nom;
            this.pass = pass;
        }
    }
}
