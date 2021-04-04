package bfst21.vector.osm;


import java.io.Serializable;

public abstract class Element implements Serializable {

    private static final long serialVersionUID = -2234832342114559254L;
    private final transient long id;

    public Element(long id) {
        this.id = id;
    }

    public long getID() {
        return id;
    }
}
