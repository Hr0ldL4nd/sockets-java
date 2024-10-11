import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class serverpaper {
    public static void main(String[] args) {
        try {
            String[] opcoes = { "pedra", "papel", "tesoura" };
            Random aleatorio = new Random();

            ServerSocket servidor = new ServerSocket(25565);
            System.out.println("\nServidor ouvindo a porta 25565");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

            InputStreamReader entrada = new InputStreamReader(cliente.getInputStream());
            BufferedReader buffer = new BufferedReader(entrada);
            PrintWriter pr = new PrintWriter(cliente.getOutputStream());

            String escolha_client = buffer.readLine();
            System.out.println("O cliente escolheu: " + escolha_client);

            // ESCOLHA DO SERVIDOR
            String escolha_server = opcoes[aleatorio.nextInt(3)];
            String resultado = "opa";

            if (!escolha_client.equals(escolha_server)) {
                if (escolha_server.equals("pedra")) {
                    if (escolha_client.equals("papel")) {
                        resultado = "ganhou";
                    } else {
                        resultado = "perdeu";
                    }
                }

                if (escolha_server.equals("papel")) {
                    if (escolha_client.equals("tesoura")) {
                        resultado = "ganhou";
                    } else {
                        resultado = "perdeu";
                    }
                }

                if (escolha_server.equals("tesoura")) {
                    if (escolha_client.equals("pedra")) {
                        resultado = "ganhou";
                    } else {
                        resultado = "perdeu";
                    }
                }

            } else {
                resultado = "empatou";
            }

            pr.println("O servidor escolheu: " + escolha_server + ". Você " + resultado + ".");
            pr.flush();

            cliente.close();
            servidor.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
