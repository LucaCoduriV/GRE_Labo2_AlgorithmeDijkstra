package coduri.luca.dijkstra;


import java.util.Arrays;
import java.util.PriorityQueue;

class CouplePrioQueue extends PriorityQueue<Couple>{
    private final Couple[] couples;

    CouplePrioQueue(Couple[] couples) {
        super(Arrays.asList(couples));
        this.couples = Arrays.copyOf(couples, couples.length);
    }

    @Override
    public boolean contains(Object o) {
        final Couple couple = (Couple) o;
        return !couple.isCompleted();
    }

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

    public Couple[] getCouples() {
        return couples;
    }

    public void update(Couple s){
        remove(s);
        add(s);
    }
}
