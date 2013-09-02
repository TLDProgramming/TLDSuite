/*
 * This file is part of the Spout plugin TLDScape. It also has a hard
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDScape;

import java.sql.SQLException;
import java.util.HashMap;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.player.PlayerJoinEvent;

import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDScape.skills.Levels;
import org.spout.api.event.Order;
import org.spout.vanilla.component.entity.misc.Health;

/**
 * Provides an example of an event listener class.
 */
public class TLDScapeListener implements Listener {

	private TLDScapePlugin plugin;
	private DataBase database = new DataBase();

	public TLDScapeListener(TLDScapePlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		// TODO make quickchat!
	}

	@EventHandler(order = Order.EARLIEST)
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		HashMap<String, Object> skills = null;
		try {
			skills = database.getRow(Lib.statement, "ScapeSkills", name, "Player");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (skills != null && skills.isEmpty()) {
			try {
				skills.put("HitPoints", 1154);
				database.insertRow(Lib.statement, "ScapeSkills", name, "Player", skills);
				p.get(Health.class).setSpawnHealth(10.0f);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (skills != null && !skills.isEmpty()) {
			int attack = Levels.getLevel((Integer) skills.get("Attack"));
			int strength = Levels.getLevel((Integer) skills.get("Strength"));
			int defence = Levels.getLevel((Integer) skills.get("Defence"));
			int hp = Levels.getLevel((Integer) skills.get("HitPoints"));
			int ranged = Levels.getLevel((Integer) skills.get("Ranged"));
			int magic = Levels.getLevel((Integer) skills.get("Magic"));
			int prayer = Levels.getLevel((Integer) skills.get("Prayer"));
			int summoning = Levels.getLevel((Integer) skills.get("Summoning"));

			int combat = Levels.getCombatLevel(attack, strength, defence, hp, ranged, magic, prayer, summoning);
			String dispname = "(Level: " + combat + ") " + name;
			p.setDisplayName(dispname);

			p.get(Health.class).setSpawnHealth(hp);
		} else {
			throw new IllegalStateException("Player has no skill levels");
		}
	}
}
