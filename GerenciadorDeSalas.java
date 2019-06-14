import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

class GerenciadorDeSalas {
    protected List<Sala> salas = new ArrayList<Sala>();
    protected TreeSet<Reserva> reservas = new TreeSet<Reserva>();

    // SETTERS
    void adicionaSalaChamada(String nomeSala, int maxCapacity) {
        boolean isNameFree = true;
        for (Sala sala : this.salas) {
            if (sala.getNome().equals(nomeSala))
                isNameFree = false;
        }
        if (isNameFree) {
            this.adicionaSala(new Sala(nomeSala, maxCapacity));
            System.out.println("Sala adicionada com sucesso!");
        } else {
            System.out.println("Nome da sala já está sendo usado. Por favor escolha outro nome!");
        }
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

            }
        }
        if (reserva == null) {
            System.out.println("Horario nao disponivel");
        } else {
            System.out.println("Reservou");
            this.reservas.add(reserva);
        }
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

    boolean cancelaReserva(Reserva reserva) {

        for (Sala sala : this.salas)
            if (sala.equals(reserva.getSala()) && sala.reservas.contains(reserva) && this.reservas.contains(reserva))
                if (sala.reservas.remove(reserva) && this.reservas.remove(reserva))
                    return true;

        return false;
    }

    void imprimeReservasDaSala(Sala sala) {

        int n = 1;
        System.out.println("\n\n#################### SALA: " + sala.getNome() + " #########################");
        for (Reserva reserva : sala.reservas) {
            System.out.println("\n======== RESERVA " + n + " ===========");
            System.out.println("Inicio: " + reserva.getDtInit());
            System.out.println("Termino: " + reserva.getDtFinal());
            n++;
        }
        if (!sala.reservas.iterator().hasNext())
            System.out.println("Sem reservas\n");
    }

}