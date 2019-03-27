package com.egandunning.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializer {
 
    /**
     * Write an object to FileOutputStream
     * @param obj the Serializable to write
     * @param fos the FileOutputStream to write to
     * @throws IOException
     * @throws NullPointerException if fos is null
     */
    public static void writeObject(Serializable obj, FileOutputStream fos) throws IOException, NullPointerException {
        
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();
    }
    
    /**
     * Writes an object to a file specified by filename
     * @param obj the Serializable to write
     * @param filename the file to write to
     * @throws IOException
     * @throws NullPointerException if filename can not be written to
     */
    public static void writeObject(Serializable obj, String filename) throws IOException, NullPointerException {
        
        FileOutputStream fos = new FileOutputStream(new File(filename));
        writeObject(obj, fos);
        fos.close();
    }
    
    /**
     * Read an object from a FileInputStream
     * @param fis the FileInputStream to read from
     * @return Serializable
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws NullPointerException
     */
    public static Serializable readObject(FileInputStream fis) throws ClassNotFoundException, IOException, NullPointerException {
        
        ObjectInputStream ois = new ObjectInputStream(fis);
        Serializable obj = (Serializable) ois.readObject();
        ois.close();
        return obj;
    }
    
    /**
     * Read an object from a file specified by filename
     * @param filename the file to read from
     * @return Serializable
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws NullPointerException if the filename can not be read from
     */
    public static Serializable readObject(String filename) throws ClassNotFoundException, IOException, NullPointerException {
        
        FileInputStream fis = new FileInputStream(new File(filename));
        Serializable obj = readObject(fis);
        fis.close();
        return obj;
    }
}
