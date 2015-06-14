/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author will
 */
public class FileTools 
{

    private static final SecureRandom random = new SecureRandom();

    private static String getRandomName() 
    {
        
        return new BigInteger(130, random).toString(32);
    }
    
    public static String getRandomFileName(String pFile)
    {
        String res;
        String extension = FilenameUtils.getExtension(pFile);
        String randomName = FileTools.getRandomName();
            
        res = randomName + "." + extension;
        
        return res;
    }

}
