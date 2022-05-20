package kz.assel.joshapp.controllers.authentication;

abstract class ExtraEncryption implements Encryption{

    protected Encryption encryption;

    public ExtraEncryption(Encryption encryption){
        this.encryption = encryption;
    }

    public String getEncryption(){
        return encryption.getEncryption();
    }
}
