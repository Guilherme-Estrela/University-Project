package participants;

public class External extends Participants{
    private int codeExternal;
    private static int codeExternalCount;

    public External(String name, String cpf) {
        super(name, cpf);
    }

    @Override
    public void register() {
        super.register();
        codeExternal = codeExternalCount;
        codeExternalCount++;
    }

    public int getCodeExternal(){
        return codeExternal;
    }
}
