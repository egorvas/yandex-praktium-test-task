public class DeliveryCost {

    private static final int MINIMUM_DELIVERY_COST = 400;
    private static final int FRAGILE_DELIVERY_COST = 300;
    private static final int FRAGILE_ALLOWED_DISTANCE = 30;

    public static final String INVALID_DISTANCE_ERROR_MESSAGE = "Invalid distance";
    public static final String FRAGILE_LOAD_ERROR_MESSAGE = "Impossible to deliver fragile load over 30 km";

    public enum WORKLOAD{
        DEFAULT(1),
        LOW(1.2),
        MEDIUM(1.4),
        HIGH(1.6);

        private final double value;

        WORKLOAD(final double newValue) {
            value = newValue;
        }

        public double getValue() { return value; }
    }



    public static double getDeliveryCost(double distance, boolean isHeavyLoad, boolean isFragileLoad, WORKLOAD workload) throws Exception {
        if (distance<=0) throw new Exception(INVALID_DISTANCE_ERROR_MESSAGE);
        double cost = isHeavyLoad ? 200:100;
        if (distance>30){
            cost += 300;
        }else if(distance>10){
            cost += 200;
        }else if (distance>2){
            cost += 100;
        }else{
            cost += 50;
        }
        cost += isFragileLoad? FRAGILE_DELIVERY_COST: 0;
        cost = cost * workload.getValue();
        if (isFragileLoad && distance>FRAGILE_ALLOWED_DISTANCE){
            throw new Exception(FRAGILE_LOAD_ERROR_MESSAGE);
        }else{
            if (cost<MINIMUM_DELIVERY_COST) cost = MINIMUM_DELIVERY_COST;
        }
        return cost;
    }
}
