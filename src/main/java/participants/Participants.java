package participants;

import com.google.common.base.Preconditions;
import events.IEvents;

public abstract class Participants implements IParticipants{
    protected String name;
    protected String cpf;
    protected int id;
    protected static int countId = 0;

    public Participants(String name, String cpf){
        this.name = name;
        this.cpf = cpf;
    }

    @Override
    public void register() {
        this.id = countId;
        countId++;
    }

    @Override
    public void joinAEvent(IEvents event){
        try {
            Preconditions.checkNotNull(event, "This event does not exist or are dot able.");
            event.setParticipant(this);
            System.out.println("Participant registered with success");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public void completeEvent(IEvents event) {
            try {
                Preconditions.checkNotNull(event, "This event does not exist or are dot able.");
                event.setAbleParticipant(this);
                System.out.println("Participant registered with success");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void requestCertificate(IEvents event) {
        if (this instanceof Teacher)
            event.issueCertificate((Teacher) this);

        if (this instanceof Student)
            event.issueCertificate((Student) this);

        if (this instanceof External)
            event.issueCertificate((External) this);
    }

    @Override
    public String getName(){
        return name;
    }
    @Override
    public int getId(){
        return id;
    }
    @Override
    public String getCpf(){
        return cpf;
    }



}
