package events;

import com.google.common.base.Preconditions;
import participants.IParticipants;

import java.util.Vector;

public class Fair extends Events {

    private Vector <IParticipants> exhibitors = new Vector<IParticipants>();

    private String theme;

    public Fair(String name, String date, String location,
             String description, int capacity, boolean online, String theme)
    {
        super(name, date, location,
                description, capacity, online);

        this.theme = theme;
    }


    public void registerExhibitors(IParticipants participant){
        try{
            Preconditions.checkArgument(participants.containsKey(participant.getId()) ,
                    "To become an exhibitor, you must first be a participant in the event." );

            exhibitors.add(participant);

            System.out.println("Exhibitors registered with success!");

        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void start() {
        try{
            Preconditions.checkArgument(!exhibitors.isEmpty(),
                    "To start the fair, you must to be any exhibitor.");
            System.out.println("The fair was successfully held. The theme is: " + theme + ".");
            started = true;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


}
