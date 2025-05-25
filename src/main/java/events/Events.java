package events;

import com.google.common.base.Preconditions;
import participants.External;
import participants.IParticipants;
import participants.Student;
import participants.Teacher;

import java.util.HashMap;

public abstract class Events implements IEvents{
    protected String name;
    protected String date;
    protected String location;
    protected String description;
    protected int capacity;
    protected boolean online;

    protected int codeEvent;
    protected static int codeEventCount;

    protected HashMap<Integer, IParticipants> participants = new HashMap<>();
    protected HashMap<Integer, IParticipants> ableParticipants = new HashMap<>();
    protected HashMap<Integer, IParticipants> participantsWithCertificate = new HashMap<>();

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
        this.codeEvent = codeEventCount;
        codeEventCount++;
    }

    private void generateCertificate(int id){
        Preconditions.checkArgument(ableParticipants.containsKey(id),
                "This participant is not able to get the certificate" +
                        "or was not registered.");

        participantsWithCertificate.put(id, ableParticipants.get(id));
        ableParticipants.remove(id);

    }

    @Override
    public void issueCertificate(Student student) {
        try {
            generateCertificate(student.getId());

            System.out.println("The student " + student.getName() + " with cpf number: " + student.getCpf() +
                    " and registration: " + student.getRegistration() + " completed the event " + name + "  and get the certificate.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void issueCertificate(Teacher teacher) {
        try {
            generateCertificate(teacher.getId());

            System.out.println("The teacher " + teacher.getName() + " with cpf number: " + teacher.getCpf() +
                    " and teacher code: " + teacher.getCodeTeacher() + " completed the event " + name + "  and get the certificate.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void issueCertificate(External external) {
        try {
            generateCertificate(external.getId());

            System.out.println("The external visitant " + external.getName() + " with cpf number: " + external.getCpf() +
                    " and external code: " + external.getCodeExternal() + " completed the event " + name + "  and get the certificate.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printParticipants(){
        try{
            Preconditions.checkArgument(!participants.isEmpty(),
                    "This event has no participants.");

            System.out.println("participants:");
            participants.forEach((id, participant) -> {
                System.out.println(participant.getName() + " ID: " + id);
            });
        }
        catch (Exception  e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setParticipant(IParticipants participant) {
        participants.put(participant.getId(), participant);
    }

    @Override
    public void setAbleParticipant(IParticipants participant) {
        ableParticipants.put(participant.getId(), participant);
    }
}
