package parser;

import type.AdvObject;
import type.Command;


/**
 * This class is used to return the output of the parser
 */
public class ParserOutput {
    private Command command;

    private AdvObject object;

    private AdvObject invObject;

    /**
     * Constructor
     *
     * @param command
     * @param object
     */
    public ParserOutput(Command command, AdvObject object) {
        this.command = command;
        this.object = object;
    }

    /**
     * Constructor
     *
     * @param command
     * @param object
     * @param invObejct
     */
    public ParserOutput(Command command, AdvObject object, AdvObject invObejct) {
        this.command = command;
        this.object = object;
        this.invObject = invObejct;
    }

    /**
     * Constructor
     *
     * @ return command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Constructor
     *
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Constructor
     *
     * @return object
     */
    public AdvObject getObject() {
        return object;
    }

    /**
     * Constructor
     *
     * @param object
     */
    public void setObject(AdvObject object) {
        this.object = object;
    }

    /**
     * Constructor
     *
     * @return invObject
     */
    public AdvObject getInvObject() {
        return invObject;
    }

    /**
     * Constructor
     *
     * @param invObject
     */
    public void setInvObject(AdvObject invObject) {
        this.invObject = invObject;
    }
}
