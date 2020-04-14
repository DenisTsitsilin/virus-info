package ru.dlts.virusinfo.entity;

import lombok.Data;

@Data
public class MainInfo {
    String date;
    Long infected;
    Long infectedPlus;
    Long healed;
    Long healedPlus;
    Long die;
    Long diePlus;
    Long active;
    String testsCount;
    String testsPlus;

    public Long getActive() {
        return infected-healed-die;
    }

    public void setActive(Long active) {
        this.active = infected-healed-die;
    }
}
