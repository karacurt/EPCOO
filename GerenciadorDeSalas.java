import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

class GerenciadorDeSalas {
    protected List<Sala> salas = new ArrayList<Sala>();
    protected TreeSet<Reserva> reservas = new TreeSet<Reserva>();

    // SETTERS
    void adicionaSalaChamada(String nomeSala, int maxCapacity) {
        this.salas.add(new Sala(nomeSala, maxCapacity));
    }

    void adicionaSala(Sala sala) {
        this.salas.add(sala);
    }

    // GETTERS
    List<Sala> listaDeSalas() {
        return this.salas;
    }

    // CONTROLERS

    String removeSalaChamada(String nomeSala) {
        for (Sala sala : this.salas) {
            if (sala.getNome().equals(nomeSala)) {
                this.salas.remove(sala);
                return "Sala removida";
            }
        }
        return "Sala não encontrada";
    }

    Reserva reservaSalaChamada(String nomeSala, LocalDateTime dtInit, LocalDateTime dtFinal) {
        Reserva reserva = null;

        for (Sala sala : this.salas) {
            if (sala.getNome().equals(nomeSala)) {
                reserva = sala.reservar(dtInit, dtFinal);
                System.out.println("Reservou");
            }
        }
        return reserva;
    }

    void cancelaReserva(Reserva reserva) {

    }

    void imprimeReservasDaSala(Sala sala) {
        sala.imprime();
    }

}