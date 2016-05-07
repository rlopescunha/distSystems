/*
 * Distributed Systems
 * Rafael Ferreira and Rodrigo Cunha
 */
package interfaces.bench;

import java.rmi.RemoteException;

/**
 * Coach interface of Bench instance.
 * @author António Ferreira, 67405; Rodrigo Cunha, 67800
 */
public interface ICoach {

    /**
     * In coach life cycle, transition between "watch trial" and "wait for referee command".
     * The coach will wait until 10 contestants are in the bench and then will continue.
     * @param team Team identifier, can be A or B.
     * @throws java.rmi.RemoteException
     */
    public void reviewNotes(String team) throws RemoteException;

    /**
     * In coach life cycle, transition between "wait for referee command" and "assemble team".
     * The coach will select randomly the contestants, this will be the strategy, select random
     * the contestants. The contestants will make one "pool" to see if they are in the array of
     * the players selected.
     * @param team Team identifier, can be A or B.
     * @throws java.rmi.RemoteException
     */
    public void callContestants(String team) throws RemoteException;
    
    /**
     * The coaches are sleeping in this method waiting that the referee inform  
     * and can select the next team.
     * @throws java.rmi.RemoteException
     */
    public void waitForCallTrial() throws RemoteException;
    
    /**
     * The coaches will wait until the referee make the decision of notify the
     * referees in the assertTrialDecision.
     * @throws java.rmi.RemoteException
     */
    public void waitForAssertTrialDecision() throws RemoteException;
    
   /**
     * The coach will wait until the last contestant stand in position to then 
     * follow the coach advice. The flags will be resetted.
     * @param team Team identifier, can be A or B.
     * @throws java.rmi.RemoteException
     */
    public void waitForFollowCoachAdvice(String team) throws RemoteException;
}