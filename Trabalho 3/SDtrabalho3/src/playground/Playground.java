/*
 * Distributed Systems
 * Rafael Ferreira and Rodrigo Cunha
 */
package playground;

import interfaces.playground.PlaygroundInterface;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.NodeSetts;

/**
 * Playground instance.
 * @author António Ferreira, 67405; Rodrigo Cunha, 67800
 */
public class Playground implements PlaygroundInterface{
    
    private static boolean trialDecisionTaken = false, startTrialTaken = false;
    private int contestantsIn = 0;
    private int contestantsAlerted = 0;
    
    private final int DELAY_MAX = NodeSetts.DELAY_MAX;
    private final int DELAY_MIN = NodeSetts.DELAY_MIN;
    
    private final interfaces.log.IPlayground log;
    
    /**
     * Log is a singleton, it needs the getInstance method.
     * @param l
     */
    public Playground(interfaces.log.IPlayground l){
        this.log = l;
    }
    
    /**
     * In Referee life cycle, transition between "teams ready" and "wait for trial conclusion".
     */
    @Override
    public synchronized void startTrial() {
        startTrialTaken = true;
        notifyAll();
    }

    /**
     * Wait for start trial. Contestant method.
     */
    @Override
    public synchronized void waitForStartTrial(){
        while(!startTrialTaken){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Playground.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(++this.contestantsIn == 6){
            this.contestantsIn = 0;
            startTrialTaken = false;
        }
    }
    
    /**
     * In Referee life cycle, transition between "wait for trial conclusion" and "wait for trial conclusion".
     */
    @Override
    public synchronized void assertTrialDecision() {
        trialDecisionTaken = true;
        notifyAll();
    }
    
    /**
     * Wait for assert trial decision. Contestant method.
     */
    @Override
    public synchronized void waitForAssertTrialDecision(){
        while(!trialDecisionTaken){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Playground.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(++this.contestantsAlerted == 6){
            trialDecisionTaken = false;
            this.contestantsAlerted = 0;
        }
    }
    
    /**
     * In Contestants life cycle, transition between "doYourBest" and "doYourBest"
     * Random time interval in the simulation 
     * @param id contestant identifier
     * @param team "A" or "B"
     */
    @Override
    public void pullTheRope(int id, String team) {
        try {
            Thread.sleep((int)Math.ceil(Math.random() * DELAY_MAX + DELAY_MIN));
        } catch (InterruptedException ex) {
            Logger.getLogger(Playground.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.updateRope(team, id);
    }

    @Override
    public void signalShutdown() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
