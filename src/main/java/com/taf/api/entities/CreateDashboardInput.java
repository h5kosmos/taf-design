package com.taf.api.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateDashboardInput {
    private String description;
    private String name;
    private Boolean share;
}
