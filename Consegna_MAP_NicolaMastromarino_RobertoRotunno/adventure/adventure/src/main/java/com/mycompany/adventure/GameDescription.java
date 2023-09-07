/*
 * @author Roberto
 * @author Nicola
 */
package com.mycompany.adventure;

import parser.ParserOutput;
import type.AdvObject;
import type.Command;
import type.Room;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is the abstract class that represents the game description.
 * It contains the list of rooms, commands and inventory.
 * It also contains the current room.
 * It has the abstract methods init and nextMove.
 */
public abstract class GameDescription {
    private final List<Room> rooms = new ArrayList<>();

    private final List<Command> commands = new ArrayList<>();

    private final List<AdvObject> inventory = new ArrayList<>();

    private Room currentRoom;

    /**
     * This method returns the list of rooms.
     *
     * @return the list of rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * This method returns the list of commands.
     *
     * @return the list of commands
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * This method returns the current room.
     *
     * @return the current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * This method sets the current room.
     *
     * @param currentRoom the current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * This method returns the list of objects in the inventory.
     *
     * @return the list of objects in the inventory
     */
    public List<AdvObject> getInventory() {
        return inventory;
    }

    /**
     * This method initializes the game.
     */
    public abstract void init() throws Exception;

    /**
     * This method is called at each move.
     *
     * @param p   the parser output
     * @param out the output stream
     */
    public abstract void nextMove(ParserOutput p, PrintStream out);

    /**
     * This method adds a room to the list of rooms.
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }
}
