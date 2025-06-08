package events;

import com.google.common.base.Preconditions;
import participants.IParticipants;
import participants.Participants;
import participants.Teacher;

public class Lecture extends Events{

    private Teacher presenter;
    private String subject;

    public Lecture(String name, String date, String location,
            String description, int capacity, boolean online)
    {
        super(name, date, location,
                description, capacity, online);
    }

    public void RegisterPresenter (IParticipants participants, String subject){
        try {
            if (this.presenter != null)
                throw new Exception("This Lecture have yet a presenter.");

            presenter = (Teacher) participants;
            this.subject = subject;

            System.out.println("Presenter registered with success!");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void start (){
        try{
            Preconditions.checkNotNull(presenter,
                    "To start a present, before you need a presenter.");
            Preconditions.checkNotNull(subject,
                    "To start a Lecture, before you need a subject.");

            System.out.println("The lecture was successfully held. The presenter is: " + presenter.getName() + " and subject: " + subject);

            started = true;

        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

    }


}
