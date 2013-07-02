package com.github.thelonedevil.TLDQuotes;

import java.util.Random;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.vanilla.event.player.PlayerRespawnEvent;

import com.github.thelonedevil.TLDCommonlib.Lib;

/**
 * A Basic Event Listener for Spout
 */
public class EListener implements Listener {

	private Lib lib;
	private Quoter plugin;

	public EListener(Quoter instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerDeath(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if (player != null) {
			String quote = lib.Quotes.get(new Random().nextInt(lib.Quotes.size()));
			player.sendMessage(quote);
			return;
		}
	}
}
