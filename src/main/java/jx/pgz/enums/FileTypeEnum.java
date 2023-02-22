package jx.pgz.enums;

public enum FileTypeEnum {
    RUNNING("RUNNING文件"),
    POSITION("POSITION文件"),
    ;

    private final String type;

    FileTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
