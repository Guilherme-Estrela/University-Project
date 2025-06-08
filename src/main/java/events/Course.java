package events;

import com.google.common.base.Preconditions;
import participants.IParticipants;
import participants.Student;

public class Course extends Events{

    public Course(String name, String date, String location,
             String description, int capacity, boolean online)
    {
        super(name, date, location,
                description, capacity, online);
    }


}
