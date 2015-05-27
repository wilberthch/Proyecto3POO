/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author will
 */
public class Persistencia 
{
    private static final String fileName = "persistencia.json";
    private static FileOutputStream fileIn;
    FileInputStream fileOut;
    private static Persistencia _instance;
    JsonWriter writer;
    JsonReader reader;
    
    private Persistencia()
    {
        try
        {
            fileIn = new FileOutputStream(fileName);
            writer = new JsonWriter(fileIn);
            
            fileOut = new FileInputStream(fileName);
            reader = new JsonReader(fileOut);
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
        writer = new JsonWriter(fileIn);
        writer.write(pObjeto);
    }
    
    public <T> T restaurarObjecto(Class<? extends T> pTipo)
    {
        
        return (T)reader.readObject();
    }
    
}
