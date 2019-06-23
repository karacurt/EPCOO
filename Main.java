import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        for (String op = "-1"; !op.equals("0");) {

            System.out.println("=============== MENU ================");
            System.out.println("1 - Marcador de Reuniao");
            System.out.println("2 - Gerenciador de Salas");
            System.out.println("0 - Sair");

            op = sc.nextLine();
            // sc.nextLine();

            switch (op) {
            case "0":
                System.exit(0);
                break;
            case "1":
                MarcadorDeReuniao();
                break;
            case "2":
                GerenciadorDeSalas();
                break;
            default:
                System.out.println("opcao invalida");

            }

        }

        sc.close();

    }

    public static void MarcadorDeReuniao() throws Exception {

        // ============= EP parte 1 ==============

        MarcadorDeReuniao marcador = new MarcadorDeReuniao();
        Collection<String> participantes = new ArrayList<String>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        try {
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
                    try {
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
                    } catch (DateTimeParseException ex) {
                        System.out.println("Erro! Por favor insira as informções na formatação correta!");
                    }

                }
            }

            marcador.mostrarSobreposicao();
            sc.close();

        } catch (DateTimeParseException ex) {
            System.out.println("Erro! Por favor insira as informções na formatação correta!");
        }

    }

    public static void GerenciadorDeSalas() throws Exception {

        // ===================== EP PARTE 2 =======================

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        GerenciadorDeSalas gerenciador = new GerenciadorDeSalas();

        try {
            for (int op = -1; op != 0;) {
                try {
                    System.out.println("=============== MENU ================");
                    System.out.println("1 - Adicionar sala");
                    System.out.println("2 - Remover sala");
                    System.out.println("3 - Reservar sala");
                    System.out.println("4 - Cancelar reserva");
                    System.out.println("5 - Exibir reservas da Sala");
                    System.out.println("6 - Imprimir Salas");
                    System.out.println("0 - Sair");
                    op = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException ex) {
                    System.out.println("Erro! Tipo de dado inserido é inválido. Insira apenas numeros inteiros!");
                }

                switch (op) {
                case 0:
                    System.exit(0);
                    break;
                case 1: // adicionar sala
                    for (String x = ""; !x.toUpperCase().equals("N");) {

                        System.out.print("Insira o nome da Sala: ");
                        String nomeSala = sc.nextLine();

                        System.out.print("Capacidade: ");
                        int capacidade = Integer.parseInt(sc.nextLine());

                        gerenciador.adicionaSalaChamada(nomeSala, capacidade);

                        System.out.print("Inserir outra Sala? (S - Sim, N - Nao): ");
                        x = sc.nextLine();
                        System.out.println();
                    }
                    break;
                case 2: // remover salas
                    for (String x = ""; !x.toUpperCase().equals("N");) {

                        System.out.println("Insira o nome da Sala");
                        String nomeSala = sc.nextLine();

                        gerenciador.removeSalaChamada(nomeSala);

                        System.out.print("Remover outra sala? (S - Sim, N - Nao): ");
                        x = sc.nextLine();
                        System.out.println();
                    }
                    break;

                case 3:// reservar sala

                    for (String x = ""; !x.toUpperCase().equals("N");) {

                        System.out.print("Nome da Sala: ");
                        String nomeSala = sc.nextLine();

                        try {
                            System.out.print("Data (dd/mm/yyyy): ");
                            String data = sc.next() + " ";

                            System.out.print("De (hh:mm): ");
                            String hrInit = sc.next();
                            LocalDateTime dtInit = LocalDateTime.parse(data + hrInit, formatter);

                            System.out.print("Até (hh:mm): ");
                            String hrFinal = sc.next();
                            LocalDateTime dtFinal = LocalDateTime.parse(data + hrFinal, formatter);
                            gerenciador.reservaSalaChamada(nomeSala, dtInit, dtFinal);
                        } catch (DateTimeParseException ex) {
                            System.out.println("Erro! Por favor insira os dados na formatação correta!");
                        }
                        // Adiciona o intervalo na lista de datas

                        sc.nextLine();
                        System.out.print("Deseja efetuar uma nova reserva? (S - Sim, N - Nao): ");
                        x = sc.nextLine();
                        System.out.println();
                    }
                    break;

                case 4: // cancelar reserva
                    for (String x = ""; !x.toUpperCase().equals("N");) {

                        try {
                            System.out.print("\nNome da Sala: ");
                            String nomeSala = sc.nextLine();

                            System.out.print("Data (dd/mm/yyyy): ");
                            String data = sc.nextLine() + " ";

                            System.out.print("De (hh:mm): ");
                            String hrInit = sc.nextLine();
                            LocalDateTime dtInit = LocalDateTime.parse(data + hrInit, formatter);

                            System.out.print("Até (hh:mm): ");
                            String hrFinal = sc.nextLine();
                            LocalDateTime dtFinal = LocalDateTime.parse(data + hrFinal, formatter);

                            if (!gerenciador.reservas.iterator().hasNext())
                                System.out.println("Não há reservas cadastradas ainda!");
                            else
                                for (Reserva reserva : gerenciador.reservas)
                                    if (reserva.getSala().getNome().equals(nomeSala)
                                            && reserva.getDtInit().equals(dtInit)
                                            && reserva.getDtFinal().equals(dtFinal))
                                        if (gerenciador.cancelaReserva(reserva)) {
                                            System.out.println("Reserva cancelada!");
                                            break;
                                        }

                        } catch (DateTimeParseException ex) {
                            System.out.println("Erro! Por favor insira as informções na formatação correta!");
                        }

                        System.out.print("Efetuar nova operação? (S - Sim, N - Nao): ");
                        x = sc.nextLine();

                    }
                    break;

                case 5: // exibir reservas da sala
                    for (String x = ""; !x.toUpperCase().equals("N");) {

                        System.out.print("\nInsira o nome da Sala: ");
                        String nomeSala = sc.nextLine();

                        for (Sala sala : gerenciador.salas)
                            if (sala.getNome().equals(nomeSala))
                                gerenciador.imprimeReservasDaSala(sala);

                        System.out.print("Exibir reservas de outra sala? (S - Sim, N - Nao): ");
                        x = sc.nextLine();
                    }
                    break;
                case 6: // imprimir salas
                    for (Sala sala : gerenciador.salas)
                        gerenciador.imprimeReservasDaSala(sala);
                    break;
                default:
                    System.out.println("Opcao invalida");
                }

            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}