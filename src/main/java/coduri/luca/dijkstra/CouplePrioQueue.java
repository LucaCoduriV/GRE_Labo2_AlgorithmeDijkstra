package coduri.luca.dijkstra;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Cette classe permet principalement d'optimiser la fonction contains d'une priority queue.
 * @implNote Cette classe est visible uniquement par le package.
 * Cela permet d'éviter que des méthodes soient appelées par des classes extérieures par accident.
 */
class CouplePrioQueue extends PriorityQueue<Couple>{
    private final Couple[] couples;

    CouplePrioQueue(Couple[] couples) {
        super(Arrays.asList(couples));
        this.couples = Arrays.copyOf(couples, couples.length);
    }

    /**
     * Permet de vérifier qu'un couple a déjà été sorti de la priority queue.
     * @param o Le couple à vérifier.
     * @return vrai s'il est encore dans la priority queue.
     */
    @Override
    public boolean contains(Object o) {
        final Couple couple = (Couple) o;
        return !couple.isCompleted();
    }

    /**
     * Cette méthode permet de récupérer le couple le plus prioritaire et indiqué qu'il est n'est plus à traiter.
     * @return le couple le plus prioritaire
     */
    @Override
    public Couple poll() {
        final Couple couple = super.poll();
        if(couple != null) {
            couple.setCompleted(true);
        }
        return couple;
    }

    public Couple get(int index) {
        return couples[index];
    }

    Couple[] couples() {
        return couples;
    }

    public Couple[] getCouples() {
        return Arrays.copyOf(couples, couples.length);
    }

    public void update(Couple s){
        remove(s);
        add(s);
    }
}
