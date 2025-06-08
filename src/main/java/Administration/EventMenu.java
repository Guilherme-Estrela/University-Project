package Administration;

import com.google.common.base.Preconditions;
import events.*;
import participants.*;

import java.util.Scanner;

public class EventMenu {

    static Scanner scanner = new Scanner(System.in);

    public static void eventMenu() {
        int option;

        do {
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════");
            System.out.println("║\t\t\t" + "EVENT MENU");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════");
            System.out.println("║\t" + "1. List events");
            System.out.println("║\t" + "2. Register a event.");
            System.out.println("║\t" + "3. Register participant in an event.");
            System.out.println("║\t" + "4. Event report by type and date.");
            System.out.println("║\t" + "5. Register a Exhibitor in a fair.");
            System.out.println("║\t" + "6. Register a presenter in a lecture.");
            System.out.println("║\t" + "7. Start a event.");
            System.out.println("║\t" + "8. Report.");
            System.out.println("║\t" + "0. Leave");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    Events.list();
                    break;

                case 2:
                    register();
                    break;

                case 3:
                    registerParticipant();
                    break;

                case 4:
                    Events.report();
                    break;

                case 5:
                    registerExhibitors();
                    break;

                case 6:
                    registerPresenter();
                    break;

                case 7:
                    startEvent();
                    break;

                case 0:
                    System.out
                            .println("╔══════════════════════════════════════════════════════════════════════════════");
                    System.out
                            .println("║\t\t\t" + "Leaving the event menu");
                    System.out
                            .println("╠══════════════════════════════════════════════════════════════════════════════");
                    break;

                default:
                    System.out
                            .println("╔══════════════════════════════════════════════════════════════════════════════");
                    System.out.println("║\t\t\t" + "Invalid option, please try again.");

                    System.out
                            .println("╠══════════════════════════════════════════════════════════════════════════════");
            }

        } while (option != 0);
    }

    private static void register() {
        String name;
        String date;
        String location;
        String description;
        int capacity;
        boolean online;

        int option = 0;
        IEvents event = null;

        System.out.println("To register an event, please type its details.");

        System.out.println("Name: ");
        name = scanner.nextLine();

        System.out.println("Date: ");
        date = scanner.nextLine();

        System.out.println("Location: ");
        location = scanner.nextLine();

        System.out.println("Description: ");
        description = scanner.nextLine();

        System.out.println("Capacity: ");
        capacity = scanner.nextInt();

        System.out.println("Is online? (True or false): ");
        online = scanner.nextBoolean();

        while (option < 1 || option > 4) {
            System.out.println("What is the type of event?");
            System.out.println("1. Course.");
            System.out.println("2. Fair.");
            System.out.println("3. Lecture.");
            System.out.println("4. Workshop.");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    Course c = new Course(name, date, location,
                            description, capacity, online);
                    c.register();
                    event = c;
                    break;

                case 2:
                    System.out.println("What is the theme of the fair?");
                    String theme;
                    scanner.nextLine();
                    theme = scanner.nextLine();

                    Fair f = new Fair(name, date, location, description,
                            capacity, online, theme);
                    f.register();
                    event = f;
                    break;

                case 3:
                    Lecture l = new Lecture(name, date, location,
                            description, capacity, online);
                    l.register();

                    event = l;
                    break;

                case 4:
                    WorkShop w = new WorkShop(name, date, location,
                            description, capacity, online);
                    w.register();
                    event = w;
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
        System.out.println("The event " + event.getName() + " was registered with success with the " + event.getEventCode() + " Event Code.");
    }

    private static void registerParticipant() {
        try {
            int id;
            int eventCode;
            IParticipants participant;
            IEvents event;

            System.out.println("To register a participant in a event you need to provide the Event Code and the ID of the participant.");

            Events.list();
            System.out.println("Event Code:");
            eventCode = scanner.nextInt();
            Preconditions.checkArgument(Events.events.containsKey(eventCode),
                    "This event code is not valid");
            event = Events.events.get(eventCode);

            Participants.list();
            System.out.println("Participant Id:");
            id = scanner.nextInt();
            Preconditions.checkArgument(Participants.participants.containsKey(id),
                    "This Id is not valid");
            participant = Participants.participants.get(id);

            event.registerParticipant(participant);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void registerPresenter() {
        try {
            int id;
            int code;
            IEvents lecture;
            IParticipants teacher;
            String subject;

            Events.report();
            System.out.println("Which lecture do you want to register a presenter for? Please type the code");
            code = scanner.nextInt();
            lecture = Events.events.get(code);
            Preconditions.checkArgument(lecture instanceof Lecture,
                    "The event need to be a Lecture!");

            Participants.report();
            System.out.println("Who is the presenter of this lecture? Please, type the id:");
            id = scanner.nextInt();
            teacher = Participants.participants.get(id);
            Preconditions.checkArgument(teacher instanceof Teacher,
                    "The presenter need to be a Teacher!");

            System.out.println("What is the subject of the lecture?");
            scanner.nextLine();
            subject = scanner.nextLine();

            ((Lecture) lecture).RegisterPresenter(teacher, subject);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }

    private static void registerExhibitors() {
        try {
            int id;
            int eventCode;
            IParticipants participant;
            IEvents event;

            System.out.println("To register a exhibitor in a fair you need to provide the Event Code and the ID of the exhibitor.");

            Events.report();
            System.out.println("Event Code:");
            eventCode = scanner.nextInt();
            Preconditions.checkArgument(Events.events.containsKey(eventCode),
                    "This event code is not valid");
            event = Events.events.get(eventCode);
            Preconditions.checkArgument(event instanceof Fair,
                    "The event need to be a Fair!");

            Participants.list();
            System.out.println("Participant Id:");
            id = scanner.nextInt();
            Preconditions.checkArgument(Participants.participants.containsKey(id),
                    "This Id is not valid");
            participant = Participants.participants.get(id);

            ((Fair) event).registerExhibitors(participant);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private static void startEvent(){
        int code;
        IEvents event;

        System.out.println("What event do you want to start?");

        Events.list();
        System.out.println("Event Code: ");
        code = scanner.nextInt();
        Preconditions.checkArgument(Events.events.containsKey(code),
                "This event code is not valid");
        event = Events.events.get(code);

        event.start();
    }

}
