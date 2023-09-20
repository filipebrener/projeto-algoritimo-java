package domain.utils;

public enum EnumUtils {

    PASTA_RAIZ("Algoritmos/");

    private final String value;

    EnumUtils(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
