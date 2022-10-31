package com.globits.da.dto.search;


import java.util.UUID;

public class EmployeeSearchDto {
    private UUID uuid;
    private  String name;

    public EmployeeSearchDto(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
