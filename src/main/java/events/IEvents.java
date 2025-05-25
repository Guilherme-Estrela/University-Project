package events;

import participants.External;
import participants.IParticipants;
import participants.Student;
import participants.Teacher;

public interface IEvents {
    public void register();
    public void singUp ();
    public void issueCertificate(Student id);
    public void issueCertificate(Teacher id);
    public void issueCertificate(External id);
    public void setParticipant(IParticipants participant);
    public void setAbleParticipant(IParticipants participant);
}