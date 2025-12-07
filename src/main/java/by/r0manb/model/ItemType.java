package by.r0manb.model;

public enum ItemType implements LabeledEnum{
    BABY_FOOD("Baby Food"),
    BEVERAGES("Beverages"),
    CEREAL("Cereal"),
    CLOTHES("Clothes"),
    COSMETICS("Cosmetics"),
    FRUITS("Fruits"),
    HOUSEHOLD("Household"),
    MEAT("Meat"),
    OFFICE_SUPPLIES("Office Supplies"),
    PERSONAL_CARE("Personal Care"),
    SNACKS("Snacks"),
    VEGETABLES("Vegetables");

    private final String label;

    ItemType(String label){
        this.label = label;
    }

    public static ItemType fromLabel(String label){
        return LabeledEnum.fromLabel(ItemType.class, label);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
