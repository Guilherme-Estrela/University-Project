package participants;

import events.IEvents;

public interface IParticipants {
    public void register();
    public String getName();
    public int getId();
    public String getCpf();
    public void requestCertificate(IEvents events);
    public void completeEvent(IEvents event);

}
