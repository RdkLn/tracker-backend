package dev.rdkln.tracker.weighthistory.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.rdkln.tracker.user.domain.UserId;
import dev.rdkln.tracker.weighthistory.WeightHistoryService;
import dev.rdkln.tracker.weighthistory.rest.dto.CreateWeightHistoryDTO;


@RestController
@RequestMapping("/weight-history")
public class WeightHistoryController {

    private WeightHistoryService weightHistoryService;

    public WeightHistoryController(WeightHistoryService weightHistoryService){
        this.weightHistoryService=weightHistoryService;
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void saveWeightByDate(@RequestHeader Long userId, @RequestBody CreateWeightHistoryDTO dto) {
        weightHistoryService.addWeight(dto, new UserId(userId));
    }


}
