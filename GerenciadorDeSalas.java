import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

class GerenciadorDeSalas {
    protected List<Sala> salas = new ArrayList<Sala>();

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
            if (sala.getName().equals(nomeSala)) {
                this.salas.remove(sala);
                return "Sala removida";
            }
        }
        return "Sala não encontrada";
    }

    Reserva reservaSalaChamada(String nomeSala, LocalDateTime dtInit, LocalDateTime dtFinal) {

        return null;
    }

    void cancelaReserva(Reserva reserva) {

    }

    Collection<Reserva> reservasParaSala() {
        return this.reservas;
    }

}