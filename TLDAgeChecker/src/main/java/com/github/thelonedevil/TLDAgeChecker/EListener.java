package com.github.thelonedevil.TLDAgeChecker;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.player.input.PlayerInputEvent;
import org.spout.api.geo.discrete.Point;


public class EListener implements Listener {
	private TLDAgeChecker plugin;

	public EListener(TLDAgeChecker instance) {
		this.plugin = instance;
	}
	static String name;

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		name = player.getName();

		if (TLDAgeChecker.DOB.get(name) == null) {
			player.sendMessage("Please verify your age by using the command /DOB <Your date of birth goes here in the format DD/MM/YYYY>");
		} else if (TLDAgeChecker.DOB.get(name) != null) {
			if (TLDAgeChecker.allowed.get(name) == true){
				
			}else if (TLDAgeChecker.allowed.get(name) == false){
				player.kick("You are not old enough to play on this server");
			}
		}
	}
	@EventHandler
	public void onMove(PlayerInputEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		Point loc = p.getPhysics().getPosition();
		if (TLDAgeChecker.DOB.get(name) == null){
			p.teleport(loc);
		}
	}
}
