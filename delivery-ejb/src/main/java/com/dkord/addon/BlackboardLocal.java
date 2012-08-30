/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.addon;

import com.github.wolfie.blackboard.Blackboard;
import com.github.wolfie.blackboard.Event;
import com.github.wolfie.blackboard.Listener;
import javax.ejb.Local;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface BlackboardLocal {

    void register(final Class<? extends Listener> listener,
            final Class<? extends Event> event);

    void addListener(final Listener listener);

    void fire(final Event event);
    
    void setBlackboard(Blackboard blackboard);
}
