package br.com.alura.aluraschool.model.enums;

public enum RoleEnum {
 ESTUDANTE("ESTUDANTE"), INSTRUTOR("INSTRUTOR"), ADMIN("ADMIN");

 private String code;

    private RoleEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RoleEnum valueOfCode(String code) {
        for (RoleEnum status : values()) {
            if (status.getCode().equalsIgnoreCase(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("The given code was not found: " + code);
    }
   
}