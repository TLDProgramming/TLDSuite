/*
 * This file is part of the Spout plugin TLDTrains. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDTrains;

import org.spout.api.event.EventHandler;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.Listener;

/**
 * Provides an example of an event listener class.
 */
public class TLDTrainsListener implements Listener {
	private TLDTrainsPlugin plugin;

	public TLDTrainsListener(TLDTrainsPlugin instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		// Do Something on PlayerChatEvent
	}
}
