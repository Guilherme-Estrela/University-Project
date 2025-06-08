package events;

import com.google.common.base.Preconditions;
import participants.External;
import participants.IParticipants;
import participants.Student;
import participants.Teacher;

import java.util.HashMap;
import java.util.Vector;

public abstract class Events implements IEvents{
    protected String name;
    protected String date;
    protected String location;
    protected String description;
    protected int capacity;
    protected boolean online;
    protected  boolean started = false;

    protected int eventCode;
    protected static int eventCodeCount;

    protected HashMap<Integer, IParticipants> participants = new HashMap<>();
    protected HashMap<Integer, IParticipants> ableParticipants = new HashMap<>();

    public static HashMap<Integer ,IEvents> events = new HashMap<>();

    public Events(String name, String date, String location,
                  String description, int capacity, boolean online)
    {
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.online = online;
    }

    @Override
    public void register() {
        this.eventCode = eventCodeCount;
        eventCodeCount++;
        events.put(this.eventCode ,this);
    }

    @Override
    public void registerParticipant(IParticipants participant){
        try {
            Preconditions.checkNotNull(participant, "This participant does not exist or are not able.");
            Preconditions.checkArgument(!started,
                    "This event has already started; there are no more spots available.");

            if (this instanceof Course)
                Preconditions.checkArgument(participant instanceof Student,
                        "Only students can participate in courses!");

            if(participants.size() == capacity)
                throw new Exception("This event is full. We have no more vacancies.");

            setParticipant(participant);
            System.out.println("Participant registered with success");

            if(this.online){
                registerParticipantOnline();
            }
            else{
                registerParticipantInPerson();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void registerParticipantOnline() {
        System.out.println("This is teh link of the event: https://" + location + "/eventCode='" + eventCode + "'");
    }

    private void registerParticipantInPerson() {
        System.out.println("This is the number of your seat:" + participants.size());
    }

    private void generateCertificate(int id){
        Preconditions.checkArgument(ableParticipants.containsKey(id),
                "This participant is not able to get the certificate" +
                        "or was not registered.");
    }

    @Override
    public void issueCertificate(Student student) {
        try {
            generateCertificate(student.getId());

            System.out.println("The student " + student.getName() + " with cpf number: " + student.getCpf() +
                    " and registration: " + student.getRegistration() + " completed the event " + name + "  and get the certificate.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void issueCertificate(Teacher teacher) {
        try {
            generateCertificate(teacher.getId());

            System.out.println("The teacher " + teacher.getName() + " with cpf number: " + teacher.getCpf() +
                    " and teacher code: " + teacher.getCodeTeacher() + " completed the event " + name + "  and get the certificate.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void issueCertificate(External external) {
        try {
            generateCertificate(external.getId());

            System.out.println("The external visitant " + external.getName() + " with cpf number: " + external.getCpf() +
                    " and external code: " + external.getCodeExternal() + " completed the event " + name + "  and get the certificate.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void list(){
        try{
            Preconditions.checkArgument(!Events.events.isEmpty(),
                    "No one event was registered.");

            System.out.println("List os events: ");

            Events.events.forEach((code, event) -> {
                System.out.println("-" + event.getName() + " -> EventCode: " + code);
            });
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void report() {
        System.out.println("Courses: ");
        Events.events.forEach((code, event) -> {
            if (event instanceof Course)
                System.out.println("-" + event.getName() + " date: " + event.getDate() + " -> EventCode: " + code);
        });

        System.out.println("Fairs: ");
        Events.events.forEach((code, event) -> {
            if (event instanceof Fair)
                System.out.println("-" + event.getName() + " date: " + event.getDate() + " -> EventCode: " + code);
        });

        System.out.println("Lectures: ");
        Events.events.forEach((code, event) -> {
            if (event instanceof Lecture)
                System.out.println("-" + event.getName() + " date: " + event.getDate() + " -> EventCode: " + code);
        });

        System.out.println("Workshops: ");
        Events.events.forEach((code, event) -> {
            if (event instanceof WorkShop)
                System.out.println("-" + event.getName() + " -> date: " + event.getDate() + " -> EventCode: " + code);
        });

    }

    public void ListParticipants(){
        try{
            Preconditions.checkArgument(!participants.isEmpty(),
                    "This event has no participants.");

            System.out.println("List of participants of this event:");
            participants.forEach((id, participant) -> {
                System.out.println("-" + participant.getName() + " -> ID: " + id);
            });
        }
        catch (Exception  e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void start() {
        started = true;
        System.out.println("The event has started!");
    }

    @Override
    public int getEventCode() {
        return eventCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setParticipant(IParticipants participant) {
        participants.put(participant.getId(), participant);
    }

    @Override
    public boolean getStarted() { return started; }

    @Override
    public void setAbleParticipant(IParticipants participant) {
        try{
            Preconditions.checkArgument(participants.containsKey(participant.getId()),
                    "This participant was not registered in this event.");
            ableParticipants.put(participant.getId(), participant);

            System.out.println("The participant complete the event with success");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public String getDate() {
        return date;
    }
}
