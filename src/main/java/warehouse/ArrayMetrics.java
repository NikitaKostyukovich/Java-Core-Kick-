package warehouse;

public class ArrayMetrics {
    private final long id;
    private final int sum;
    private final int min;
    private final int max;
    private final double average;

    public ArrayMetrics(long id, int sum, int min, int max, double average) {
        this.id = id;
        this.sum = sum;
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public long getId() { return id; }
    public int getSum() { return sum; }
    public int getMin() { return min; }
    public int getMax() { return max; }
    public double getAverage() { return average; }
}
