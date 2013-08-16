/*
 * This file is part of the Spout plugin TLDScape. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDScape;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.Listener;

import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;

/**
 * Provides an example of an event listener class.
 */
public class TLDScapeListener implements Listener {
	private TLDScapePlugin plugin;
	private DataBase databsae = new DataBase();

	public TLDScapeListener(TLDScapePlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		// TODO make quickchat!
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		String query = "SELECT * FROM Factions;";
		List<String> pl = new ArrayList<String>();
		try {
			ResultSet rs = DataBase.rs(Lib.statement, query);
			while (rs.next()) {
				pl.add(rs.getString("Player"));
			}

			if (!pl.contains(name)) {
				String update = "INSERT INTO ScapeSkills (Player, HitPoints) VALUES ('" + name + "', '1154');";
				try {
					Lib.statement.executeUpdate(update);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
