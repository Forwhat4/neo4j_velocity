package com.example.neo4j_velocity.domain;

import java.util.Objects;

public class PpNode {

    private Integer category;

    private String name;

    private Integer value;

    private String label;

    private Integer symbolSize;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(Integer symbolSize) {
        this.symbolSize = symbolSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PpNode ppNode = (PpNode) o;
        return Objects.equals(name, ppNode.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
