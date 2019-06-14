import java.util.TreeSet;
import java.util.Collection;
import java.time.LocalDateTime;

class Sala {
    protected String nome, local, observacoes;
    protected int capacidade;
    protected TreeSet<Reserva> reservas = new TreeSet<Reserva>();

    Sala(String nome, int capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.local = "";
        this.observacoes = "";
    }

    // GETTERS
    String getNome() {
        return this.nome;
    }

    String getLocal() {
        return this.local;
    }

    String getObservacoes() {
        return this.observacoes;
    }

    int getCapacidade() {
        return this.capacidade;
    }

    Reserva reservar(LocalDateTime dtInit, LocalDateTime dtFinal) {
        Reserva value = new Reserva(this, dtInit, dtFinal);

        for (Reserva reserva : this.reservas) {
            if (reserva.compareTo(value) == 0) { // verifica se nao ha conflito
                System.out.println("Hora de inicio ou fim conflitantes");
                return null;
            }
        }
        this.reservas.add(value);
        return value;
    }

    Collection<Reserva> reservasParaSala() {
        return this.reservas;
    }

}