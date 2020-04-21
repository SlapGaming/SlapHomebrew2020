package com.slapgaming.slaphomebrew.controller;

import com.slapgaming.slaphomebrew.SlapPlugin;

public abstract class AbstractController {

    protected SlapPlugin plugin;

    /**
     * Get the SlapHomebrew plugin
     */
    public AbstractController() {
        plugin = SlapPlugin.getInstance();
    }

    public abstract void shutdown();

}

