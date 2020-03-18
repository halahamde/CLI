/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Zak
 */
public class Parser
{

    String[] args; // Will be filled by arguments extracted by parse method
    String  cmd; // Will be filled by the command extracted by parse method  
    int counter = 0 ;
     boolean pipe = false ;
   // String redirect ;
    String dir1 ;
    String dir2 ;
    
    // Returns true if it was able to parse user input correctly. Otherwise false 
    // In case of success, it should save the extracted command and arguments  
    // to args and cmd variables   
    // It should also print error messages in case of too few arguments for a commands 
    // eg. “cp requires 2 arguments”  
    public boolean parse(String input)
    {
        boolean valid = true ;
        int j = 0 ;
        String[] Input = input.split(" ") ;
        cmd = Input[0] ; // Frist element is the command
        args = new String[Input.length - 1 ] ; //null character
        
        //System.out.println(cmd);
        //System.out.println(Input[0]);
        //System.out.println(Input[1]);
        //System.out.println(Input[2]);
        if ( Input.length > 1 )               
        { 
           if ( ">".equals(Input[1]))
                {
                   dir1 = ">" ;
                   j++ ;
                }
           else if (">>".equals(Input[1]))
              {
                 dir2 = ">>" ;
                 j++;
              }
           else 
             {
                 j = 0 ;
             }
        }
        for ( int i = 0  ; j < args.length ; i++ , j++ )
        {
            args[i] = Input[j+1] ;
            counter++;
        }
        
       // System.out.println(args[0]);
        // check command if valid --> then check their args 
        if ("cp".equals(cmd) || "mv".equals(cmd) || "rm".equals(cmd) || "pwd".equals(cmd) || "cat".equals(cmd) )
        {
            valid = CheckArgs() ;
        }
        else if ("clear".equals(cmd) || "cd".equals(cmd) || "ls".equals(cmd) || "mkdir".equals(cmd) ||  "rmdir".equals(cmd) )
        {
            valid = CheckArgs() ;
        }
       
        else if ( "more".equals(cmd) || "args".equals(cmd) ||  "date".equals(cmd) || "help".equals(cmd) )
        {
            valid = CheckArgs() ;
        }

        else
        {
            System.out.println("Command line Not Valid");
        }
        
        return valid ;
    }
    
    public String getCmd()
    {
      return cmd ;  
    }
    public String[] getArguments()
    {
        return args ;
    }
    
    public boolean CheckArgs ()
    {
        boolean key = false ;
        if ( ("cp".equals(cmd) || "mv".equals(cmd) ) && counter == 2 )
        {
            key = true ;
        }
        else if ( ("rm".equals(cmd) || "cd".equals(cmd) || "mkdir".equals(cmd) || "rmdir".equals(cmd) || "more".equals(cmd) ) && counter == 1 )
        {
           key = true  ;
        }
        else if (( "pwd".equals(cmd) || "clear".equals(cmd) || "help".equals(cmd) || "args".equals(cmd)    ) && counter == 0 )
        {
            key = true ;
        }
        else if ( "cat".equals(cmd) && counter >= 1  )
        {
            key = true ;
        }
        else if ("date".equals(cmd) || "ls".equals(cmd) )
        {
             
            if( (null != dir1 || dir2 != null) &&  counter == 1 )
            {
                key = true ;
            }
            else if ( counter == 0  )
            {
                key = true ;
            }
            else
            {
                System.out.println("No Arguments");
            }
        }
        else
        {
            System.out.println("Arguments is False");
        }     
      return key ;          
    }
            
