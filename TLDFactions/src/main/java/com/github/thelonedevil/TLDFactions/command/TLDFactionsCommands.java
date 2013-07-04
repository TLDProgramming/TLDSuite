/*
 * This file is part of the Spout plugin TLDFactions. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDFactions.command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.spout.api.command.annotated.Permissible;
import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;

import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDFactions.TLDFactionsPlugin;

/**
 * Provides an example of a class to hold commands.
 */
public class TLDFactionsCommands {

	private TLDFactionsPlugin plugin;

	public TLDFactionsCommands(TLDFactionsPlugin instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@Command(aliases = { "create", "found" }, desc = "This is an example of what a command might look like. Try it out with /cmd !", min = 2)
	@Permissible("TLDFactions.create")
	public void create(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		String faction = args.getString(0);
		String motto = args.getJoinedString(1);
		String query = "select Faction from Factions";
		List<String> pl = new ArrayList<String>();
		try {
			ResultSet rs = DataBase.rs(Lib.statement, query);
			while (rs.next()) {
				pl.add(rs.getString("Faction"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!pl.contains(faction)) {
			try {
				String update = "UPDATE Factions SET Faction='" + faction + "', " + "Rank='Founder' WHERE Player='" + name + "' ";
				Lib.statement.executeUpdate(update);
				Lib.factions.put(faction, motto);
				source.sendMessage("You have Founded the Faction " + faction + ".");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Command(aliases = { "join" }, min = 1, max = 1, desc = "Lets you join a faction")
	@Permissible("TLDFactions.join")
	public void join(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		String faction = args.getString(0);
		String query = "select Faction, Player from Factions";
		try {
			ResultSet rs = DataBase.rs(Lib.statement, query);
			while (rs.next()) {
				if (rs.getString("Player").equalsIgnoreCase(name) && rs.getString("Faction") == "null") {
					String update = "UPDATE Factions SET Faction='" + faction + "', " + "Rank='Newbie' WHERE Player='" + name + "' ";
					Lib.statement.executeUpdate(update);
					source.sendMessage("You have joined the faction: " + faction);
				}
			}
		} catch (SQLException e) {

		}
	}

	@Command(aliases = { "leave" }, min = 0, max = 0, desc = "Lets you leave a faction")
	@Permissible("TLDFactions.leave")
	public void leave(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		String query = "select Faction, Player from Factions";
		try {
			ResultSet rs = DataBase.rs(Lib.statement, query);
			while (rs.next()) {
				if (rs.getString("Player").equalsIgnoreCase(name)) {
					final String faction = rs.getString("Faction");
					String update = "UPDATE Factions SET Faction='null',Rank='null' WHERE Player='" + name + "' ";
					Lib.statement.executeUpdate(update);
					source.sendMessage("You have left the faction: " + faction);
				}
			}
		} catch (SQLException e) {

		}
	}

	@Command(aliases = { "list" }, min = 0, max = 0, desc = "Lists factions")
	@Permissible("TLDFactions.list")
	public void list(CommandSource source, CommandArguments args) throws CommandException {
		String query = "select Faction, Player from Factions";
		try {
			ResultSet rs = DataBase.rs(Lib.statement, query);
			List<String> pl = new ArrayList<String>();
			while (rs.next()) {
				if (!pl.contains(rs.getString("Faction"))) {
					pl.add(rs.getString("Faction"));
				}
			}
			int s = pl.size();
			int i = 0;
			source.sendMessage("There are " + s + " Factions:");
			while (i < s) {
				String message = pl.get(i);
				source.sendMessage("- " + message);
				i++;
			}
		} catch (SQLException e) {

		}
	}

	@Command(aliases = { "members" }, min = 0, max = 1, desc = "lists members")
	@Permissible("TLDFactions.members")
	public void members(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		String query = "SELECT Player, Faction FROM Factions";
		String Faction = "null";
		List<String> pl = new ArrayList<String>();
		try {
			ResultSet rs = DataBase.rs(Lib.statement, query);
			if (args.length() == 0) {
				while (rs.next()) {
					if (rs.getString("Player").equalsIgnoreCase(name)) {
						Faction = rs.getString("Faction");	
					}
				}

			} else if (args.length() == 1) {
				Faction = args.getString(0);
			}
			rs.close();
			rs = DataBase.rs(Lib.statement, query);
			while (rs.next()) {
				if (rs.getString("Faction").equalsIgnoreCase(Faction)) {
					pl.add(rs.getString("Player"));
				}
			}
			int s = pl.size();
			int i = 0;
			source.sendMessage("There are " + s + " people in " + Faction + ".");
			while (i < s) {
				String message = pl.get(i);
				source.sendMessage("- " + message);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Command(aliases = { "claim" }, min = 0, max = 2, desc = "claims land for your faction")
	@Permissible("TLDFactions.claim")
	public void claim(CommandSource source, CommandArguments args) throws CommandException {
		if (source instanceof Player) {
			Player p = (Player) source;
			int x = args.getInteger(0);
			int y = args.getInteger(1);
			int z = args.getInteger(0);
			Point point = p.getScene().getPosition();
			Block start = point.getBlock();
			Point max = point.add(x, y, z);
			Point min = point.subtract(x, y, z);
			Block bottom = min.getBlock();
			Block top = max.getBlock();
			World w = point.getWorld();

		}
	}
}
