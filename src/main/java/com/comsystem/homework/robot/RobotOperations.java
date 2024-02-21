package com.comsystem.homework.robot;


import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;

import java.util.ArrayList;
import java.util.List;

public class RobotOperations {

    /**
     * An algorithm that converts a number of days into an action plan.
     *
     * @param days the number of days that the robot can work
     * @return The action plan <em>must maximize</em> the number of stones that the robot will dig. In other words, this
     * algorithm must try to achieve the highest value of {@link RobotPlan#numberOfStones} possible for the
     * number of provided days. The value of {@link RobotPlan#numberOfDays} is equal to the input
     * days parameter
     * @see RobotPlan
     */

    public RobotPlan excavateStonesForDays(int days) {
        if (days <= 0) {
            // This returns an empty plan for 0 or negative days, which the robot cannot work in negative or 0
            return new RobotPlan(0, 0, List.of());
        }

        // I use half of the time for cloning "daysToClone" to increase the number of robots that collect stones.
        // Each day during this period, the number of robots doubles.
        // The other half of the time "daysToDig" is used for collecting stones.
        // Each robot can collect one stone per day.
        // In the end, I create and return a RobotPlan object which contains the total number of days,
        // the number of stones collected and a list of actions performed by the robot "cloning and digging".
        int daysToClone = days / 2;
        int daysToDig = days - daysToClone;
        int numberOfRobots = (int) Math.pow(2, daysToClone);
        int totalStones = numberOfRobots * daysToDig;

        List<RobotAction> actions = new ArrayList<>();
        for (int i = 0; i < daysToClone; i++) {
            actions.add(RobotAction.CLONE);
        }
        for (int i = 0; i < daysToDig; i++) {
            actions.add(RobotAction.DIG);
        }

        return new RobotPlan(days, totalStones, actions);
    }

    /**
     * An algorithm that converts a number of stones into an action plan. Essentially this algorithm is the inverse of
     * {@link #excavateStonesForDays(int)}.
     *
     * @param numberOfStones the number of stones the robot has to collect
     * @return The action plan <em>must minimize</em> the number of days necessary for the robot to dig the
     * provided number of stones. In other words, this algorithm must try to achieve the lowest value of
     * {@link RobotPlan#numberOfDays} possible for the number of provided stones. The value of
     * {@link RobotPlan#numberOfStones} is equal to the numberOfStones parameter
     * @see RobotPlan
     */


    // Here I calculate the minimum days needed for the robot to collect a given number of stones.
    // Iterates daily, using excavateStonesForDays, until the stone collection goal is met, then returns the plan.
    public RobotPlan daysRequiredToCollectStones(int numberOfStones) {
        int currentDays = 1;
        while (true) {
            RobotPlan plan = excavateStonesForDays(currentDays);
            if (plan.numberOfStones() >= numberOfStones) {
                // Returns the plan when have enough stones
                return plan;
            }
            // Increase the day count and try again
            currentDays++;
        }
    }
}


