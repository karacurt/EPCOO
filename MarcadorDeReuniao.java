import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.Iterator;

class MarcadorDeReuniao {
    protected LocalDate dataInicial = null;
    protected LocalDate dataFinal = null;
    protected Collection<String> participantes = new ArrayList<String>();
    protected TreeSet<Node> horarios = new TreeSet<Node>();

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> participantes) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.participantes = participantes;
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {

        // Verifica se a data esta no intervalo de dias selecionado pelo organizador
        if (this.dataInicial.isBefore(inicio.toLocalDate()) || this.dataInicial.isEqual(inicio.toLocalDate())) {
            if (this.dataFinal.isAfter(fim.toLocalDate()) || this.dataFinal.isEqual(fim.toLocalDate())) {
                this.horarios.add(new Node('I', inicio));
                this.horarios.add(new Node('F', fim));
            }
        }

    }

    public void mostrarSobreposicao() {
        TreeSet<Node> teste = (TreeSet) this.horarios.descendingSet();
        Iterator iterador = teste.iterator();
        int i = 0;

        while (iterador.hasNext()) {

            Node n = (Node) iterador.next();

            if (n.getTipo() == 'I')
                ++i;
            else
                --i;

            // System.out.println("======= INFO ======");
            // System.out.println("Tamanho: "+ this.participantes.size());
            // System.out.println("contador: "+i);
            // System.out.println("Data: "+ n.getData());
            // System.out.println("Tipo: "+ n.getTipo());
            // System.out.println();

            if (i == this.participantes.size()) {

                System.out.println("\n====== INTERVALO ========");
                System.out.println("Data Inicio: " + n.getData());
                Node f = (Node) iterador.next();
                System.out.println("Data Termino: " + f.getData());

                // alteracao ao inves de fazer iterador2 = iterador
                if (f.getTipo() == 'I')
                    ++i;
                else
                    --i;
            }
        }

    }

}