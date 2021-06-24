import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class DeliveryCostTest {

    @Test
    void minimumDeliveryCostTest() throws Exception {
        assertEquals(DeliveryCost.getDeliveryCost(11, false, false,
                DeliveryCost.WORKLOAD.DEFAULT),400);
    }

    @Test
    void impossibleDeliveryFragileLoadOverLongDistanceTest(){
        Exception exception = assertThrows(Exception.class, () -> {
            assertEquals(DeliveryCost.getDeliveryCost(31, false, true,
                    DeliveryCost.WORKLOAD.DEFAULT),0);
        });
        assertEquals(exception.getMessage(),DeliveryCost.FRAGILE_LOAD_ERROR_MESSAGE);
    }

    @Test
    void invalidDistanceTest(){
        Exception exception = assertThrows(Exception.class, () -> {
            assertEquals(DeliveryCost.getDeliveryCost(0, false, true,
                    DeliveryCost.WORKLOAD.DEFAULT),0);
        });
        assertEquals(exception.getMessage(),DeliveryCost.INVALID_DISTANCE_ERROR_MESSAGE);
    }

    @ParameterizedTest(name="[{0}] Distance: {1}, Workload: {2}, isFragileLoad: {3}, isHeavyLoad: {4}, Result: {5}")
    @CsvFileSource(resources = "Pairwise.csv")
    void deliveryCostParameterizedTest(int number, int distance, DeliveryCost.WORKLOAD workload,
                                  Boolean isFragileLoad, Boolean isHeavyLoad, int result) throws Exception {
        assertEquals(DeliveryCost.getDeliveryCost(distance, isHeavyLoad, isFragileLoad,
                workload),result);
    }
}
