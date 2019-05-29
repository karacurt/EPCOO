import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Map;

public class Main {

public static void main(String [] args){

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
            for(int i=1; !email.equals(""); i++ ){

                System.out.print("Insira o email do participante "+i+": ");
                email = sc.nextLine();
                if(!email.equals("")) participantes.add(email);
                else break;
            }
        //Marcar reuniao com intervalo de data e participantes
        marcador.marcarReuniaoEntre(dataInicial, dataFinal, participantes);
        
        //Intervalo de cada participante
        for(String participante : participantes){

            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            System.out.println("\n====== Insira o horarios livres de " + participante + " ============");

            for(String x=""; !x.toUpperCase().equals("N");){

                    System.out.print("Data (dd/mm/yyyy): ");                    
                    String data = sc.nextLine() + " ";

                    System.out.print("De (hh:mm): ");                               
                    String hrInit = sc.nextLine(); 
                    LocalDateTime dtInit = LocalDateTime.parse(data+hrInit, formatter);              
                    
                    System.out.print("Até (hh:mm): ");
                    String hrFinal = sc.nextLine();                    
                    LocalDateTime dtFinal = LocalDateTime.parse(data+hrFinal, formatter);

                //Adiciona o intervalo na lista de datas
                marcador.indicaDisponibilidadeDe(participante, dtInit, dtFinal);                

                System.out.print("Inserir outro horário? (S - Sim, N - Nao): ");
                x = sc.nextLine();
            }     
        }        

        marcador.mostrarSobreposicao();

    }    
}