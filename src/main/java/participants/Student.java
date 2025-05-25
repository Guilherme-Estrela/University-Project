package participants;

public class Student extends Participants {
    private int registration;
    private static int registrationCount = 0;


    public Student(String name, String cpf) {
        super(name, cpf);
    }

    @Override
    public void register() {
        super.register();
        registration = registrationCount;
        registrationCount++;
    }

    public int getRegistration(){
        return registration;
    }
}
