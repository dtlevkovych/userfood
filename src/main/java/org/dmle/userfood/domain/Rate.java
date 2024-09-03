package org.dmle.userfood.domain;

public class Rate {
    private String id;
    private String name;
    private Integer value;
    private String colorHex;
    private Long createdAt;

    public Rate() {}
    
    public Rate(String id, String name, Integer value, String colorHex, Long createdAt) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.colorHex = colorHex;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", colorHex='" + colorHex + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
