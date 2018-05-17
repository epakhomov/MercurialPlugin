/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinsightgenericplugin;

import java.io.BufferedReader;
import java.io.*;
/**
 *
 * @author epakhomov
 */
public class MercurialShell {
    
    //private String path;
    //boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    
    //public MercurialShell(String path)
    //{
        //this.path = path;
   // }
    
    StringBuffer output = new StringBuffer();
    
   
    public String shell(String path, String repourl) {    
        
        
        Process p;
                try {
			String command = "hg clone " + repourl + " " + path;
                        p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
                                                

		} catch (Exception e) {
			e.printStackTrace();
		}

		
                return output.toString();
                
               

	}
        
        
        
 
    } 

