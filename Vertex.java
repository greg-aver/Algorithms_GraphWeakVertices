package org.gregory;

public class Vertex {
    public int Value;
    public boolean Hit;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
    }

    public int getValue() {
        return Value;
    }

    public void visit() {
        Hit = true;
    }

    public void unvisited() {
        Hit = false;
    }

    public boolean isHit() {
        return Hit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;
        return Value == vertex.Value;
    }

    @Override
    public String toString() {
        return "Vertex {" +
                "Value = " + Value +
                ", Hit = " + Hit +
                '}';
    }
}
