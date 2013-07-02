package com.github.thelonedevil.TLDAfk;

import java.util.HashMap;

import org.spout.api.Server;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.player.input.PlayerKeyEvent;
import org.spout.api.event.Listener;

import com.github.thelonedevil.TLDCommonlib.Lib;

public class EListener implements Listener {
	private TLDAfk plugin;

	public EListener(TLDAfk instance) {
		this.plugin = instance;
	}
	private Lib lib;

	@EventHandler
	public void onPlayerKey(PlayerKeyEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		HashMap<String, Long> idle = new HashMap<String, Long>();
		if (lib.afk.containsKey(name) && lib.afk.get(name) == true) {
			lib.afk.put(name, false);
			player.sendMessage("You are no longer AFK");
			String message = name + " Is no longer AFK";
			((Server) plugin.getInstance().getEngine()).broadcastMessage(message);
		} else if (!lib.afk.containsKey(name) || lib.afk.get(name) == false) {
			long value = System.currentTimeMillis();
			idle.put(name, value);
		}
		if (idle.get(name) != null) {
			long value1 = idle.get(name);
			long value2 = System.currentTimeMillis();
			long value3 = value2 - value1;
			long value4 = lib.idletime;
			if (value3 >= value4) {
				lib.afk.put(name, true);
			}
		}
	}
}
