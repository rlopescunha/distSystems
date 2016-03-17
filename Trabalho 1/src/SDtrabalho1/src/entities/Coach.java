/*
 * Distributed Systems
 * Rafael Ferreira and Rodrigo Cunha
 */
package entities;

import general_info_repo.Log;

/**
 *
 * @author António Ferreira, 67405; Rodrigo Cunha, 67800
 */
public class Coach extends Thread {
    
    private CoachState state;
    
    private final int id;
    private final Log log;
    private final String team;
    private final bench.ICoach bench;
    private final referee_site.ICoach referee_site;
    
    public Coach(bench.ICoach b, referee_site.ICoach r, int id, String team, Log log){
        this.bench = b;
        this.referee_site = r;
        this.log = log;
        
        this.team = team;
        this.id = id;
        
        this.setName("Coach " + id + " of the team " + team);
        state = CoachState.WAIT_FOR_REFEREE_COMMAND;
    }
    
    /**
     * This function represents the life cycle of Coach.
     */
    
    @Override
    public void run(){
        while(!referee_site.endOfMatch()){
            switch(this.state){
                case ASSEMBLE_TEAM:
                    this.bench.waitForFollowCoachAdvice(this.team);
                    this.referee_site.informReferee(this.team);
                    this.state = CoachState.WATCH_TRIAL;
                    break;
                case WAIT_FOR_REFEREE_COMMAND:
                    this.bench.waitForCallTrial();

                    if(this.referee_site.endOfMatch()){
                        break;
                    }

                    this.bench.callContestants(this.team);
                    this.state = CoachState.ASSEMBLE_TEAM;
                    break;
                case WATCH_TRIAL:
                    this.bench.waitForAssertTrialDecision();
                    this.bench.reviewNotes(team);
                    this.state = CoachState.WAIT_FOR_REFEREE_COMMAND;
                    break;
            }
        }
    }
}
