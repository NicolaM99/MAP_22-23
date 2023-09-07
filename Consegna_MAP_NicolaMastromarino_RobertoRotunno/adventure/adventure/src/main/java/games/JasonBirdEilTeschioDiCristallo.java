package games;

import com.mycompany.adventure.GameDescription;
import parser.ParserOutput;
import timer.TimerController;
import type.AdvObject;
import type.Command;
import type.CommandType;
import type.Room;

import javax.swing.*;
import java.io.*;

import static com.mycompany.adventure.Utils.decrypt;


/**
 * This class represents the game Jason Bird e il Teschio di Cristallo
 */
public class JasonBirdEilTeschioDiCristallo extends GameDescription {
    //Rooms
    private final Room jungle = new Room(0, "Giungla", "Ti trovi nella giungla, un luogo lussureggiante e pieno di vegetazione.");
    private final Room templeEntrance = new Room(1, "Ingresso del Tempio", "Ti trovi all'ingresso del tempio antico." +
            " Le imponenti porte " +
            "di pietra si aprono davanti a te.");
    private final Room cell = new Room(2, "Cella", "Ti trovi in una cella buia e fredda. L'aria e' umida. " +
            "C'e' un odore di muffa insopportabile, " +
            "c'e' uno scheletro che non sembra passarsela molto bene");
    private final Room secretTunnel = new Room(3, "Tunnel Segreto", "Sei nel tunnel segreto, " +
            "un passaggio buio e nascosto. " +
            "e' evidente che e' stato scavato da qualche detenuto in cerca di liberta'.");
    private final Room statueRoom = new Room(4, "Sala delle Statue", "La sala e' adornata da statue maestose che ti osservano con sguardi severi.");
    private final Room parchmentRoom = new Room(5, "Sala delle Pergamene", "Ti trovi nella sala delle pergamene, " +
            "un luogo pieno di antichi rotoli di pergamena.");
    private final Room treasureRoom = new Room(6, "Stanza del Tesoro", "Finalmente sei arrivato alla stanza del tesoro. " +
            "Un'abbagliante luce risplende al centro della stanza.");
    //timer
    TimerController controller = TimerController.getInstance();


