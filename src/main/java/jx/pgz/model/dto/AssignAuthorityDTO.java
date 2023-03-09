package jx.pgz.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AssignAuthorityDTO {
    private String roleId;
    private Set<String> authority;
}
