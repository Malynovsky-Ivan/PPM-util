package com.malynovsky.restapp.reporting;

public class ReportItem {

    private ItemType type;
    private Object value;

    public ReportItem(String value) {
        this.value = value;
        type = value.startsWith("http") ? ItemType.HYPERLINK : ItemType.TEXT;
    }

    public ReportItem(Integer value) {
        this.value = value;
        type = ItemType.NUMERIC;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
