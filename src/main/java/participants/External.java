package participants;

public class External extends Participants{
    private int externalCode;
    private static int externalCodeCount;

    public External(String name, String cpf) {
        super(name, cpf);
    }

    @Override
    public void register() {
        super.register();
        externalCode = externalCodeCount;
        externalCodeCount++;
    }

    public int getCodeExternal(){
        return externalCode;
    }
}
