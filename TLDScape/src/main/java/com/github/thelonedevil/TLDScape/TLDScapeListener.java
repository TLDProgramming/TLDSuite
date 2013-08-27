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
	    skills.put("HitPoints", 1154);
	    try {
		database.insertRow(Lib.statement, "ScapeSkills", name, "Player", skills);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	if (skills != null && !skills.isEmpty()) {
	    int xp = (Integer) skills.get("HitPoints");
	    if (xp >= Levels.L1) {
		p.get(Health.class).setSpawnHealth(1.0f);
	    } else if (xp >= Levels.L2) {
		p.get(Health.class).setSpawnHealth(2.0f);
	    } else if (xp >= Levels.L3) {
		p.get(Health.class).setSpawnHealth(3.0f);
	    } else if (xp >= Levels.L4) {
		p.get(Health.class).setSpawnHealth(4.0f);
	    } else if (xp >= Levels.L5) {
		p.get(Health.class).setSpawnHealth(5.0f);
	    } else if (xp >= Levels.L6) {
		p.get(Health.class).setSpawnHealth(6.0f);
	    } else if (xp >= Levels.L7) {
		p.get(Health.class).setSpawnHealth(7.0f);
	    } else if (xp >= Levels.L8) {
		p.get(Health.class).setSpawnHealth(8.0f);
	    } else if (xp >= Levels.L9) {
		p.get(Health.class).setSpawnHealth(9.0f);
	    } else if (xp >= Levels.L10) {
		p.get(Health.class).setSpawnHealth(10.0f);
	    } else if (xp >= Levels.L11) {
		p.get(Health.class).setSpawnHealth(11.0f);
	    } else if (xp >= Levels.L12) {
		p.get(Health.class).setSpawnHealth(12.0f);
	    } else if (xp >= Levels.L13) {
		p.get(Health.class).setSpawnHealth(13.0f);
	    } else if (xp >= Levels.L14) {
		p.get(Health.class).setSpawnHealth(14.0f);
	    } else if (xp >= Levels.L15) {
		p.get(Health.class).setSpawnHealth(15.0f);
	    } else if (xp >= Levels.L16) {
		p.get(Health.class).setSpawnHealth(16.0f);
	    } else if (xp >= Levels.L17) {
		p.get(Health.class).setSpawnHealth(17.0f);
	    } else if (xp >= Levels.L18) {
		p.get(Health.class).setSpawnHealth(18.0f);
	    } else if (xp >= Levels.L19) {
		p.get(Health.class).setSpawnHealth(19.0f);
	    } else if (xp >= Levels.L20) {
		p.get(Health.class).setSpawnHealth(20.0f);
	    } else if (xp >= Levels.L21) {
		p.get(Health.class).setSpawnHealth(21.0f);
	    } else if (xp >= Levels.L22) {
		p.get(Health.class).setSpawnHealth(22.0f);
	    } else if (xp >= Levels.L23) {
		p.get(Health.class).setSpawnHealth(23.0f);
	    } else if (xp >= Levels.L24) {
		p.get(Health.class).setSpawnHealth(24.0f);
	    } else if (xp >= Levels.L25) {
		p.get(Health.class).setSpawnHealth(25.0f);
	    } else if (xp >= Levels.L26) {
		p.get(Health.class).setSpawnHealth(26.0f);
	    } else if (xp >= Levels.L27) {
		p.get(Health.class).setSpawnHealth(27.0f);
	    } else if (xp >= Levels.L28) {
		p.get(Health.class).setSpawnHealth(28.0f);
	    } else if (xp >= Levels.L29) {
		p.get(Health.class).setSpawnHealth(29.0f);
	    } else if (xp >= Levels.L30) {
		p.get(Health.class).setSpawnHealth(30.0f);
	    }
	} else {
	    throw new IllegalStateException("Player has no skill levels");
	}
    }
}