    /**
     * This method initializes the game
     */
    @Override
    public void init() {
        //commands
        Command read = new Command(CommandType.READ, "Leggi");
        read.setAlias(new String[]{"leggi", "leggere"});
        getCommands().add(read);
        Command nord = new Command(CommandType.NORD, "Nord");
        nord.setAlias(new String[]{"n", "N", "nord", "NORD"});
        getCommands().add(nord);
        Command inventory = new Command(CommandType.INVENTORY, "Inventario");
        inventory.setAlias(new String[]{"inv", "i", "I", "inventario"});
        getCommands().add(inventory);
        Command sud = new Command(CommandType.SOUTH, "Sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD", "sud"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "Est");
        est.setAlias(new String[]{"e", "E", "Est", "EST", "est"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "Ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST", "ovest"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "Esci");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "Osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi", "osserva"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "Raccogli");
        pickup.setAlias(new String[]{"prendi", "raccogli"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "Apri");
        open.setAlias(new String[]{"apri", "aprire", "aperto"});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "Premi");
        push.setAlias(new String[]{"spingi", "attiva", "premi", "premere", "spingere", "attivare"});
        getCommands().add(push);
        Command drink = new Command(CommandType.DRINK, "Bevi");
        drink.setAlias(new String[]{"ingurgita", "ingoia", "sorseggia", "dissetati", "bevi"});
        getCommands().add(drink);
        Command use = new Command(CommandType.USE, "Usa");
        use.setAlias(new String[]{"utilizza", "impiega", "sfrutta", "usa"});
        getCommands().add(use);

        jungle.setLook("Sei nella giungla, a nord, in lontananza vedi il tempio del tesoro, " +
                "in tutte le altre direzioni c'e' solo una " +
                "fitta giungla, inoltre vicino al tempio vedi qualcosa brillare," +
                " forse e' una clessidra");
        templeEntrance.setLook("Sei finalmente nell'ingresso del tempio," +
                " a terra sembra esserci una lente speciale, a nord c'e' " +
                "la sala delle statue, a ovest ci sono le celle");
        cell.setLook("A nord della cella osservi un foro nel muro , " +
                "in fondo al foro osservi un pulsante rosso," +
                " al centro della stanza ci sono varie ossa sparpagliate.");
        secretTunnel.setLook("In questa tunnel scuro, centinaia di ragni si agitano inquieti. " +
                "Il tunnel e' stretto e sinuoso, " +
                "sembra che il detenuto non ha ancora terminato di scavare. " +
                "Nel buio avvolgente, alla fine del tunnel, si intravede un bagliore, e' una chiave!");
        statueRoom.setLook("A sud c'e' l'ingresso, a nord invece c'e' un grande portone." +
                " Al centro della stanza osservi un altare, " +
                "su di esso vedi un ampolla con un liquido dorato al suo interno, sara' scaduto?");
        parchmentRoom.setLook("A sud c'e' la sala delle statue," +
                " a nord e a est e a ovest i muri sono pieni di pergamene accatastate," +
                " a ovest ti accorgi della presenza di un meccanismo e in basso di un foro. " +
                "Al centro della stanza un blocco di pietra" +
                " sostiene una pergamena che sembra essere molto importante, " +
                "leggerla puo' aiutarci?");
        treasureRoom.setLook("A est c'e' la sala delle pergamene, " +
                "al centro della stanza c'e' il teschio di cristallo finalmente, " +
                "ora non resta che prenderlo e uscire dal tempio");

        jungle.setNorth(templeEntrance);
        jungle.setSouth(null);
        jungle.setEast(null);
        jungle.setWest(null);

        getRooms().add(jungle);

        templeEntrance.setSouth(jungle);
        templeEntrance.setEast(null);
        templeEntrance.setNorth(statueRoom);
        templeEntrance.setWest(cell);

        getRooms().add(templeEntrance);

        cell.setWest(null);
        //all'inizio il nord e' null, ma successivamente verra' settato a secretTunnel
        cell.setNorth(null);
        cell.setEast(templeEntrance);
        cell.setSouth(null);

        getRooms().add(cell);

        secretTunnel.setSouth(cell);
        secretTunnel.setNorth(null);
        secretTunnel.setEast(null);
        secretTunnel.setWest(null);

        getRooms().add(secretTunnel);

        statueRoom.setSouth(templeEntrance);
        statueRoom.setNorth(null);
        statueRoom.setEast(null);
        statueRoom.setWest(null);

        getRooms().add(statueRoom);

        parchmentRoom.setSouth(statueRoom);
        parchmentRoom.setNorth(null);
        parchmentRoom.setEast(treasureRoom);
        parchmentRoom.setWest(null);

        getRooms().add(parchmentRoom);

        treasureRoom.setSouth(null);
        treasureRoom.setNorth(null);
        treasureRoom.setEast(null);
        treasureRoom.setWest(parchmentRoom);


        getRooms().add(treasureRoom);

        // Aggiunge oggetti alla stanza "Giungla" (jungle)
        AdvObject hourglass = new AdvObject(1, "Clessidra", "Una clessidra che sembra essere stata dimenticata qui.");
        jungle.getObjects().add(hourglass);

        // Aggiunge oggetti alla stanza "Temple Entrance" (templeEntrance)
        AdvObject ancientLens = new AdvObject(2, "Lente dell'Antico Sole", "Una lente che apparteneva all'antica civilta'.");
        templeEntrance.getObjects().add(ancientLens);

        // Aggiunge oggetti alla stanza "Cell" (cell)
        AdvObject humanBones = new AdvObject(3, "Ossa Umane", "Una collezione di ossa umane, resti di un prigioniero dimenticato.");
        cell.getObjects().add(humanBones);

        AdvObject button = new AdvObject(4, "Pulsante", "Un misterioso pulsante dallo scopo sconosciuto.");
        cell.getObjects().add(button);

        // Aggiunge oggetti alla stanza "Secret Tunnel" (secretTunnel)
        AdvObject key = new AdvObject(5, "Chiave", "Una chiave arrugginita che sembra aprire qualcosa di importante.");
        secretTunnel.getObjects().add(key);

        // Aggiunge oggetti alla stanza "Statue Room" (statueRoom)
        AdvObject cursePotion = new AdvObject(6, "Pozione Antimaledizione", "Una pozione in grado di spezzare antiche maledizioni.");
        statueRoom.getObjects().add(cursePotion);

        AdvObject ancientGate = new AdvObject(7, "Portone Antico", "Un massiccio portone che conduce alla stanza delle pergamene.");
        statueRoom.getObjects().add(ancientGate);

        // Aggiunge oggetti alla stanza "Sala delle Pergamene" (parchmentRoom)
        AdvObject scroll = new AdvObject(8, "Pergamena", "Una pergamena antica con iscrizioni misteriose.");
        parchmentRoom.getObjects().add(scroll);

        // Aggiunge oggetti alla stanza "Stanza del Tesoro" (treasureRoom)
        AdvObject crystalSkull = new AdvObject(9, "Teschio di Cristallo", "Un leggendario teschio di cristallo, si dice che possieda un grande potere.");
        treasureRoom.getObjects().add(crystalSkull);

        // Aggiunge il meccanismo come oggetto nella stanza delle pergamene
        AdvObject mechanism = new AdvObject(10, "meccanismo", "Un misterioso meccanismo di pietra inciso con simboli strani.");
        parchmentRoom.getObjects().add(mechanism);

        // Oggetto: Clessidra
        hourglass.setAlias(new String[]{"clessidra", "vecchia clessidra", "clessidra magica"});
        // Oggetto: Lente dell'Antico Sole
        ancientLens.setAlias(new String[]{"lente", "lente dell'Antico Sole", "lente antica", "lente magica"});
        // Oggetto: Ossa Umane
        humanBones.setAlias(new String[]{"ossa", "ossa umana", "ossa umane", "ossa di prigioniero"});
        // Oggetto: Pulsante
        button.setAlias(new String[]{"pulsante", "misterioso pulsante", "pulsante misterioso"});
        // Oggetto: Chiave
        key.setAlias(new String[]{"chiave", "chiave arrugginita", "chiave misteriosa"});
        // Oggetto: Pozione Antimaledizione
        cursePotion.setAlias(new String[]{"pozione", "pozione antimaledizione", "pozione magica"});
        // Oggetto: Portone Antico
        ancientGate.setAlias(new String[]{"portone", "portone antico", "portone magico","porta"});
        // Oggetto: Pergamena
        scroll.setAlias(new String[]{"pergamena", "pergamena antica", "pergamena misteriosa"});
        // Oggetto: Teschio di Cristallo
        crystalSkull.setAlias(new String[]{"teschio di cristallo", "teschio", "teschio magico"});
        // Oggetto: Meccanismo
        mechanism.setAlias(new String[]{"ingranaggio", "meccanismo", "meccanismo misterioso", "meccanismo magico"});

        //settiamo l'attributo openable degli oggetti
        hourglass.setOpenable(false);
        ancientLens.setOpenable(false);
        humanBones.setOpenable(false);
        button.setOpenable(false);
        key.setOpenable(false);
        cursePotion.setOpenable(false);
        ancientGate.setOpenable(true);
        scroll.setOpenable(false);
        crystalSkull.setOpenable(false);
        mechanism.setOpenable(false);

        //settiamo l'attributo pickupable degli oggetti
        hourglass.setPickupable(true);
        ancientLens.setPickupable(true);
        humanBones.setPickupable(true);
        button.setPickupable(false);
        key.setPickupable(true);
        cursePotion.setPickupable(true);
        ancientGate.setPickupable(false);
        scroll.setPickupable(true);
        crystalSkull.setPickupable(true);
        mechanism.setPickupable(false);

        //settiamo l'attributo pushable degli oggetti
        hourglass.setPushable(false);
        ancientLens.setPushable(false);
        humanBones.setPushable(false);
        button.setPushable(true);
        key.setPushable(false);
        cursePotion.setPushable(false);
        ancientGate.setPushable(false);
        scroll.setPushable(false);
        crystalSkull.setPushable(false);
        mechanism.setPushable(true);

        //settiamo l'attributo drinkable degli oggetti
        hourglass.setDrinkable(false);
        ancientLens.setDrinkable(false);
        humanBones.setDrinkable(false);
        button.setDrinkable(false);
        key.setDrinkable(false);
        cursePotion.setDrinkable(true);
        ancientGate.setDrinkable(false);
        scroll.setDrinkable(false);
        crystalSkull.setDrinkable(false);
        mechanism.setDrinkable(false);

        //settiamo l'attributo usable degli oggetti
        hourglass.setUsable(true);
        ancientLens.setUsable(false);
        humanBones.setUsable(false);
        button.setUsable(false);
        key.setUsable(true);
        cursePotion.setUsable(false);
        ancientGate.setUsable(false);
        scroll.setUsable(false);
        crystalSkull.setUsable(false);
        mechanism.setUsable(false);

        //settiamo l'attributo readable degli oggetti
        hourglass.setReadable(false);
        ancientLens.setReadable(false);
        humanBones.setReadable(false);
        button.setReadable(false);
        key.setReadable(false);
        cursePotion.setReadable(false);
        ancientGate.setReadable(false);
        scroll.setReadable(true);
        crystalSkull.setReadable(false);
        mechanism.setReadable(false);

        setCurrentRoom(jungle);
    }

