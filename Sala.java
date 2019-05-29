class Sala {
    protected String nomeSala;
    protected int maxCapacity;

    Sala(String nomeSala, int maxCapacity) {
        this.nomeSala = nomeSala;
        this.maxCapacity = maxCapacity;
    }

    String getName() {
        return this.nomeSala;
    }
}