import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

class GerenciadorDeSalas {
    protected List<Sala> salas = new ArrayList<Sala>();
    protected TreeSet<Reserva> reservas = new TreeSet<Reserva>();

    // SETTERS
    void adicionaSalaChamada(String nomeSala, int maxCapacity) {
        this.adicionaSala(new Sala(nomeSala, maxCapacity));
    }

    void adicionaSala(Sala sala) {
        this.salas.add(sala);
    }

    // GETTERS
    List<Sala> listaDeSalas() {
        return this.salas;
    }

    Reserva reservaSalaChamada(String nomeSala, LocalDateTime dtInit, LocalDateTime dtFinal) {
        Reserva reserva = null;

        for (Sala sala : this.salas) {
            if (sala.getNome().equals(nomeSala)) {
                imprimeReservasDaSala(sala);
                reserva = sala.reservar(dtInit, dtFinal);
                imprimeReservasDaSala(sala);
                System.out.println("Reservou");
            }
        }
        if (reserva == null)
            System.out.println("Horario nao disponivel");
        else
            this.reservas.add(reserva);
        return reserva;
    }

    String removeSalaChamada(String nomeSala) {
        for (Sala sala : this.salas) {
            if (sala.getNome().equals(nomeSala)) {
                this.salas.remove(sala);
                return "Sala removida";
            }
        }
        return "Sala não encontrada";
    }

    // CONTROLERS

    void cancelaReserva(Reserva value) {
        if (reservas.contains(value)) {
            value.getSala().reservasParaSala().remove(value);
            reservas.remove(value);
        }
    }

    void imprimeReservasDaSala(Sala sala) {
        int n = 1;
        System.out.println("*********** SALA  " + sala.getNome() + " **********");
        for (Reserva reserva : sala.reservas) {
            System.out.println("======== RESERVA " + n + " ===========");
            System.out.println("Inicio: " + reserva.getDtInit());
            System.out.println("Termino: " + reserva.getDtFinal());
            n++;
        }
        if (!sala.reservas.iterator().hasNext())
            System.out.println("Sem reservas");
    }

}