package com.chua.backendassignment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupDto {
    private Long id;
    private String name;
    private String code;
    private String description;
    private boolean active;
}
