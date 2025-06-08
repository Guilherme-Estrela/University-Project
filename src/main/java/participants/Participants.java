package participants;

import com.google.common.base.Preconditions;
import events.*;

import java.util.HashMap;
import java.util.Vector;

public abstract class Participants implements IParticipants {
    protected String name;
    protected String cpf;
    protected int id;
    protected static int countId = 0;

    public static HashMap<Integer ,IParticipants> participants = new HashMap<>();

    public Participants(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    @Override
    public void register() {
        this.id = countId;
        countId++;
        participants.put(this.id ,this);
    }

    @Override
    public void completeEvent(IEvents event) {
        try {
            Preconditions.checkNotNull(event, "This event does not exist or are dot able.");

            Preconditions.checkArgument(event.getStarted(),
                    "To complete the event, you need to wait for it to start.");
            event.setAbleParticipant(this);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void requestCertificate(IEvents event) {

        Preconditions.checkArgument(event.getStarted(),
                "To request a certificate of the event, you need to wait for it to start.");
        if (this instanceof Teacher)
            event.issueCertificate((Teacher) this);

        if (this instanceof Student)
            event.issueCertificate((Student) this);

        if (this instanceof External)
            event.issueCertificate((External) this);
    }

    public static void list(){
        try{
            Preconditions.checkArgument(!Participants.participants.isEmpty(),
                    "No one participant was registered.");

            System.out.println("List os participants: ");

            Participants.participants.forEach((id, participant) -> {
                System.out.println("-" + participant.getName() + " -> ID: " + id);
            });

        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void report() {
        System.out.println("Students: ");
        Participants.participants.forEach((id, participant) -> {
            if (participant instanceof Student)
                System.out.println("-" + participant.getName() + " -> ID: " + id);
        });

        System.out.println("Teachers: ");
        Participants.participants.forEach((id, participant) -> {
            if (participant instanceof Teacher)
                System.out.println("-" + participant.getName() + " -> ID: " + id);
        });

        System.out.println("Externals: ");
        Participants.participants.forEach((id, participant) -> {
            if (participant instanceof External)
                System.out.println("-" + participant.getName() + " -> ID: " + id);
        });

    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getCpf() {
        return cpf;
    }


}
