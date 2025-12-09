package by.r0manb.model;

import java.util.Arrays;

public interface LabeledEnum {
    String getLabel();

    static <E extends Enum<E> & LabeledEnum> E fromLabel(Class<E> enum_, String label){
        return Arrays.stream(enum_.getEnumConstants())
                .filter(e -> e.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неизвестное значение " + label));
    }
}
