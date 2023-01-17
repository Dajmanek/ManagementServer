package pl.krakow.up.managementserver.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptionService {

    private final MessageDigest messageDigest;

    public EncryptionService() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("SHA-512");
    }

    public String encrypt(final String text) {
        final byte[] encryptedMessage = this.messageDigest.digest(text.getBytes());
        final StringBuilder hexString = new StringBuilder();
        for (byte b : encryptedMessage) {
            final String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean compare(final String text, final String hash) {
        return this.encrypt(text).equals(hash);
    }

}
