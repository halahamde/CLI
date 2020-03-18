/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

/**
 *
 * @author Zak
 */
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption ;
import java.io.*;
import java.util.ArrayList;
//import java.util.*;
import java.util.Scanner;

public class Terminal 
{
    Parser s = new Parser() ;
    
    public void cat(String[] paths)
     {
    	//FileInputStream input = null ; 
        System.out.println("The content is: ");
        for ( int i = 0 ; i < paths.length ; i++  )
        {
            File file = new File(paths[i]) ;
            if ( file.isFile())
            {                
                try
                {
                    Scanner keyboard = new Scanner(file) ;
                    while(keyboard.hasNextLine())
                    {
                         System.out.println(keyboard.nextLine());
                    }
                }
                catch(Exception e )
                {
                    e.getMessage() ;
                }
            }                    
        }    	
     }
     
    public void cd(String sourcePath)
     {
    	// File file = new File(".");

        System.out.println("Current working directory:");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new File(".").getAbsolutePath());
        String dir = sourcePath ;
        System.out.println("Changing working directory...");
        System.setProperty("user.dir", dir);

        System.out.println("Current working directory:");
        System.out.println(System.getProperty("user.dir"));
    	     	 
     } 
     
    public void ls()
     { 
        String sourcePath  = System.getProperty("user.dir");
        File file = new File(sourcePath);
        String [] files = file.list();
        System.out.println("The content is: ");
         for(String s: files)
          { 
            System.out.println(s);
          } 
     }
    public void cp(String sourcePath, String destinationPath )
    {
         try
        {
          FileInputStream source = new FileInputStream (sourcePath);            
          FileOutputStream destination= new FileOutputStream (destinationPath); 
          int content;
          while ((content = source.read()) != -1)
                  {
                      destination.write(content);
                  }
          source.close();
          destination.close();
          System.out.println("File copied successfully");
        }
        catch (IOException e)
        {
            System.out.println("Exception: "+ e.toString());
            
        }
    }
    
    public void mv(String sourcePath, String destinationPath)
    {
        File source = new File(sourcePath);
        File destination = new File(destinationPath);
        try {
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("file copy successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     public void rm(String sourcePath)
    {
       File file = new File(sourcePath); 
       // FileReader file = new FileReader(sourcePath);        
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        }  
    }
    
    public void pwd()
    {
      String currentDirectory = System.getProperty("user.dir");
      System.out.println("The current working directory is " + currentDirectory);
    }   
    public void  clear()
    {
        for (int i = 0; i < 50; ++i)
        {
            System.out.println();
        }
    }
    public void mkdir ( String sourcepath )
    {
            File file = new File(sourcepath)  ;
            if (file.mkdir())
            {
                System.out.println("Directory is created!");
            } 
            else
            {
                System.out.println("Failed to create directory!");
            }
    }
    public void date ()
    {
         Date date = new Date();
         System.out.println(date.toString());
    }
    
     public void more(String path)
    {
    	/*if(s.pipe==true)
        {
            try
            {
                Scanner scanner=new Scanner(System.in);
                Scanner scanner1=new Scanner(passed.toString());
                ArrayList<String> str=new ArrayList<String>();
                while (scanner1.hasNext())
                {
                    if(str.size()<10)
                    {
                        str.add(scanner1.nextLine());
                    }
                    else
                    {
                        for(int i=0;i<str.size();i++)
                        {
                            if(i==str.size()-1)
                                System.out.print(str.get(i));
                            else
                                System.out.println(str.get(i));
                        }
                        str.clear();
                        scanner.nextLine();
                    }
                }
                if(str.size()>0)
                {
                    for(int i=0;i<str.size();i++)
                    {
                        if(i==str.size()-1)
                            System.out.print(str.get(i));
                        else
                            System.out.println(str.get(i));
                    }
                }
                return;
            }
            catch (Exception e)
            {
                System.out.println("No such file or directory");
            }
        }*/
        /*String path;
        if(!new File(s.dir1).exists())
            path=file.getPath()+"/"+s.dir1;
        else
            path=s.dir1;
                
         */       
        try
        {
            Scanner scanner=new Scanner(System.in);
            Scanner scanner1=new Scanner(new File(path));
            ArrayList<String> str=new ArrayList<String>();
            while (scanner1.hasNext())
            {
                if(str.size()<10)
                {
                    str.add(scanner1.nextLine());
                }
                else
                {
                    for(int i=0;i<str.size();i++)
                    {
                        if(i==str.size()-1)
                            System.out.print(str.get(i));
                        else
                            System.out.println(str.get(i));
                    }
                    str.clear();
                    scanner.nextLine();
                }
            }
            if(str.size()>0)
            {
                for(int i=0;i<str.size();i++)
                {
                    if(i==str.size()-1)
                        System.out.print(str.get(i));
                    else
                        System.out.println(str.get(i));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No such file or directory");
        }
    }
    
    public void rmdir(String sourcepath)
    {
    	File file = new File(sourcepath); 
    	if ( file.length() == 0 )
    	{       
    	        if(file.delete()) 
    	        { 
    	            System.out.println("File deleted successfully"); 
    	        } 
    	        else
    	        { 
    	            System.out.println("Failed to delete the file"); 
    	        }  
    	}
    	else 
    	{
    		System.out.println("Error non empty File");
    	}
    	 	
    }
    
    public void help() throws FileNotFoundException
    {
        File file = new File("Help.txt"); 
        Scanner sc = new Scanner(file);
  
        while (sc.hasNextLine())
        {
        System.out.println(sc.nextLine());
        }
    }
     
     public void args()
    {
        String CMD ;
        System.out.println("Enter the command line: ");
        Scanner keyboard = new Scanner(System.in) ;
        CMD = keyboard.next();
        if ("cp".equals(CMD) || "mv".equals(CMD)  )
        {
            System.out.println("arg1: SourcePath , arg2: DestinationPath");
        }
        else if ( "pwd".equals(CMD) || "more".equals(CMD)   ||  "clear".equals(CMD) || "args".equals(CMD) || "date".equals(CMD) || "help".equals(CMD) || "ls".equals(CMD)  )
        {
           System.out.println("No Arguments");
        }
        else if ( "mkdir".equals(CMD) || "rm".equals(CMD) || "rmdir".equals(CMD) || "cd".equals(CMD)   )
        {
            System.out.println("arg1: SourcePath");
        }
       
        else if ( "cat".equals(CMD)   )
        {
            System.out.println("rg1:Source Path or More than one");
        }
            
        else
        {
            System.out.println("Command line Not Valid");
        }
    }
    // Add any other required command in the same structure…..
     
    /*public static String ShortPath (String path)
   {
      if(path.getName()!=path.getAbsolutePath())
      {
          return path.getAbsolutePath;
      }
      else
      { 
           return path.getName;
      }
   }*/
}
