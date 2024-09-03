package org.dmle.userfood.domain;

public class RateReport extends Rate {
    private Integer count;

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

    public String getId() {
        return super.getId();
    }

    public String getName() {
        return super.getName();
    }

    public Integer getValue() {
        return super.getValue();
    }

    public String colorHex() {
        return super.getColorHex();
    }

    public Long createdAt() {
        return super.getCreatedAt();
    }

    @Override
    public String toString() {
        return "RateReport{" +
                "count=" + count +
                "} " + super.toString();
    }
}
