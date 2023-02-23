package jx.pgz.enums;

public enum RoleTypeEnum {
    ADMIN("管理员"),
    USER("用户");

    private final String type;

    RoleTypeEnum(String type) {
        this.type = type;
    }

}
