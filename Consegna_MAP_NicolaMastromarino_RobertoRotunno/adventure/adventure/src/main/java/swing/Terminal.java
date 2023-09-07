package swing;

import com.mycompany.adventure.Engine;
import games.JasonBirdEilTeschioDiCristallo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;


/**
 * This class is used to create the Swing interface
 */
public class Terminal extends JFrame {
    final JTextArea outputArea;
    private final JTextField inputField;
    private final Engine gameEngine; // Riferimento alla classe Engine


    /**
     * @param gameEngine Reference to the Engine class
     */
    public Terminal(Engine gameEngine) {


        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.YELLOW);
        outputArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));


        scrollPane.setPreferredSize(new Dimension(600, 400));
        this.gameEngine = gameEngine;
        setTitle("Jason Bird e il Teschio di Cristallo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Aumentato le dimensioni per adattare meglio l'ASCII art
        setLocationRelativeTo(null);

        // Crea un pannello per la parte inferiore con layout FlowLayout
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Crea l'area di input con la dimensione desiderata
        inputField = new JTextField(100); // Imposta la dimensione in base alle tue esigenze

        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.YELLOW);
        inputField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));


        // Aggiungi l'area di input al pannello inferiore
        bottomPanel.add(inputField);

        // Aggiungi il pannello inferiore al pannello principale
        add(bottomPanel, BorderLayout.SOUTH);

        SwingOutputStream swingOutputStream = new SwingOutputStream(outputArea);
        System.setOut(new PrintStream(swingOutputStream, true));
        System.setErr(new PrintStream(swingOutputStream, true));

        inputField.addActionListener(this::processInput);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(scrollPane);
        getContentPane().add(inputField);

        printWelcomeMessage(); // Aggiunto per stampare il messaggio di benvenuto e l'ASCII art

        setVisible(true);
        inputField.requestFocus();

    }

    /**
     * @param args This method starts the game
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Engine gameEngine = new Engine(new JasonBirdEilTeschioDiCristallo()); // Inizializza l'Engine con il tuo gioco
            new Terminal(gameEngine); // Crea l'interfaccia Swing

        });
    }

    /**
     * @param e This method processes the input entered by the user
     */
    // All'interno del metodo processInput
    private void processInput(ActionEvent e) {

        String input = inputField.getText();
        outputArea.append("> " + input + "\n");

        String gameOutput = gameEngine.processInput(input);
        if (gameOutput.contains("Hai premuto il pulsante e improvvisamente una lama affilata ti taglia la mano... Hai subito una grave ferita! Sei morto!")) {
            outputArea.append(gameOutput + "\n");
            inputField.setEnabled(false);
            // Chiudi in modo appropriato il terminale, ad esempio:
            this.dispose(); // Chiude la finestra del terminale

        } else if (gameOutput.contains("Grazie per aver giocato! Alla prossima!")) {
            // chiudi l'input
            inputField.setEnabled(false);

            outputArea.append(gameOutput + "\n");
            // Chiudi in modo appropriato il terminale, ad esempio:
            this.dispose(); // Chiude la finestra del terminale
            // oppure, puoi mostrare un messaggio e disabilitare l'inputField
        } else if (gameOutput.contains("Hai trovato il tesoro e sei riuscito a scappare dal tempio! Hai vinto!")) {
            outputArea.append(gameOutput + "\n");
            // Chiudi in modo appropriato il terminale, ad esempio:
            this.dispose(); // Chiude la finestra del terminale
            // oppure, puoi mostrare un messaggio di vittoria e disabilitare l'inputField
        } else if (gameOutput.toLowerCase().contains("errore") || gameOutput.toLowerCase().contains("error")) {
            outputArea.append(gameOutput + "\n");
        } else {
            outputArea.append(gameOutput + "\n\n");
        }

        // Scorrimento automatico in basso e pulizia dell'inputField
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
        inputField.setText("");


    }

    /**
     * This method prints the welcome message and the ASCII art
     */
    private void printWelcomeMessage() {
        outputArea.append("\n\n"); // Aggiungi spazio prima del messaggio di benvenuto
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.YELLOW);


        // ASCI art  Benvenuto in Jason Bird e il Teschio di Cristallo!
        String welcomeMessage = """
                ━━━━━━━━━━┏┓━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┏━━┓━━━━━━━━┏┓━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━━━┃┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃┏┓┃━━━━━━━━┃┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━━━┃┃┏━━┓━┏━━┓┏━━┓┏━┓━━━━━━━━━━━━━━━━━━━━━┃┗┛┗┓┏┓┏━┓┏━┛┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━┏┓┃┃┗━┓┃━┃━━┫┃┏┓┃┃┏┓┓━━━━━━━━━━━━━━━━━━━━┃┏━┓┃┣┫┃┏┛┃┏┓┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━┃┗┛┃┃┗┛┗┓┣━━┃┃┗┛┃┃┃┃┃━━━━━━━━━━━━━━━━━━━━┃┗━┛┃┃┃┃┃━┃┗┛┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━┗━━┛┗━━━┛┗━━┛┗━━┛┗┛┗┛━━━━━━━━━━━━━━━━━━━━┗━━━┛┗┛┗┛━┗━━┛━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                """;


        // ASCI art Teschio di Cristallo
        String crystalSkull = """
                ━━━━━┏━━━━┓━━━━━━━━━━━━┏┓━━━━━━━━━━━━━━┏┓━━━━━━━━━━━━━━━━━━━━┏┓━━━━━━┏┓━┏┓━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━┃┏┓┏┓┃━━━━━━━━━━━━┃┃━━━━━━━━━━━━━━┃┃━━━━━━━━━━━━━━━━━━━┏┛┗┓━━━━━┃┃━┃┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━┗┛┃┃┗┛┏━━┓┏━━┓┏━━┓┃┗━┓┏┓┏━━┓━━━━┏━┛┃┏┓━━━━┏━━┓┏━┓┏┓┏━━┓┗┓┏┛┏━━┓━┃┃━┃┃━┏━━┓━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━┃┃━━┃┏┓┃┃━━┫┃┏━┛┃┏┓┃┣┫┃┏┓┃━━━━┃┏┓┃┣┫━━━━┃┏━┛┃┏┛┣┫┃━━┫━┃┃━┗━┓┃━┃┃━┃┃━┃┏┓┃━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━┏┛┗┓━┃┃━┫┣━━┃┃┗━┓┃┃┃┃┃┃┃┗┛┃━━━━┃┗┛┃┃┃━━━━┃┗━┓┃┃━┃┃┣━━┃━┃┗┓┃┗┛┗┓┃┗┓┃┗┓┃┗┛┃━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━┗━━┛━┗━━┛┗━━┛┗━━┛┗┛┗┛┗┛┗━━┛━━━━┗━━┛┗┛━━━━┗━━┛┗┛━┗┛┗━━┛━┗━┛┗━━━┛┗━┛┗━┛┗━━┛━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                """;

        String welcomeText = """
                Benvenuto in Jason Bird e il Teschio di Cristallo!
                La leggenda narra che alla soglia di un tempio antico e leggendario,
                nascosto tra le fitte foreste di una terra dimenticata, si nasconda un tesoro dal valore inestimabile,
                il Teschio di Cristallo, un artefatto antico e misterioso, custode di poteri inimmaginabili.
                Sfuggendo alle insidie della giungla esterna, dopo giorni estenuanti di ricerche,
                hai finalmente trovato l'ingresso del tempio in cima ad una montagna.""";

        String welcomeText2 = "Cosa devo fare?...vedo un' entrata...a nord...";

        // ASCII art dell'intro
        String temple = """
                \t▒▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒
                \t▒▒████▄▒▒▒▄▄▄▄▄▄▄▒▒▒▄████▒▒
                \t▒▒▒▒▒▀▀▒▄█████████▄▒▀▀▒▒▒▒▒
                \t▒▒▒▒▒▒▒█████████████▒▒▒▒▒▒▒
                \t▒▒▒▒▒▒▒██▀▀▀███▀▀▀██▒▒▒▒▒▒▒
                \t▒▒▒▒▒▒▒██▒▒▒███▒▒▒██▒▒▒▒▒▒▒
                \t▒▒▒▒▒▒▒█████▀▄▀█████▒▒▒▒▒▒▒
                \t▒▒▒▒▒▒▒▒▒▒███████▒▒▒▒▒▒▒▒▒▒
                \t▒▒▒▒▄▄▄██▒▒█▀█▀█▒▒██▄▄▄▒▒▒▒
                \t▒▒▒▒▀▀██▒▒▒▒▒▒▒▒▒▒▒██▀▀▒▒▒▒
                \t▒▒▒▒▒▒▀▀▒▒▒▒▒▒▒▒▒▒▒▀▀▒▒▒▒▒▒
                """;


        // Suddividi il testo in righe
        String[] lines = (temple + "\n" + welcomeMessage + "\n" + crystalSkull + "\n" + welcomeText + "\n" +
                rest.Weather.getWeather() + "\n" + welcomeText2 + "\n\n" +
                "Comandi disponibili:\n" + gameEngine.getCommands() + "\n").split("\n");
        // Disabilita l'input durante la stampa
        inputField.setEnabled(false);
        // Mostra le righe gradualmente
        Timer timer = new Timer(200, new ActionListener() {
            int index = 0;

            /**
             * Invoked when an action occurs.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < lines.length) {
                    outputArea.append(lines[index] + "\n");
                    outputArea.setCaretPosition(outputArea.getDocument().getLength());
                    index++;
                } else {
                    ((Timer) e.getSource()).stop();
                    inputField.setEnabled(true);
                }
            }
        });

        timer.start();
    }


}









