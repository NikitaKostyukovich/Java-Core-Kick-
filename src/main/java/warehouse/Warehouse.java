package warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<Long, ArrayMetrics> metricsMap;
    private static Warehouse instance;
    private Warehouse(){
        this.metricsMap = new HashMap<>();
    }
    public static Warehouse getInstance(){
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }
    public void saveMetrics(long id, ArrayMetrics metrics){
        this.metricsMap.put(id, metrics);
    }

    public ArrayMetrics getMetrics(long id){
       return this.metricsMap.get(id);
    }
}
