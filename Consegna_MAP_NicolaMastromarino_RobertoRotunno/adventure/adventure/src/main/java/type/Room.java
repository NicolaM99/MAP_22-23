package type;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int id;
    private final List<AdvObject> objects = new ArrayList<>();
    private String name;
    private String description;
    //descrizione quando viene digitato osserva
    private String look;
    private boolean visible = true;
    private Room south = null;
    private Room north = null;
    private Room east = null;
    private Room west = null;


    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Room(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */

    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */

    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the visible
     */

    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the south
     */

    public Room getSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */

    public void setSouth(Room south) {
        this.south = south;
    }

    /**
     * @return the north
     */

    public Room getNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */

    public void setNorth(Room north) {
        this.north = north;
    }

    /**
     * @return the east
     */

    public Room getEast() {
        return east;
    }

    /**
     * @param east the east to set
     */

    public void setEast(Room east) {
        this.east = east;
    }

    /**
     * @return the west
     */

    public Room getWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(Room west) {
        this.west = west;
    }

    /**
     * @return the objects
     */
    public List<AdvObject> getObjects() {
        return objects;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        return this.id == other.id;
    }

    /**
     * @return the look
     */
    public String getLook() {
        return look;
    }

    /**
     * @param look the look to set
     */
    public void setLook(String look) {
        this.look = look;
    }

    public String getDescReturn() {
        return "Sei nella stanza " + this.getName() + "\n" + this.getDescription();
    }
}
