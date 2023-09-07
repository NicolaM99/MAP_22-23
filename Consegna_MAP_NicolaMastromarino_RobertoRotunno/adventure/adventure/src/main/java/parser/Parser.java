package parser;

import com.mycompany.adventure.Utils;
import type.AdvObject;
import type.Command;

import java.util.List;
import java.util.Set;


/**
 * The Parser class is used to parse the user input and to return the corresponding command and object.
 */
public class Parser {
    private final Set<String> stopwords;

    /**
     * Constructor for the Parser class.
     *
     * @param stopwords Set of stopwords.
     */
    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    /**
     * This method checks if the token is a command.
     *
     * @param token    Token to check.
     * @param commands List of commands.
     * @return The index of the command in the list of commands, -1 if the command is not found.
     */
    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || (commands.get(i).getAlias() != null && commands.get(i).getAlias().contains(token))) {

                return i;
            }
        }
        return -1;
    }

    /**
     * This method checks if the token is an object.
     *
     * @param token   Token to check.
     * @param objects List of objects.
     * @return The index of the object in the list of objects, -1 if the object is not found.
     */
    private int checkForObject(String token, List<AdvObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getName().equals(token) || objects.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method parses the user input and returns the corresponding command and object.
     *
     * @param command   User input.
     * @param commands  List of commands.
     * @param objects   List of objects.
     * @param inventory List of objects in the inventory.
     * @return ParserOutput object containing the command and the object.
     */
    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {
        List<String> tokens = Utils.parseString(command, stopwords);
        if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands);
            if (ic > -1) {
                if (tokens.size() > 1) {
                    int io = checkForObject(tokens.get(1), objects);
                    int ioinv = -1;
                    if (io < 0 && tokens.size() > 2) {
                        io = checkForObject(tokens.get(2), objects);
                    }
                    if (io < 0) {
                        ioinv = checkForObject(tokens.get(1), inventory);
                        if (ioinv < 0 && tokens.size() > 2) {
                            ioinv = checkForObject(tokens.get(2), inventory);
                        }
                    }
                    //caso in cui il primo oggetto e' stato trovato e il secondo va cercato nell'inv
                    if (tokens.size() > 2 && io > -1) {
                        ioinv = checkForObject(tokens.get(2), inventory);
                    }
                    //sia nella lista degli oggetti che nella lista dell'inventario
                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), inventory.get(ioinv));
                    }
                    //solo lista degli oggetti, nell'inventario non e' stato trovato nulla
                    else if (io > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null);
                    }
                    //l'oggetto e' stato trovato solo nell'inventario
                    else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }
}
