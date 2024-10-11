import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class clientpaper {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("192.168.0.232", 25565);
            System.out.println("\nConectado ao servidor!");

            PrintWriter pr = new PrintWriter(cliente.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            System.out.println("pedra, papel ou tesoura?\nSua escolha: ");
            String saida = scanner.nextLine(); // Captura a mensagem digitada pelo usuário

            pr.println(saida);
            pr.flush();

            InputStreamReader entrada = new InputStreamReader(cliente.getInputStream());
            BufferedReader buffer = new BufferedReader(entrada);

            String resposta = buffer.readLine();
            System.out.println(resposta);

            cliente.close();
            scanner.close();
            System.out.println("Conexão encerrada");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
