/*
 * Distributed Systems
 * Rafael Ferreira and Rodrigo Cunha
 */
package interfaces.referee_site;

import java.rmi.RemoteException;
import structures.VectorTimestamp;

/**
 * Coach interface of Referee Site instance.
 * @author António Ferreira, 67405; Rodrigo Cunha, 67800
 */
public interface ICoach {
    
    /**
     * The referee is waken up by the last of the coaches in operation 
     * "informReferee" when the teams are ready to proceed
     * @param team "A" or "B"
     * @param vt
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorTimestamp informReferee(String team, VectorTimestamp vt) throws RemoteException;

    /**
     * End of the match.
     * @return if has endeed or not
     * @throws java.rmi.RemoteException
     */
    public boolean endOfMatch() throws RemoteException;
}
