package com.github.thelonedevil.TLDScape.skills;

import java.sql.SQLException;
import java.util.HashMap;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.Action;
import org.spout.api.event.player.PlayerInteractBlockEvent;

import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDScape.TLDScapePlugin;

public class MiningListener implements Listener{
	private DataBase database = new DataBase();
	private TLDScapePlugin plugin;

	public MiningListener(TLDScapePlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onMine(PlayerInteractBlockEvent event) {
		Action action = event.getAction();
		Player p = event.getEntity();
		String name = p.getName();
		HashMap<String, Object> skills = null;
		try {
			skills = database.getRow(Lib.statement, "ScapeSkills", name, "Player");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (skills != null) {
			int xp = (Integer) skills.get("Mining");
			if (xp >= Levels.L1) {
				// wooden
			} else if (xp >= Levels.L2) {
			} else if (xp >= Levels.L3) {
			} else if (xp >= Levels.L4) {
			} else if (xp >= Levels.L5) {
			} else if (xp >= Levels.L6) {
				// stone
			} else if (xp >= Levels.L7) {
			} else if (xp >= Levels.L8) {
			} else if (xp >= Levels.L9) {
			} else if (xp >= Levels.L10) {
			} else if (xp >= Levels.L11) {
			} else if (xp >= Levels.L12) {
				// gold
			} else if (xp >= Levels.L13) {
			} else if (xp >= Levels.L14) {
			} else if (xp >= Levels.L15) {
			} else if (xp >= Levels.L16) {
			} else if (xp >= Levels.L17) {
			} else if (xp >= Levels.L18) {
				// iron
			} else if (xp >= Levels.L19) {
			} else if (xp >= Levels.L20) {
			} else if (xp >= Levels.L21) {
			} else if (xp >= Levels.L22) {
			} else if (xp >= Levels.L23) {
			} else if (xp >= Levels.L24) {
				// diamond
			} else if (xp >= Levels.L25) {
			} else if (xp >= Levels.L26) {
			} else if (xp >= Levels.L27) {
			} else if (xp >= Levels.L28) {
			} else if (xp >= Levels.L29) {
			} else if (xp >= Levels.L30) {
				// faster mining
			}
		}
	}
}
