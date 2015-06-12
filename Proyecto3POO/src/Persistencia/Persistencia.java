/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author will
 */
public class Persistencia 
{
    private static final String fileName = "persistencia.json";
    private FileOutputStream fileOut;
    private FileInputStream fileIn;
    private static Persistencia _instance;
    JsonWriter writer;
    JsonReader reader;
    
    private Persistencia()
    {
        try
        {
            
            
            
            fileIn = new FileInputStream(fileName);
            
            reader = new JsonReader(fileIn);    
            System.out.println("inicio");
        }
        catch(Exception ex)
        {
            
        }
    }
    
    public static Persistencia getInstance() 
    {
        if(_instance == null)
        {
            _instance = new Persistencia();
        }
        return _instance;
    }
    
    public void guardarObjecto(Object pObjeto)
    {
        try
        {
            fileOut = new FileOutputStream(fileName);
        }
        catch(FileNotFoundException ex){}
        writer = new JsonWriter(fileOut);
        writer.write(pObjeto);
        writer.close();
    }
    
    public <T> T restaurarObjecto(Class<? extends T> pTipo)
    {
        
        
        T res = (T)reader.readObject();
        System.out.println("---------------------------------");
        System.out.println(res);
        return res;
    }
    
}
