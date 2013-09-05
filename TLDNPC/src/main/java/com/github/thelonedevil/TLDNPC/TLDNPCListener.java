/*
 * This file is part of the Spout plugin TLDNPC. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDNPC;

import org.spout.api.event.EventHandler;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.Listener;

/**
 * Provides an example of an event listener class.
 */
public class TLDNPCListener implements Listener {
	private TLDNPCPlugin plugin;

	public TLDNPCListener(TLDNPCPlugin instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		// Do Something on PlayerChatEvent
	}
}
