package com.dkord.navigation;

import com.github.wolfie.blackboard.Event;

/**
 *
 * @author Peter Gyschuk
 */
public class NavigateEvent implements Event {

    private String navigateTo;

    public NavigateEvent(String navigateTo) {
        this.navigateTo = navigateTo;
    }

    public String getNavigateTo() {
        return navigateTo;
    }
}
