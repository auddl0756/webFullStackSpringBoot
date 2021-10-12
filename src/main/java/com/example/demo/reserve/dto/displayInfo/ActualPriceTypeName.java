package com.example.demo.reserve.dto.displayInfo;

public enum ActualPriceTypeName {
    Y("청소년"),
    A("성인"),
    B("유아"),
    D("장애인"),
    E("얼리버드"),
    V("VIP석"),
    R("R석"),
    S("S석");

    private String actualTypeName;

    private ActualPriceTypeName(String name) {
        this.actualTypeName = name;
    }

    public String getActualTypeName() {
        return this.actualTypeName;
    }
}
