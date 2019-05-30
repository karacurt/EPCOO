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
        if (this.dtInit.isEqual(reserva.getDtInit()) || this.dtFinal.isEqual(reserva.getDtFinal())) {
            return 0;
        } else if (this.dtInit.isBefore(reserva.getDtInit()) && this.dtFinal.isBefore(reserva.getDtFinal())) {
            return -1;
        } else if (this.dtInit.isAfter(reserva.getDtInit()) || this.dtFinal.isAfter(reserva.getDtFinal())) {
            return 1;
        } else {
            return 2;
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