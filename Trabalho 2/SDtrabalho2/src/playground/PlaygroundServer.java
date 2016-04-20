/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playground;

import communication.ServerChannel;
import communication.message.Message;
import communication.message.MessageException;
import communication.message.MessageType;
import communication.proxy.ServerInterface;
import java.net.SocketException;

/**
 *
 * @author António Ferreira, 67405; Rodrigo Cunha, 67800
 */
public class PlaygroundServer extends Playground implements ServerInterface {
    
    private final boolean serverEnded;
    
    public PlaygroundServer() {
        super();
        this.serverEnded = false;
    }
    
    
    @Override
    public Message processAndReply(Message inMessage, ServerChannel scon) throws MessageException, SocketException {
        switch(inMessage.getType()){
            case startTrial:
                super.startTrial();
                break;
            case waitForStartTrial:
                super.waitForStartTrial();
                break;
            case assertTrialDecision:
                super.assertTrialDecision();
                break;
            case waitForAssertTrialDecision:
                super.waitForAssertTrialDecision();
                break;
            case pullTheRope:
                super.pullTheRope(inMessage.getIdC(), inMessage.getTeam());
                break;
        }
        
        return new Message(MessageType.ACK);
    }

    @Override
    public boolean serviceEnded() {
        return serverEnded;
    }
    
    
}
