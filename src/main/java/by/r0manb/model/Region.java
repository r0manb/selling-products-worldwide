package by.r0manb.model;

public enum Region implements LabeledEnum{
    ASIA("Asia"),
    EUROPE("Europe"),
    AUSTRALIA_N_OCEANIA("Australia and Oceania"),
    CENTRAL_AMERICA_N_CARIBBEAN("Central America and the Caribbean"),
    MIDDLE_EAST_N_NORTH_AFRICA("Middle East and North Africa"),
    NORTH_AMERICA("North America"),
    SUBSAHARAN_AFRICA("Sub-Saharan Africa");

    private final String label;

    Region(String label){
        this.label = label;
    }

    public static Region fromLabel(String label){
        return LabeledEnum.fromLabel(Region.class, label);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
