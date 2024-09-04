package org.dmle.userfood.domain;

public class RateReport extends Rate {
    private Integer count;

    public RateReport() {}

    public RateReport(String id, String name, Integer value, String colorHex, Long createdAt, Integer count){
        super(id, name, value, colorHex, createdAt);
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RateReport{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", value=" + getValue() +
                ", colorHex='" + getColorHex() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", count=" + getCount() +
                '}';
    }
}
