package participants;

public class Teacher extends Participants{
    private int codeTeacher;
    private static int codeTeacherCount = 0;


    public Teacher(String name, String cpf) {
        super(name, cpf);
    }

    @Override
    public void register() {
        super.register();
        codeTeacher = codeTeacherCount;
        codeTeacherCount++;
    }

    public int getCodeTeacher(){
        return codeTeacher;
    }
}
