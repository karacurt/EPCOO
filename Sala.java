import java.util.TreeSet;
import java.util.Collection;

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

    TreeSet<Reserva> getReservas() {
        return this.reservas;
    }

    int getCapacidade() {
        return this.capacidade;
    }

    boolean reservar(Reserva value) {

        for (Reserva reserva : this.reservas)
            if (reserva.compareTo(value) == 0) // verifica se nao ha conflito
                return false;

        this.reservas.add(value);
        return true;
    }

    Collection<Reserva> reservasParaSala() {
        return this.reservas;
    }

}