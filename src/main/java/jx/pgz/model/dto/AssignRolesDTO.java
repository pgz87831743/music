package jx.pgz.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AssignRolesDTO {
    private String userId;
    private Set<String> roles;
}
