package jx.pgz.enums;

public enum RoleTypeEnum {
    XS("信审人员"),
    CW("财务人员"),
    KF("客服人员"),
    GL("管理人员"),
    PT("普通用户");

    private final String type;

    RoleTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
