/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Zak
 */
public class CLI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Parser p = new Parser() ;
        Terminal t = new Terminal();
        String CMD ;
        System.out.println("Enter the command: ");
        Scanner keyboard = new Scanner(System.in) ;
        CMD = keyboard.nextLine();
        String[] Input = CMD.split(" ") ;
        boolean flag = false  ;   
        if ( Input.length > 1 )
        {
            flag =  p.check_pipe(CMD);
        }
        
         if ( flag == false ) // Not pipe
         {
             flag = p.parse(CMD);
         }
         
        if ( flag == true )
        {
           if("cp".equals(p.cmd))
           {                
              t.cp(p.args[0], p.args[1]);              
           }
           else if ( "cat".equals(p.cmd)  )
           {
                if( ">".equals(p.dir1))
               {
                   //System.out.println(p.dir1);
                   p.dir1();
               }
               else if (">>".equals(p.dir2) )
               {
                   p.dir2() ;
               }
               else
               {
                   t.cat(p.args);
               }
               
           }
           else if ( "clear".equals(p.cmd))
           {
               t.clear();
           }
           else if ( "args".equals(p.cmd))
           {
               t.args();
           }
           else if ( "cd".equals(p.cmd))
           {
               t.cd(p.args[0]);
           }
           else if ( "date".equals(p.cmd))
           {
               if( ">".equals(p.dir1))
               {
                   //System.out.println("hala");
                   p.dir1();
               }
               else if (">>".equals(p.dir2) )
               {
                   p.dir2();
               }
               else
               {
                   t.date();
               }
           }
           else if ( "help".equals(p.cmd))
           {
               t.help();
           }
           else if ( "ls".equals(p.cmd))
           {
               if ( ">".equals(p.dir1))
               {
                   p.dir1();
               }
               else if (">>".equals(p.dir2) )
               {
                   p.dir2();
               }
               else
               {
                   t.ls();
               }
           }
           
            else if ( "mkdir".equals(p.cmd)  )
           {
               t.mkdir(p.args[0]);
           }
           
             else if ( "more".equals(p.cmd)  )
           {
               t.more(p.args[0]);
           }
           
             else if("mv".equals(p.cmd))
           {                
              t.mv(p.args[0], p.args[1]);              
           }
             
           else if ( "pwd".equals(p.cmd))
           {
               t.pwd();
           }
           else if ( "rm".equals(p.cmd))
           {
               t.rm(p.args[0]);
           }
           else if ( "rmdir".equals(p.cmd))
           {
               t.rmdir(p.args[0]);
           }
           else
           {
              System.out.println("please Enter the right command"); 
           }
        }
        
   }
    
}
