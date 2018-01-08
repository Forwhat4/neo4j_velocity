package com.example.neo4j_velocity.domain;

import java.util.Objects;

public class Link{

    private String startNode;

    private String endNode;

    public String getStartNode() {
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public String getEndNode() {
        return endNode;
    }

    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(startNode, link.startNode) &&
                Objects.equals(endNode, link.endNode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(startNode, endNode);
    }
}
