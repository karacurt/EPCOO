import java.time.LocalDateTime;;

class Reserva {
    protected LocalDateTime dtInit, dtFinal;

    Reserva(LocalDateTime dtInit, LocalDateTime dtFinal) {
        this.dtInit = dtInit;
        this.dtFinal = dtFinal;
    }
}