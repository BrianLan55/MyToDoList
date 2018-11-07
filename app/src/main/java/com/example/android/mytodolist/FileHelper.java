package com.example.android.mytodolist;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by tranceaddict on 11/6/18.
 * Helps save data entered to a file
 */

public class FileHelper
{
    public static final String FILE_NAME = "listinformation.dat";

    /**
     * Method writes data from list to a file for saving
     */
    public static void writeToFile(ArrayList<String> itemList, Context context)
    {
        try
        {
            FileOutputStream writeData = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream output  = new ObjectOutputStream(writeData);

            output.writeObject(itemList);
            output.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("An error has occurred when writing: " + e);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Reads in data from a file and populates the list
     */
    public static ArrayList<String> readFromFile(Context context)
    {
        ArrayList<String> listofItems = null;
        try
        {
            FileInputStream readData = context.openFileInput(FILE_NAME);
            ObjectInputStream input  = new ObjectInputStream(readData);
            listofItems = (ArrayList<String>)input.readObject();
        }
        catch (FileNotFoundException e)
        {
            //return default arraylist
            listofItems = new ArrayList<>();
            System.err.println("An error has occurred when reading: " + e);
        }
        catch (IOException e)
        {
            System.err.println("An error has occurred when reading: " + e);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("An error has occurred when reading: " + e);
        }


        return listofItems;
    }
}
