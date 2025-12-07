package by.r0manb.model;

public enum ChannelStatus implements LabeledEnum {
    ONLINE("Online"),
    OFFLINE("Offline");

    private final String label;

    ChannelStatus(String label){
        this.label = label;
    }

    public static ChannelStatus fromLabel(String label){
        return LabeledEnum.fromLabel(ChannelStatus.class, label);
    }

    @Override
    public String getLabel() {
        return label;
    }
}