     public void dir1() throws IOException
     {
         if ( "date".equals(cmd) )
         {
          String fileName = args[0] ; 
          Date date = new Date();
          String textToAppend = date.toString() ; 
          BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
          writer.write(textToAppend);
          writer.close();  
         }
         else if (  "ls".equals(cmd)   )
         {
          String fileName = args[0] ; 
          String sourcePath  = System.getProperty("user.dir");
          File file = new File(sourcePath);
          String [] files = file.list();
          BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
          String x ;
          x = files[0] ;          
          writer.write(x);
          for(String s: files)
          {
           writer = new BufferedWriter( new FileWriter(fileName , true)  );  //Set true for append mode;           
           writer.write(s);
           writer.newLine();   //Add new line
           writer.close();
          }
         }
         
         else if ("cat".equals(cmd)    )
         {
            String[] paths = null ;
            paths = args ;
            //System.out.println(paths[1]);
            String fileName = args[0] ; 
            File file = new File(paths[0]) ;
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            String x ;
            
            for ( int i = 0 ; i < paths.length ; i++  )
        {
            //File file = new File(paths[i]) ;
            if ( file.isFile())
            {                
                try
                {
                    Scanner keyboard = new Scanner(file) ;
                    x = keyboard.nextLine() ;          
                    writer.write(x);
                    while(keyboard.hasNextLine())
                    {    
                         writer = new BufferedWriter( new FileWriter(fileName , true)  );  //Set true for append mode;           
                         writer.write(keyboard.nextLine());
                         writer.newLine();   //Add new line
                         writer.close();
                            
                    }
                }
                catch(Exception e )
                {
                    e.getMessage() ;
                }
            }       
         }
         }
         else 
         {
             System.out.println("Error!");
         }
     }
     
     public void dir2() throws IOException
      {
          if ( "date".equals(cmd) )
         {
           String fileName = args[0] ;
           Date date = new Date();
           String textToAppend = date.toString() ;
           BufferedWriter writer;
           writer = new BufferedWriter( new FileWriter(fileName , true)  );  //Set true for append mode;
           writer.newLine();   //Add new line
           writer.write(textToAppend);
           writer.close();
         }
         else if (  "ls".equals(cmd)   )
         {
           String fileName = args[0] ;
           String sourcePath  = System.getProperty("user.dir");
           File file = new File(sourcePath);
           String [] files = file.list();
           for(String s: files)
          { 
           BufferedWriter writer;
           writer = new BufferedWriter( new FileWriter(fileName , true)  );  //Set true for append mode;
           writer.write(s);
           writer.newLine();   //Add new line
           writer.close();
          } 
         }
         
         else if ("cat".equals(cmd)    )
         {
             String[] paths = null ;
             paths = args ;
            //System.out.println(paths[1]);
            String fileName = args[0] ;
            File file = new File(paths[0]) ;
            for ( int i = 0 ; i < paths.length ; i++  )
        {
            if ( file.isFile())
            {                
                try
                {
                    Scanner keyboard = new Scanner(file) ;
                    while(keyboard.hasNextLine())
                    {
                         BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                         writer = new BufferedWriter( new FileWriter(fileName , true)  );  //Set true for append mode;           
                         writer.write(keyboard.nextLine());
                         writer.newLine();   //Add new line
                         writer.close();
                            
                    }
                }
                catch(Exception e )
                {
                    e.getMessage() ;
                }
            }       
         }
         }
         else 
         {
             System.out.println("Error!");
         }
      } 
     
     public boolean check_pipe(String input)
     {
    	 String[] Input = input.split(" ") ;
    	 String SecondCMD = "" ;
    	 if ( "|".equals(Input[1]))
         {
    		  pipe = true ;
    		 for ( int i = 2 ; i<Input.length ; i++ )
    		 {
    			 SecondCMD += Input[i] + " " ;
    		 }
    		 //System.out.println(SecondCMD);
                 parse(SecondCMD) ;
                 //FirstCMD(input);
                    
                   		
         }
    	 else
    	 {       pipe = false;
    		 
    	 }
    	 return pipe ;
     }
     
     void FirstCMD (String input)
     {
         //System.out.println("hala");
         String[] Input = input.split(" ") ;
         if( pipe == true )
         {
             parse(Input[0]) ;
         }
     }
    
      
    
}
