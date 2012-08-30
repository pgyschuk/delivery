package com.dkord.navigation;

import com.github.wolfie.blackboard.Listener;
import com.github.wolfie.blackboard.annotation.ListenerMethod;

/**
 *
 * @author Peter Gyschuk
 */
public interface NavigateListener extends Listener {

    @ListenerMethod
    public void navigate(NavigateEvent event);
}
