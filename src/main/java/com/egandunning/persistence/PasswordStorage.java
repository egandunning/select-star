package com.egandunning.persistence;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordStorage {

    private static final Logger logger = LogManager.getLogger(com.egandunning.persistence.PasswordStorage.class);
    
    private SecretKey sk;
    private String filename;
    
    //TODO: constructor
    
    public static void main(String[] args) throws Exception {
        
        PasswordStorage ps = new PasswordStorage();
        ps.generateKey();
        ps.store("pass".getBytes());
    }
    
    /**
     * Store a new password
     * @param name the given name for the password
     * @param password the new password
     */
    public void createPassword(String name, byte[] password) {
        
    }
    
    /**
     * Update an existing password
     * @param name the name of the password to update
     * @param password the new password
     */
    public void updatePassword(String name, byte[] password) {
        
    }
    
    /**
     * Delete an exisiting password
     * @param name the password to delete
     */
    public void deletePassword(String name) {
        
    }
    
    /**
     * Encrypt plaintext password and write encrypted bytes to file specified
     * by filename.
     * @param password the password to write
     * @param filename the file to store the password in
     * @throws InvalidKeyException 
     */
    private void store(byte[] password) throws InvalidKeyException {
        
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            System.out.println(cipher.getAlgorithm());
            //TODO: encrypt password
            cipher.init(Cipher.ENCRYPT_MODE, sk);
            
            
            byte[] iv = cipher.getIV();
            for(byte b : iv) {
                System.out.print(b + " ");
            }
            System.out.println();
            //TODO: write encrypted password to file            
            
        } catch (NoSuchAlgorithmException e) {
            logger.catching(e);
            
        } catch (NoSuchPaddingException e) {
            logger.catching(e);

        } finally {
            
            //clear password from memory
            for(int i = 0; i < password.length; i++) {
                password[i] = 0;
            }
        }
    }
    
    private void generateKey() throws NoSuchAlgorithmException {
        
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        sk = kg.generateKey();
    }
    
}
