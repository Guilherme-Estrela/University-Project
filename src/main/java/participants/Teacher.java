package participants;

public class Teacher extends Participants{
    private int teacherCode;
    private static int teacherCodeCount = 0;


    public Teacher(String name, String cpf) {
        super(name, cpf);
    }

    @Override
    public void register() {
        super.register();
        teacherCode = teacherCodeCount;
        teacherCodeCount++;
    }

    public int getCodeTeacher(){
        return teacherCode;
    }
}
