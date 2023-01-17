package pl.krakow.up.managementserver.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class EncryptionService {

    private final MessageDigest messageDigest;

    public EncryptionService() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("SHA-512");
    }

    public String encrypt(final String text) {
        final byte[] encryptedMessage = this.messageDigest.digest(text.getBytes());
        final byte[] encodedEncryption = Base64.getEncoder().encode(encryptedMessage);
        return new String(encodedEncryption);
    }

    public boolean compare(final String text, final String hash) {
        return this.encrypt(text).equals(hash);
    }

}
