/*
 * This file is part of the Spout plugin TLDScape. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDScape;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		HashMap<String, Object> skills = new HashMap<String, Object>();
		try {
			skills = database.getRow(Lib.statement, "ScapeSkills", name, "Player");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(skills.isEmpty()){
			skills.put("hitpoints", 1154);
			try {
				database.updateRow(Lib.statement, "ScapeSkills", name, "Player", skills);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
