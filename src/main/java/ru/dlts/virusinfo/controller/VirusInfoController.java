package ru.dlts.virusinfo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dlts.virusinfo.entity.Statistics;
import ru.dlts.virusinfo.model.InfoChecker;

import java.util.List;

@RestController
public class VirusInfoController {
    @GetMapping(value = "/info")
    public ResponseEntity<List<Statistics>> read() {
        List<Statistics> statisticsList = new InfoChecker().getInfo();

        return statisticsList != null
                ? new ResponseEntity<>(statisticsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
