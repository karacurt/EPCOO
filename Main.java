import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int op = 0; op != -1;) {

            System.out.println("=============== MENU ================");
            System.out.println("1 - Marcador de Reuniao");
            System.out.println("2 - Gerenciador de Salas");
            System.out.println("-1 - Sair");
            op = sc.nextInt();

            switch (op) {
            case 1:
                MarcadorDeReuniao();
                break;
            case 2:
                GerenciadorDeSalas();
                break;
            default:
                System.out.println("Opcao invalida");
            }
        }

        sc.close();

    }

    public static void MarcadorDeReuniao() {

        // ============= EP parte 1 ==============

        MarcadorDeReuniao marcador = new MarcadorDeReuniao();
        Collection<String> participantes = new ArrayList<String>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("\n======= Defina o intervalo entre datas para reunião (dd/mm/yyyy) =======");

        System.out.print("De: ");
        LocalDate dataInicial = LocalDate.parse(sc.nextLine(), formatter);

        System.out.print("Até: ");
        LocalDate dataFinal = LocalDate.parse(sc.nextLine(), formatter);

        String email = "a";
        System.out.println("\n====== Insira o email dos participantes (entrada vazia para encerrar) ======");
        for (int i = 1; !email.equals(""); i++) {

            System.out.print("Insira o email do participante " + i + ": ");
            email = sc.nextLine();
            if (!email.equals(""))
                participantes.add(email);
            else
                break;
        }
        // Marcar reuniao com intervalo de data e participantes
        marcador.marcarReuniaoEntre(dataInicial, dataFinal, participantes);

        // Intervalo de cada participante
        for (String participante : participantes) {

            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            System.out.println("\n====== Insira o horarios livres de " + participante + " ============");

            for (String x = ""; !x.toUpperCase().equals("N");) {

                System.out.print("Data (dd/mm/yyyy): ");
                String data = sc.nextLine() + " ";

                System.out.print("De (hh:mm): ");
                String hrInit = sc.nextLine();
                LocalDateTime dtInit = LocalDateTime.parse(data + hrInit, formatter);

                System.out.print("Até (hh:mm): ");
                String hrFinal = sc.nextLine();
                LocalDateTime dtFinal = LocalDateTime.parse(data + hrFinal, formatter);

                // Adiciona o intervalo na lista de datas
                marcador.indicaDisponibilidadeDe(participante, dtInit, dtFinal);

                System.out.print("Inserir outro horário? (S - Sim, N - Nao): ");
                x = sc.nextLine();
            }
        }

        marcador.mostrarSobreposicao();
        sc.close();
    }

    public static void GerenciadorDeSalas() {

        // ===================== EP PARTE 2 =======================

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        GerenciadorDeSalas gerenciador = new GerenciadorDeSalas();

        for (int op = 0; op != -1;) {

            System.out.println("=============== MENU ================");
            System.out.println("1 - Adicionar sala");
            System.out.println("2 - Remover sala");
            System.out.println("3 - Reservar sala");
            System.out.println("4 - Cancelar reserva");
            System.out.println("5 - Exibir reservas da Sala");
            System.out.println("-1 - Sair");
            op = sc.nextInt();

            switch (op) {

            case 1:
                for (String x = ""; !x.toUpperCase().equals("N");) {
                    System.out.print("Insira o nome da Sala: ");
                    String nomeSala = sc.nextLine();

                    System.out.print("Capacidade: ");
                    int capacidade = sc.nextInt();

                    gerenciador.adicionaSalaChamada(nomeSala, capacidade);

                    System.out.print("Inserir outra Sala? (S - Sim, N - Nao): ");
                    x = sc.nextLine();
                }
                break;
            case 2:
                for (String x = ""; !x.toUpperCase().equals("N");) {

                    System.out.println("Insira o nome da Sala");
                    String nomeSala = sc.nextLine();

                    gerenciador.removeSalaChamada(nomeSala);

                    System.out.print("Remover outra sala? (S - Sim, N - Nao): ");
                    x = sc.nextLine();
                }
                break;

            case 3:

                for (String x = ""; !x.toUpperCase().equals("N");) {

                    System.out.print("Nome da Sala: ");
                    String nomeSala = sc.nextLine();

                    System.out.print("Data (dd/mm/yyyy): ");
                    String data = sc.nextLine() + " ";

                    System.out.print("De (hh:mm): ");
                    String hrInit = sc.nextLine();
                    LocalDateTime dtInit = LocalDateTime.parse(data + hrInit, formatter);

                    System.out.print("Até (hh:mm): ");
                    String hrFinal = sc.nextLine();
                    LocalDateTime dtFinal = LocalDateTime.parse(data + hrFinal, formatter);

                    // Adiciona o intervalo na lista de datas
                    gerenciador.reservaSalaChamada(nomeSala, dtInit, dtFinal);

                    System.out.print("Deseja efetuar uma nova reserva? (S - Sim, N - Nao): ");
                    x = sc.nextLine();
                }
                break;

            case 4:
                for (String x = ""; !x.toUpperCase().equals("N");) {

                    System.out.print("Nome da Sala: ");
                    String nomeSala = sc.nextLine();

                    System.out.print("Data (dd/mm/yyyy): ");
                    String data = sc.nextLine() + " ";

                    System.out.print("De (hh:mm): ");
                    String hrInit = sc.nextLine();
                    LocalDateTime dtInit = LocalDateTime.parse(data + hrInit, formatter);

                    System.out.print("Até (hh:mm): ");
                    String hrFinal = sc.nextLine();
                    LocalDateTime dtFinal = LocalDateTime.parse(data + hrFinal, formatter);

                    for (Reserva reserva : gerenciador.reservas) {
                        if (reserva.getSala().getNome().equals(nomeSala)) {
                            if (reserva.getDtInit().equals(dtInit) && reserva.getDtFinal().equals(dtFinal)) {
                                gerenciador.cancelaReserva(reserva);
                            }
                        }
                    }

                    System.out.print("Cancelar outra reserva? (S - Sim, N - Nao): ");
                    x = sc.nextLine();
                }
                break;

            case 5:
                for (String x = ""; !x.toUpperCase().equals("N");) {

                    System.out.println("Insira o nome da Sala");
                    String nomeSala = sc.nextLine();

                    for (Sala sala : gerenciador.salas) {
                        if (sala.getNome().equals(nomeSala)) {
                            gerenciador.imprimeReservasDaSala(sala);
                        }
                    }

                    System.out.print("Exibir reservas de outra sala? (S - Sim, N - Nao): ");
                    x = sc.nextLine();
                }
                break;
            default:
                System.out.println("Opcao invalida");
                sc.close();
            }

        }
    }

}