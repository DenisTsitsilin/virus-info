package ru.dlts.virusinfo.entity;

import lombok.Data;

@Data
public class Statistics {
    Long id;
    String location;
    String stick;
    String healed;
    String die;
}
