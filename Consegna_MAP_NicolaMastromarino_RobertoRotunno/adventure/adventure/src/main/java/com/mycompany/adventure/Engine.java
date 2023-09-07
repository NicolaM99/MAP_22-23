/*
 * @author Nicola
 * @author Roberto
 */

package com.mycompany.adventure;


import games.JasonBirdEilTeschioDiCristallo;
import parser.Parser;
import parser.ParserOutput;
import timer.TimerController;
import type.Command;
import type.CommandType;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;


/**
 * This class is the core of the game. It contains the game logic and the game loop.
 */
public class Engine {


    private final GameDescription game;

    private Parser parser;

    /**
     * <p>
     * This constructor initializes the game
     * </p>
     * <p>
     * The game is composed by a series of rooms, each of which has a description and can contain objects.
     * </p>
     */
    public Engine(GameDescription game) {

        this.game = game;
        try {
            this.game.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File(".//resources//stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException e) {
            e.printStackTrace();// Questo stampera' lo stack trace dell'eccezione su System.err
        }

    }

    /**
     * @param args the command line arguments
     *             <p>
     *             This method starts the game
     *             </p>
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new JasonBirdEilTeschioDiCristallo());
        engine.run();
    }

    /**
     * This method runs the game loop
     */
    public void run() {
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            // se il comando non e' valido stampa un messaggio di errore
            processInput(scanner.nextLine());
        }

    }

    /**
     * @param input This method processes the input of the player
     *              <p>
     *              This method processes the input of the player and returns the output of the game
     *              </p>
     */
    public String processInput(String input) {
        ParserOutput p = parser.parse(input, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
        try {
            if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {

                System.out.println("Grazie per aver giocato! Alla prossima!");
                new Timer(1000, e -> {
                    TimerController.getInstance().quitGame();
                }).start();
            } else {
                game.nextMove(p, System.out);
                //stampa trattini
                System.out.println("================================================================================================");
                switch (p.getCommand().getType()) {
                    case NORD, WEST, EAST, SOUTH -> {
                        System.out.println(game.getCurrentRoom().getName());
                        System.out.println("================================================================================================");
                        System.out.println(game.getCurrentRoom().getDescription());
                    }

                }
            }
        } catch (NullPointerException exception) {
            System.out.println("Non ho capito cosa devo fare! Prova con un altro comando.\nRiprova!");
        }

        return "";
    }

    /**
     * @return This method returns the commands available for the player
     */
    public String getCommands() {
        return game.getCommands().stream().map(Command::getName).reduce("", (acc, command) -> acc + command + "\n");
    }


}
