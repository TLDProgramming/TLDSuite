/*
 * This file is part of the Spout plugin TLDFactions. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDFactions;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.spout.api.component.widget.LabelComponent;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.Listener;
import org.spout.api.gui.Screen;
import org.spout.api.gui.ScreenStack;
import org.spout.api.gui.Widget;
import org.spout.api.Client;
import org.spout.api.Platform;
import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;
import java.util.HashMap;

/**
 * Provides an example of an event listener class.
 */
public class TLDFactionsListener implements Listener {

    private TLDFactionsPlugin plugin;

    public TLDFactionsListener(TLDFactionsPlugin instance) {
	this.plugin = instance;
    }
    private DataBase database = new DataBase();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
	Player p = event.getPlayer();
	final String name = p.getName();
	HashMap<String, Object> map = null;
	try {
	    map = database.getRow(Lib.statement, "Factions", name, "Player");
	} catch (SQLException e) {
	}

	if (map != null && map.isEmpty()) {
	    map.put("Faction", "null");
	    map.put("Rank", "null");
	    try {
		database.insertRow(Lib.statement, "Factions", name, "Player", map);
	    } catch (SQLException e) {
	    }
	}
	if (map != null && !map.isEmpty()) {
	    if (plugin.getEngine().getPlatform() == Platform.CLIENT) {
		String Faction;
		String Rank;
		if (map.get("Faction") == "null") {
		    Faction = "No Faction";
		} else {
		    Faction = (String) map.get("Faction");
		}
		if (map.get("Rank") == "null") {
		    Rank = "No Rank";
		} else {
		    Rank = (String) map.get("Rank");
		}
		ScreenStack stack = ((Client) plugin.getEngine()).getScreenStack();
		Screen screen = new Screen();
		Widget faction = stack.createWidget();
		LabelComponent labelField = faction.add(LabelComponent.class);
		labelField.setText("Faction: " + Faction);
		labelField.newLine();
		labelField.append("Rank: " + Rank);
		screen.attachWidget(plugin, faction);
		stack.openScreen(screen);
	    }
	}
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
	Player p = event.getPlayer();
	final String name = p.getName();
	String Message = event.getMessage();
	HashMap<String, Object> map = null;
	try {
	    map = database.getRow(Lib.statement, "Factions", name, "Player");
	} catch (SQLException e) {
	}
	String Faction = "null";
	String Rank = "null";
	if (map != null && !map.isEmpty()) {
	    Faction = (String) map.get("Faction");
	    Rank = (String) map.get("Rank");
	}

	if (!Faction.equalsIgnoreCase("null") && !Rank.equalsIgnoreCase("null")) {
	    event.setMessage("[" + Faction + "]: " + Message);
	}
    }

    /*@EventHandler
     public void onPVP(PlayerInteractEntityEvent event) {
     Player source = event.getEntity();
     String name = source.getName();
     Action action = event.getAction();
     Entity e1 = event.getInteracted();
     Player target;
     String name1;
     String targetFaction = "null";
     String sourceFaction = "null";
     String query = "SELECT * FROM Factions;";
     ResultSet rs;
     if (e1 instanceof Player) {
     target = (Player) e1;
     name1 = target.getName();
     Health start = target.get(Health.class);
     if (action == Action.LEFT_CLICK) {
     try {
     rs = DataBase.rs(Lib.statement, query);
     while (rs.next()) {
     if (rs.getString("Player").equalsIgnoreCase(name)) {
     sourceFaction = rs.getString("Faction");
							
     }
     if(rs.getString("Player").equalsIgnoreCase(name1)) {
     targetFaction = rs.getString("Faction");
							
     }
     }
     rs.close();
     if(sourceFaction != "null" && sourceFaction.equalsIgnoreCase(targetFaction)){
						
     }
     } catch (SQLException e) {
     e.printStackTrace();
     }
     }
     } else if (e1 instanceof Player == false) {
     return;
     }
		

     }*/
}
