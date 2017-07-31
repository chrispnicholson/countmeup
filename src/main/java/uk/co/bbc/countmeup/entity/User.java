package uk.co.bbc.countmeup.entity;

/**
 * Created by Chris on 31-Jul-17.
 */
public class User {
    private long id;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
