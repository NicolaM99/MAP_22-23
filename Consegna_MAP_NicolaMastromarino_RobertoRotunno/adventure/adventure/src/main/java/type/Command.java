/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * This class represents the command
 */
public class Command {
    private final CommandType type;

    private final String name;

    private Set<String> alias;

    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @param type  Type of the command
     * @param name  Name of the command
     * @param alias Alias of the command
     */
    public Command(CommandType type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    /**
     * @return Name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * @return Alias of the command
     */
    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     * @param alias Alias of the command
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * @return Type of the command
     */
    public CommandType getType() {
        return type;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }

    /**
     * @param obj Object to compare
     * @return True if the object is equal to the command, false otherwise
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
        final Command other = (Command) obj;
        return (this.type == other.type);
    }

}
