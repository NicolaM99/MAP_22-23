/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.adventure;

import games.JasonBirdEilTeschioDiCristallo;

import java.util.Scanner;

import parser.Parser;
import parser.ParserOutput;
import type.CommandType;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author Nicola
 * @author Roberto
 */
public class Engine {
    private final GameDescription game;

    private Parser parser;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File("./adventure/resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void run() {
        System.out.println("La leggenda narra che alla soglia di un tempio antico e leggendario, nascosto tra le fitte foreste di una terra dimenticata, si nasconda un tesoro dal valore insetimabile, il Teschio di Cristallo, un artefatto antico e misterioso, custode di poteri inimmaginabili.\n" +
                "\n" +
                "Sfuggendo alle insidie della giungla esterna, dopo giorni estenuanti di ricerche, hai finalmente trovato l'ingresso del tempio.\n" +
                "\n" +
                "Cosa devo fare?");
        System.out.println(game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                System.out.println("Addio!");
                break;
            } else {
                game.nextMove(p, System.out);
                System.out.println("================================================");
                switch (p.getCommand().getType()) {
                    case NORD, WEST, EAST, SOUTH -> {
                        System.out.println(game.getCurrentRoom().getName());
                        System.out.println("================================================");
                        System.out.println(game.getCurrentRoom().getDescription());
                    }
                    default -> {
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        Engine engine = new Engine(new JasonBirdEilTeschioDiCristallo());
        engine.run();
    }
}
