/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.addon;

import com.github.wolfie.blackboard.Blackboard;
import com.github.wolfie.blackboard.Event;
import com.github.wolfie.blackboard.Listener;
import javax.ejb.Stateless;

/**
 *
 * @author Peter Gyschuk
 */
@Stateless
public class BlackboardBean implements BlackboardLocal {

    private Blackboard blackboard;

    @Override
    public void register(Class<? extends Listener> listener, Class<? extends Event> event) {
        blackboard.register(listener, event);
    }

    @Override
    public void addListener(Listener listener) {
        blackboard.addListener(listener);
    }

    @Override
    public void fire(Event event) {
        blackboard.fire(event);
    }

    @Override
    public void setBlackboard(Blackboard blackboard) {
        this.blackboard = blackboard;
    }
    

}
