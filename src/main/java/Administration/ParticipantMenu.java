package Administration;

import com.google.common.base.Preconditions;
import events.Events;
import events.IEvents;
import participants.*;

import java.util.Scanner;

public class ParticipantMenu {

    static Scanner scanner = new Scanner(System.in);

    public static void participantMenu() {
        int option;

        do {
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════");
            System.out.println("║\t\t\t" + "PARTICIPANT MENU");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════");
            System.out.println("║\t" + "1. List participants");
            System.out.println("║\t" + "2. Register a participant.");
            System.out.println("║\t" + "3. Complete a event.");
            System.out.println("║\t" + "4. Request certificate.");
            System.out.println("║\t" + "0. Leave");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    Participants.list();
                    break;

                case 2:
                    registerParticipant();
                    break;

                case 3:
                    completeEvent();
                    break;

                case 4:
                    requestCertificate();
                    break;

                case 0:
                    System.out
                            .println("╔══════════════════════════════════════════════════════════════════════════════");
                    System.out
                            .println("║\t\t\t" + "Leaving the participant menu");
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

    private static void registerParticipant() {

        String name;
        String cpf;
        int option = 0;
        IParticipants participant = null;

        System.out.println("To register a participant, please type the name and the cpf of the participant.");
        System.out.println("Name: ");
        name = scanner.nextLine();
        //scanner.nextLine();

        System.out.println("CPF: ");
        cpf = scanner.nextLine();
        //scanner.nextLine();

        while (option < 1 || option > 3) {
            System.out.println("What is the type of participant?");
            System.out.println("1. Student.");
            System.out.println("2. External.");
            System.out.println("3. Teacher.");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    Student s = new Student(name, cpf);
                    s.register();
                    participant = s;
                    break;

                case 2:
                    External e = new External(name, cpf);
                    e.register();
                    participant = e;
                    break;

                case 3:
                    Teacher t = new Teacher(name, cpf);
                    t.register();
                    participant = t;
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
        System.out.println("The participant " + participant.getName() + " was registered with success with the " + participant.getId() + " ID.");

    }

    private static void completeEvent() {
        try {
            int id;
            int eventCode;
            IParticipants participant;
            IEvents event;

            System.out.println("To complete a event you need to provide the ID of the participant and the Event Code.");

            Participants.list();
            System.out.println("Participant Id:");
            id = scanner.nextInt();
            Preconditions.checkArgument(Participants.participants.containsKey(id),
                    "This Id is not valid");
            participant = Participants.participants.get(id);

            Events.list();

            System.out.println("Event Code:");
            eventCode = scanner.nextInt();
            Preconditions.checkArgument(Events.events.containsKey(eventCode),
                    "This event code is not valid");
            event = Events.events.get(eventCode);

            participant.completeEvent(event);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private static void requestCertificate(){
        try {
            int id;
            int eventCode;
            IParticipants participant;
            IEvents event;

            System.out.println("To request a certificate you need to provide the ID of the participant and the Event Code.");

            Participants.list();
            System.out.println("Participant Id:");
            id = scanner.nextInt();
            Preconditions.checkArgument(Participants.participants.containsKey(id),
                    "This Id is not valid");
            participant = Participants.participants.get(id);

            Events.list();

            System.out.println("Event Code:");
            eventCode = scanner.nextInt();
            Preconditions.checkArgument(Events.events.containsKey(eventCode),
                    "This event code is not valid");
            event = Events.events.get(eventCode);

            participant.requestCertificate(event);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
