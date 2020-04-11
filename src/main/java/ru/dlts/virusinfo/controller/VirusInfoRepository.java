package ru.dlts.virusinfo.controller;

import org.springframework.data.repository.CrudRepository;
import ru.dlts.virusinfo.entity.Statistics;

public interface VirusInfoRepository extends CrudRepository<Statistics, Long> {
}
