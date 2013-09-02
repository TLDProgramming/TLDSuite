package com.github.thelonedevil.TLDReserve;

import org.spout.api.Server;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;

import com.github.thelonedevil.TLDCommonlib.Lib;

/**
 * A Basic Event Listener for Spout
 */
public class EListener implements Listener {

	private TLDReserve plugin;

	public EListener(TLDReserve instance) {
		this.plugin = instance;
	}

	private Lib lib = new Lib();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		Player[] online = ((Server) plugin.getEngine()).getOnlinePlayers();
		int w = online.length;
		if (w == lib.reserved && !lib.admins.contains(name)) {
			p.kick();
		}
	}
}
