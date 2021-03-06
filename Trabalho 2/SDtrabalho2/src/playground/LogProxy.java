/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playground;

import communication.message.Message;
import general_info_repo.IPlayground;
import communication.message.MessageType;
import communication.message.WrapperMessage;
import communication.proxy.ClientProxy;
import settings.NodeSettsProxy;

/**
 * Log proxy
 * @author António Ferreira, 67405; Rodrigo Cunha, 67800
 */
public class LogProxy implements IPlayground{

    private final String SERVER_HOST;
    private final int SERVER_PORT;
    
    /**
    * Log Proxy
    */
    public LogProxy(){
        NodeSettsProxy proxy = new NodeSettsProxy(); 
        SERVER_HOST = proxy.SERVER_HOSTS().get("Log");
        SERVER_PORT = proxy.SERVER_PORTS().get("Log");
    }
    
    /**
    * Communicate method to communicate with the Log
    */
    private WrapperMessage communicate(Message m){
        return ClientProxy.connect(SERVER_HOST,  SERVER_PORT, m);
    }
    
    /**
    * Update rope
    */
    @Override
    public void updateRope(String team, int contestant) {
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        communicate(new Message(mt, team, contestant));
    }
    
}