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

public class MiningListener implements Listener {
	private DataBase database = new DataBase();
	private TLDScapePlugin plugin;

	public MiningListener(TLDScapePlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onMine(PlayerInteractBlockEvent event) {
		Action action = event.getAction();
		if (action == Action.RIGHT_CLICK) {
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
				int level = Levels.getLevel(xp);
				switch (level) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
				case 16:
				case 17:
				case 18:
				case 19:
				case 20:
				case 21:
				case 22:
				case 23:
				case 24:
				case 25:
				case 26:
				case 27:
				case 28:
				case 29:
				case 30:
				}
			}
		}
	}
}
