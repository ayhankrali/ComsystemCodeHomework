package robot;


import com.comsystem.homework.robot.RobotOperations;

import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RobotOperationsTest {
    private final RobotOperations operations = new RobotOperations();

    @Test
    void testExcavateStonesForDays() {
        // the number 4 is exampled of days for testing
        int days = 4;
        RobotPlan result = operations.excavateStonesForDays(days);


        assertEquals(days, result.numberOfDays(),
                "The number of days should match the input.");

        // This is an example assertion based on the understanding that the robot doubles each day for half the time.
        // And then collects stones for the remaining days.
        int expectedStones = (int) Math.pow(2, (double) days / 2) * (days / 2);
        assertEquals(expectedStones, result.numberOfStones(),
                "The number of stones should match the expected output.");

        // Check if the action list contains DIG actions as expected
        assertTrue(result.robotActions().contains(RobotAction.DIG),
                "The action plan should include digging.");
    }


    @Test
    void testDaysRequiredToCollectStones() {

        int stonesToCollect = 1;
        RobotPlan result = operations.daysRequiredToCollectStones(stonesToCollect);

        // For example, if one stone requires at least one-day collecting (assuming no cloning for simplicity)
        int expectedDays = 1;
        assertEquals(expectedDays, result.numberOfDays(),
                "The calculated number of days should be correct for collecting one stone.");
    }


}
