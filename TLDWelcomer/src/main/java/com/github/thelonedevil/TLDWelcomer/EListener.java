package com.github.thelonedevil.TLDWelcomer;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;

import com.github.thelonedevil.TLDCommonlib.Lib;

/**
 * A Basic Event Listener for Spout
 */
public class EListener implements Listener {
	private Welcomer plugin;

	public EListener(Welcomer instance) {
		this.plugin = instance;
	}

	static String name;

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		name = player.getName();
		if (Lib.logins.get(name) == null) {
			plugin.getLogger().info(Lib.error);
		} else if (Lib.logins.get(name) == 1) {
			player.sendMessage("Welcome, " + name + ".");
			player.sendMessage(Lib.firstlogin);
		} else if (Lib.logins.get(name) == 2) {
			player.sendMessage("Welcome back, " + name + ".");
			player.sendMessage("You have played once before.");
			player.sendMessage(Lib.otherlogin);
		} else if (Lib.logins.get(name) > 2) {
			int plays = Lib.logins.get(name) - 1;
			player.sendMessage("Welcome back, " + name + ".");
			player.sendMessage("You have played " + plays + " times before.");
			player.sendMessage(Lib.otherlogin);
		}
	}
}
