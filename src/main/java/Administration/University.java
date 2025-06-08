package Administration;

import com.google.common.base.Preconditions;
import events.Events;
import events.IEvents;
import participants.IParticipants;
import participants.Participants;

import java.util.Scanner;

public class University {

    static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        int option;

        do {
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════");
            System.out.println("║\t\t\t" + "ACADEMIC EVENT MANAGEMENT SYSTEM");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════");
            System.out.println("║\t" + "1. Participant Menu.");
            System.out.println("║\t" + "2. Event Menu.");
            System.out.println("║\t" + "0. Leave");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    ParticipantMenu.participantMenu();
                    break;

                case 2:
                    EventMenu.eventMenu();
                    break;

                case 0:
                    System.out
                            .println("╔══════════════════════════════════════════════════════════════════════════════");
                    System.out
                            .println("║\t\t\t" + "Leaving the system...");
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

}