    /**
     * This method is called when the user types a command
     *
     * @param p   ParserOutput
     * @param out PrintStream
     */
    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        boolean noroom = false;
        if (p.getCommand().getType() == CommandType.NORD) {
            if (getCurrentRoom().getNorth() != null) {
                setCurrentRoom(getCurrentRoom().getNorth());
                //1 sta per ingresso del tempio
                if (getCurrentRoom().getId() == 1) {
                    getCurrentRoom().getSouth().setNorth(null);
                    getCurrentRoom().setSouth(null);
                    out.println("Dietro di te si abbassa un muro di pietra, e' partita la maledizione, " +
                            "ora hai 20 minuti per recuperare il tesoro e uscire dal tempio");
                    //facciamo partire il timer di 20 minuti
                    controller.startGame();
                }
            } else {
                noroom = true;
            }
        } else if (p.getCommand().getType() == CommandType.SOUTH) {
            if (getCurrentRoom().getSouth().getId() == 0) {
                setCurrentRoom(getCurrentRoom().getSouth());
                out.println("Hai trovato il tesoro e sei riuscito a scappare dal tempio! Hai vinto!");
                new Timer(500, e -> TimerController.getInstance().quitGame()).start();

            } else if (getCurrentRoom().getSouth() != null) {
                setCurrentRoom(getCurrentRoom().getSouth());
            }
            //se il giocatore prova ad andare a sud dal ingresso del tempio dopo che ha preso il teschio

            else {
                noroom = true;
            }
        } else if (p.getCommand().getType() == CommandType.EAST) {
            if (getCurrentRoom().getEast() != null) {
                setCurrentRoom(getCurrentRoom().getEast());
            } else {
                noroom = true;
            }
        } else if (p.getCommand().getType() == CommandType.WEST) {
            if (getCurrentRoom().getWest() != null) {
                setCurrentRoom(getCurrentRoom().getWest());
                //move = true;
            } else {
                noroom = true;
            }
        } else if (p.getCommand().getType() == CommandType.INVENTORY) {
            out.println("Nel tuo inventario hai:");
            for (AdvObject o : getInventory()) {
                out.println(o.getName() + ": " + o.getDescription());
            }
        } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
            if (getCurrentRoom().getLook() != null) {
                out.println(getCurrentRoom().getLook());
            } else {
                out.println("Non c'e' niente di interessante qui.");
            }
        } else if (p.getCommand().getType() == CommandType.PICK_UP) {
            if (p.getObject() != null) {
                if (p.getObject().isPickupable()) {
                    getInventory().add(p.getObject());
                    getCurrentRoom().getObjects().remove(p.getObject());
                    out.println("Hai raccolto: " + p.getObject().getDescription());
                    // se l0ggetto raccolto e' il teschio di cristallo
                    if (p.getObject().getId() == 9) {
                        out.println("ingresso del tempio si e' aperto, corri il tempo scorre!");
                        templeEntrance.setSouth(jungle);
                    }
                } else {
                    out.println("Non puoi raccogliere questo oggetto.");
                }
            } else {
                out.println("Non c'e' niente da raccogliere qui.");
            }

        } else if (p.getCommand().getType() == CommandType.PUSH) {
            if (p.getObject() != null) {
                if (p.getObject().isPushable()) {
                    //il gioco termina perché viene premuto il pulsante senza nessun oggetto
                    if (p.getObject().getId() == 4 && p.getInvObject() == null) {
                        out.append("Hai premuto il pulsante e improvvisamente una lama affilata ti taglia la mano... " +
                                "Hai subito una grave ferita! Sei morto!");
                        new Timer(1000, e -> TimerController.getInstance().quitGame()).start();
                    } //4 sta per il pulsante e 3 sta per le ossa
                    else if (p.getObject().getId() == 4 && p.getInvObject().getId() == 3) {
                        out.println("Hai premuto il pulsante e improvvisamente una lama affilata cade dall'alto... " +
                                "Per fortuna non ci hai infilato la mano!");
                        cell.setNorth(secretTunnel);
                        out.println("I mattoni si sono mossi, un varco si e' aperto a nord della stanza ed ora puoi passare!");
                    }
                    //4 sta per pulsante e il diverso da 3 rappresenta qualsiasi oggetto che non sia l'osso
                    else if (p.getObject().getId() == 4 && (p.getInvObject().getId() != 3)) {
                        out.println("Non succede nulla...Sembra che il pulsante richieda qualcos'altro...");
                    } else if ((p.getObject().getId() == 10) && (p.getInvObject().getId() == 2)) {
                        out.println("Hai inserito la lente nel meccanismo, " +
                                "senti un boato assordante che riempie la stanza." +
                                "Improvvisamente, l'intera struttura inizia a tremare e si apre un varco!");
                        // la stanza del tesoro e' a est della sala delle pergamene
                        parchmentRoom.setEast(treasureRoom);
                    } else if ((p.getObject().getId() != 10) && (p.getInvObject().getId() == 2)) {
                        out.println("Questa cosa non si puo' inserire nel meccanismo");
                    } else if ((p.getObject().getId() == 10) && (p.getInvObject().getId() != 2)) {
                        out.println("Non puoi usare questo oggetto qui...");
                    } else {
                        out.println("Non succede nulla...");
                    }
                }
            } else {
                out.println("Non c'e' niente da premere qui.");
            }

        } else if (p.getCommand().getType() == CommandType.OPEN) {
            if (!p.getObject().isOpenable()) {
                out.println("Non tutto cio' che hai attorno si puo' aprire");
            }
            //7 e' il portone e 5 e' la chiave
            else if (p.getObject().getId() == 7 && p.getInvObject().getId() == 5) {
                statueRoom.setNorth(parchmentRoom);
                out.println("Hai inserito la chiave nel massiccio portone che si trova a nord," +
                        " spingi le porte " +
                        "e osservi una stanza piena di pergamene");
                p.getObject().setOpen(true);
            } else if (p.getObject().getId() == 5 && p.getInvObject().getId() != 7) {
                out.println("Non puoi usare questa chiave qui...forse e' per un portone?");
            } else {
                out.println("Non c'e' niente da aprire qui...");
            }
        } else if (p.getCommand().getType() == CommandType.DRINK) {
            if (p.getInvObject() != null && p.getInvObject().isDrinkable()) {
                controller.addMinutes(5); // Add 5 minutes to the timer
                out.println("Che schifo! La pozione era imbevibile," +
                        " ma quel poco che ho bevuto mi ha donato 5 minuti in piu' di tempo.");
                //rimozione della clessidra dall'inventario
                getInventory().remove(p.getInvObject());
            } else {
                out.println("Non c'e' niente da bere qui...");
            }

        } else if (p.getCommand().getType() == CommandType.USE) {

            //1 sta per clessidra
            if (p.getInvObject() != null && p.getInvObject().isUsable() && (p.getInvObject().getId() == 1)) {
                // se sei nella giungla
                if(getCurrentRoom().getId() == 0){
                    out.println("La clessidra non indica nulla qui, forse dovresti andare al tempio...");
                }
                else {
                    controller.printRemainingTime();
                }
            } else if (!p.getObject().isUsable()) {
                out.println("Non tutto cio' che hai attorno si puo' usare..");
            }
        } else if (p.getCommand().getType() == CommandType.READ) {
            // se la pergamena e' stata letta senza lente scrivere if
            if ((p.getObject().getId() == 8) && ((p.getInvObject() == null || p.getInvObject().getId() != 2))) {
                try {
                    File file = new File(".//resources//pergamena");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String st;
                    out.println("Non riesci a leggere il testo, forse hai bisogno di qualcosa per decifrarlo...");
                    while ((st = br.readLine()) != null) {
                        out.println(st);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (p.getObject().getId() == 8 && p.getInvObject().getId() == 2) {
                // usa decrypt per decriptare il testo
                try {
                    File file = new File(".//resources//pergamena");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String st;
                    out.println("La lente dell'Antico Sole ti permette di leggere il testo criptato:");
                    while ((st = br.readLine()) != null) {
                        out.println(decrypt(st));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            out.println("Non c'e' niente da leggere qui...");
        }
        if (noroom) {
            System.out.println("Da quella parte non si puo' andare c'e' un muro! Non hai ancora acquisito i poteri per oltrepassare i muri...");
        }


    }


}
