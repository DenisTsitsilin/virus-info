package ru.dlts.virusinfo.entity;

import lombok.Data;

@Data
public class MainInfo {
    String date;
    String infected;
    String infectedPlus;
    String healed;
    String healedPlus;
    String die;
    String diePlus;
    String testsCount;
    String testsPlus;
}
