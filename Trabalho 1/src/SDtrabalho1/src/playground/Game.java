/*
 * Distributed Systems
 * Rafael Ferreira and Rodrigo Cunha
 */
package playground;

/**-
 *
 * @author António Ferreira, 67405; Rodrigo Cunha, 67800
 */
public class Game {
    
    private int id;
    private String winner;
    private Trial[] trials;
    private int trial_idx = 0;
    private int[] pontuation = new int[2];
    
    public Game(int id){
        this.trials = new Trial[6];
        this.id = id;
    }
    
    public void newTrial(int centre_of_the_rope){
        assert trial_idx < this.trials.length;
        
        this.trials[trial_idx] = new Trial(trial_idx++, centre_of_the_rope);
    }
    
    public void setPontuation(int pontuation, String team){
        if(team.equals("A")){
            this.pontuation[0] = pontuation;
        }else if(team.equals("B")){
            this.pontuation[1] = pontuation;
        }
    }
    
    public int getPontuation(String team){
        if(team.equals("A")){
            return this.pontuation[0];
        }else{
            return this.pontuation[1];
        }
    }
    
    public int gameNumberOfTrials(){
        return this.trial_idx;
    }
}