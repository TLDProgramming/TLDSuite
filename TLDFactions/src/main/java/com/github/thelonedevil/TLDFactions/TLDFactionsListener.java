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
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.player.Action;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.player.PlayerInteractEntityEvent;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.Listener;
import org.spout.api.gui.Screen;
import org.spout.api.gui.ScreenStack;
import org.spout.api.gui.Widget;
import org.spout.api.Client;
import org.spout.api.Platform;

import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;

/**
 * Provides an example of an event listener class.
 */
public class TLDFactionsListener implements Listener {
	private TLDFactionsPlugin plugin;

	public TLDFactionsListener(TLDFactionsPlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		final String name = p.getName();
		String query = "SELECT * FROM Factions;";
		List<String> pl = new ArrayList<String>();
		try {
			ResultSet rs = DataBase.rs(Lib.statement, query);
			while (rs.next()) {
				pl.add(rs.getString("Player"));
			}

			if (!pl.contains(name)) {
				String update = "INSERT INTO Factions (Player, Faction, Rank) VALUES ('" + name + "', 'null', 'null');";
				try {
					Lib.statement.executeUpdate(update);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (pl.contains(name)) {
				String Faction = "null";
				String Rank = "null";
				rs.close();
				rs = DataBase.rs(Lib.statement, query);
				while (rs.next()) {
					if (rs.getString("Player").equalsIgnoreCase(name)) {
						Faction = rs.getString("Faction");
						Rank = rs.getString("Rank");
						rs.close();
					}
				}
				if (!Faction.equalsIgnoreCase("null") && !Rank.equalsIgnoreCase("null")) {
					if (plugin.getEngine().getPlatform() == Platform.CLIENT) {
						ScreenStack stack = ((Client) plugin.getEngine()).getScreenStack();
						Screen screen = new Screen();
						Widget faction = stack.createWidget();
						LabelComponent labelField = faction.add(LabelComponent.class);
						labelField.setText("Faction: " + Faction);
						labelField.newLine();
						labelField.append("Rank: " + Rank);
						screen.attachWidget(plugin, faction);
						stack.openScreen(screen);
						// p.setDisplayName(Faction + "\n" + Rank + "\n" +
						// name);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onChat(PlayerChatEvent event) {
		Player p = event.getPlayer();
		final String name = p.getName();
		String query = "SELECT * FROM Factions;";
		String Faction = "null";
		String Rank = "null";
		ResultSet rs;
		String Message = event.getMessage();
		try {
			rs = DataBase.rs(Lib.statement, query);
			while (rs.next()) {
				if (rs.getString("Player").equalsIgnoreCase(name)) {
					Faction = rs.getString("Faction");
					Rank = rs.getString("Rank");
					rs.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!Faction.equalsIgnoreCase("null") && !Rank.equalsIgnoreCase("null")) {
			event.setMessage("[" + Faction + "]: " + Message);
		}
	}

	@EventHandler
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
						event.setCancelled(true);						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (e1 instanceof Player == false) {
			return;
		}
		

	}
}
