
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jim
 */
public class burner_thread implements Runnable{
    
    Thread t;
    String comm[];
    File pp;
    BufferedReader in,er;
     Process p;
    boolean finished = false;
    
    public burner_thread(String[] s, String ppath){
        t = new Thread(this, "thead");
        comm=s;
        pp = new File(ppath);
        t.start();
    
    
}
    

    @Override
    public void run() {   
        try {
            
            burner.disableBurnButton();
            
            p = Runtime.getRuntime().exec(comm,null,pp);
            in = new BufferedReader(  
                                new InputStreamReader(p.getInputStream()));  
            
            
            
            String line = null;  
            while ((line = in.readLine()) != null) {  
                //System.out.println(line); 
                burner.showoutline(line);
            }
            er = new BufferedReader(  
                                new InputStreamReader(p.getErrorStream()));
             
            line = null;  
            while ((line = er.readLine()) != null) {  
                //System.out.println(line); 
                burner.showerrline(line);
            }
            
            finished = true;
            
            burner.makeburNull();
            burner.enableBurnButton();
            
            
        } catch (IOException ex) {
            Logger.getLogger(burner_thread.class.getName()).log(Level.SEVERE, null, ex);
            burner.makeburNull();
            burner.enableBurnButton();
        }
        
    }
    
    public void interrupt() {
        if(!finished){
            p.destroy();
            burner.makeburNull();
        }
        
    }
    
    public boolean getFinished(){
        return finished;
    }
    
    public BufferedReader get_out(){
        return in;
        
    }
    
    public BufferedReader get_err(){
        return er;
        
    }
    
}
