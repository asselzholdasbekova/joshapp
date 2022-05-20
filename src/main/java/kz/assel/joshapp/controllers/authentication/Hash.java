package kz.assel.joshapp.controllers.authentication;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

public class Hash implements Encryption {
    private String message;

    public Hash(String message){
        this.message = message;
    }

    @Override
    public String getEncryption() {
        String res = MD5.getHash(message);

        return res;
    }
}
