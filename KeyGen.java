import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class KeyGen {
    public static final String HMAC_ALGO = "HmacSHA224";

    public static String bytestohex(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder(bytes.length*2);
        for(byte b: bytes){
            stringBuilder.append(String.format("%02x",b));
        }
        return stringBuilder.toString();
    }

    public byte[] generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key= new byte[16];
        random.nextBytes(key);
        return key;
    }

    public void generateHMAC(byte[] key, String move) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Mac mac = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(key, HMAC_ALGO);
        mac.init(keySpec);
        System.out.println(bytestohex(mac.doFinal(move.getBytes(StandardCharsets.UTF_8))));;
    }
}