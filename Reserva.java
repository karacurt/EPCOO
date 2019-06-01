import java.time.LocalDateTime;;

class Reserva implements Comparable<Reserva> {
    protected LocalDateTime dtInit, dtFinal;
    protected Sala sala;

    Reserva(Sala sala, LocalDateTime dtInit, LocalDateTime dtFinal) {
        this.dtInit = dtInit;
        this.dtFinal = dtFinal;
        this.sala = sala;
    }

    public int compareTo(Reserva reserva) {

        if (this.dtInit.isBefore(reserva.getDtInit()) && this.dtFinal.isBefore(reserva.getDtInit())) {
            // reserva esta antes
            return 1;
        } else if (this.dtInit.isAfter(reserva.getDtFinal()) && this.dtFinal.isAfter(reserva.getDtFinal())) {
            // reserva esta depois
            return -1;
        } else {
            // reserva esta entre alguma outra ou coincide o inicio ou o fim
            return 0;
        }
    }

    // GETTERS
    Sala getSala() {
        return this.sala;
    }

    LocalDateTime getDtInit() {
        return this.dtInit;
    }

    LocalDateTime getDtFinal() {
        return this.dtFinal;
    }

}