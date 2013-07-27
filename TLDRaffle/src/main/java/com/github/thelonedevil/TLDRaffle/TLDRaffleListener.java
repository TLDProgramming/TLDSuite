/*
 * This file is part of the Spout plugin TLDRaffle. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDRaffle;

import org.spout.api.event.EventHandler;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.Listener;

/**
 * Provides an example of an event listener class.
 */
public class TLDRaffleListener implements Listener {
	private TLDRafflePlugin plugin;

	public TLDRaffleListener(TLDRafflePlugin instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		// Do Something on PlayerChatEvent
	}
}
