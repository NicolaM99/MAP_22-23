package swing;

import javax.swing.*;
import java.io.OutputStream;

/**
 * This class is used to write the output in the JTextArea
 */

public class SwingOutputStream extends OutputStream {
    private final JTextArea textArea;

    /**
     * @param textArea JTextArea
     *                 <p>
     *                 This method initializes the JTextArea
     *                 </p>
     */
    public SwingOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * @param b int
     *          <p>
     *          This method writes the output
     *          </p>
     */
    @Override
    public void write(int b) {
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
