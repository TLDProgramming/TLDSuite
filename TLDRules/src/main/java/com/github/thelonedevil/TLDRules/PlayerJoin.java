package com.github.thelonedevil.TLDRules;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;

import com.github.thelonedevil.TLDCommonlib.Lib;

/**
 * A Basic Event Listener for Spout
 */
public class PlayerJoin implements Listener {
	private CleanRules plugin;

	public PlayerJoin(CleanRules instance) {
		this.plugin = instance;
	}
	private Lib lib;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player != null && lib.config.getNode("onPlayerJoin.enabled").getBoolean()) {
			for (String string : lib.onJoin) {
				player.sendMessage(string);
			}
			return;
		}
	}
}
