package kz.assel.joshapp.controllers.authentication;

public class MAC extends ExtraEncryption{
    private static final int ENC_KEY = 3;

    public MAC(Encryption encryption){
        super(encryption);
    }

    @Override
    public String getEncryption() {
        String res = encryption.getEncryption();
        for (int i = 0; i < 3; i++){
            res+= ENC_KEY;
        }

        return res;
    }
}
