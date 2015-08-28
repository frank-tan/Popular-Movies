package com.franktan.popularmovies.model;

/**
 * Created by tan on 27/08/2015.
 */
public class Trailer {
    private String name;
    private String size;
    private String source;
    private String type;

    public Trailer () {}

    public Trailer (String name, String size, String source, String type) {
        this.name = name;
        this.size = size;
        this.source = source;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trailer trailer = (Trailer) o;

        if (!name.equals(trailer.name)) return false;
        if (!size.equals(trailer.size)) return false;
        if (!source.equals(trailer.source)) return false;
        return type.equals(trailer.type);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + source.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
