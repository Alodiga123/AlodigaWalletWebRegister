package com.portal.business.commons.utils;

import org.apache.commons.codec.binary.Base64;

import com.alodiga.wallet.common.utils.S3cur1ty3Cryt3r;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Level;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author kerwin
 */
public class AlodigaCryptographyUtils {

    private static final String KEY_ENCRIPT_DESENCRIPT = "1nt3r4xt3l3ph0ny";
    private static final String K2_ENCRIPT_DESENCRIPT = "DESede";
    private static final String VECTOR_ENCRIPT_DESENCRIPT = "0123456789ABCDEF";

    public static String encrypt(String texto, String secretKey) {
        String base64EncryptedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes(StandardCharsets.UTF_8);
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return base64EncryptedString;
    }

    public static String decrypt(String textoEncriptado, String secretKey) throws Exception {
        String base64EncryptedString = "";
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes(StandardCharsets.UTF_8));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, StandardCharsets.UTF_8);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return base64EncryptedString;
    }

    public static String aloDesencrypt(String input) {
        try {
            return S3cur1ty3Cryt3r.aloDesencript(input, KEY_ENCRIPT_DESENCRIPT, null, K2_ENCRIPT_DESENCRIPT, VECTOR_ENCRIPT_DESENCRIPT);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AlodigaCryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
            //TODO throw exception
            return null;
        }
    }

    public static String aloEncrpter(String clave) {
        try {
            return S3cur1ty3Cryt3r.aloEncrpter(clave, KEY_ENCRIPT_DESENCRIPT, null, K2_ENCRIPT_DESENCRIPT, VECTOR_ENCRIPT_DESENCRIPT);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AlodigaCryptographyUtils.class.getName()).log(Level.SEVERE, null, ex);
            //TODO throw exception
            return null;
        }
    }

}
