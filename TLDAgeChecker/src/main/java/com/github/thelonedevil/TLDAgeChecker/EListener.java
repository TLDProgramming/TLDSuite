package com.github.thelonedevil.TLDAgeChecker;

import com.github.thelonedevil.TLDCommonlib.Lib;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.player.input.PlayerKeyEvent;
import org.spout.api.geo.discrete.Point;

public class EListener implements Listener {

	private TLDAgeChecker plugin;

	public EListener(TLDAgeChecker instance) {
		this.plugin = instance;
	}

	private Lib lib = new Lib();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		if (lib.DOB.get(name) == null) {
			player.sendMessage("Please verify your age by using the command /DOB <Your date of birth goes here in the format: " + lib.dateformat + ">");
		} else if (lib.DOB.get(name) != null) {
			if (lib.allowed.get(name) == true) {
			} else if (lib.allowed.get(name) == false) {
				player.kick("You are not old enough to play on this server");
			}
		}
	}

	@EventHandler
	public void onMove(PlayerKeyEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		Point loc = p.getPhysics().getPosition();
		if (lib.DOB.get(name) == null) {
			p.getPhysics().setPosition(loc);
		}
	}
}
