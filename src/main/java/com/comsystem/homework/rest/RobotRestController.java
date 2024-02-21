package com.comsystem.homework.rest;

import com.comsystem.homework.exception.InvalidRequestException;
import com.comsystem.homework.model.RobotPlan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.comsystem.homework.robot.RobotOperations;

@RestController()
@RequestMapping("/api/v1/robot/operation")


public final class RobotRestController {
    private final RobotOperations robotOperations = new RobotOperations();

    /**
     * This method exposes the functionality of {@link RobotOperations#excavateStonesForDays(int)} via HTTP
     */
    @PostMapping("/excavation")
    public ResponseEntity<RobotPlan> excavateStones(@RequestParam Integer numberOfDays) {

        if (numberOfDays == null || numberOfDays <= 0) {
            throw new InvalidRequestException
                    ("The number of days must be greater than 0.");
        }


        try {
            RobotPlan plan = robotOperations.excavateStonesForDays(numberOfDays);
            return ResponseEntity.ok(plan);
        } catch (Exception e) {
            throw new InvalidRequestException
                    ("An error occurred while processing your request for excavation.");
        }

        //throw new ErrorResponseException(HttpStatus.I_AM_A_TEAPOT);
    }

    /**
     * This method exposes the functionality of {@link RobotOperations#daysRequiredToCollectStones(int)} via HTTP
     */
    @PostMapping("/approximation")
    public ResponseEntity<RobotPlan> approximateDays(@RequestParam Integer numberOfStones) {


        if (numberOfStones == null || numberOfStones <= 0) {
            throw new InvalidRequestException
                    ("The number of stones must be greater than 0.");
        }


        try {
            RobotPlan plan = robotOperations.daysRequiredToCollectStones(numberOfStones);
            return ResponseEntity.ok(plan);
        } catch (Exception e) {
            throw new InvalidRequestException
                    ("An error occurred while processing your request for approximation.");


            // throw new ErrorResponseException(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
