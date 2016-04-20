/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import settings.NodeSetts;

/**
 *
 * @author António Ferreira, 67405; Rodrigo Cunha, 67800
 */
public class CoachRun {
    
    public static void main(String [] args) {        
        ArrayList<Coach> coachs = new ArrayList<>(NodeSetts.nCoachs);
        BenchProxy bp = new BenchProxy();
        RefereeSiteProxy rsp = new RefereeSiteProxy();
        
        for (int i = 0; i < NodeSetts.nCoachs; i++){
            coachs.add(new Coach((bench.ICoach) bp, (referee_site.ICoach) rsp, NodeSetts.teams[i]));
        }
        
        
        System.out.println("Number of coachs: " + coachs.size());
        
        for (Coach c : coachs)
            c.start();
        
        for (Coach c : coachs) { 
            try { 
                c.join ();
            } catch (InterruptedException e) {}
        }
        
        System.out.println("Sending TERMINATE message to the logging");
        
        
        /* SEND TO LOG THAT COACH HAS FINISHED */
        
    }
}