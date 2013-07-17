/*
 * This file is part of the Spout plugin TLDFactions. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDFactions.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Filter;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.command.filter.PlayerFilter;
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

	private Lib lib = new Lib();

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@CommandDescription(aliases = { "create", "found" }, desc = "This is an example of what a command might look like. Try it out with /cmd !")
	@Permissible("TLDFactions.create")
	@Filter(PlayerFilter.class)
	public void create(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		String faction = args.popString("Faction");
		String motto = args.popRemainingStrings("Faction Motto");
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
				lib.factions.put(faction, motto);
				source.sendMessage("You have Founded the Faction " + faction + ".");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@CommandDescription(aliases = { "join" }, desc = "Lets you join a faction")
	@Permissible("TLDFactions.join")
	@Filter(PlayerFilter.class)
	public void join(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		String faction = args.popString("Faction");
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

	@CommandDescription(aliases = { "leave" }, desc = "Lets you leave a faction")
	@Permissible("TLDFactions.leave")
	@Filter(PlayerFilter.class)
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

	@CommandDescription(aliases = { "list" }, desc = "Lists factions")
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

	@CommandDescription(aliases = { "members" }, desc = "lists members")
	@Permissible("TLDFactions.members")
	@Filter(PlayerFilter.class)
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
				Faction = args.popString("Faction");
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

	@SuppressWarnings("unchecked")
	@CommandDescription(aliases = { "claim" }, desc = "claims land for your faction")
	@Permissible("TLDFactions.claim")
	@Filter(PlayerFilter.class)
	public void claim(CommandSource source, CommandArguments args) throws CommandException {
		source.sendMessage("this command is a work in progress!");
		if (source instanceof Player) {
			Player p = (Player) source;
			String name = p.getName();
			String query = "SELECT Player, Faction FROM Factions";
			String Faction = "null";
			try {
				ResultSet rs = DataBase.rs(Lib.statement, query);
				while (rs.next()) {
					if (rs.getString("Player").equalsIgnoreCase(name)) {
						Faction = rs.getString("Faction");
					}
				}

				rs.close();
			} catch (SQLException e) {

			}
			if (Faction != "null") {
				int x = args.popInteger("x");
				int y = args.popInteger("y");
				int z = args.popInteger("z");
				Point point = p.getScene().getPosition();
				Point max = point.add(x, y, z);
				Point min = point.subtract(x, y, z);
				World w = point.getWorld();
				List<Block> blocksnew = getBlocks(max, min, w);
				List<Block> blocksold;

				try {
					String query1 = "SELECT Faction, Blocks FROM FactionClaims";
					ResultSet rs = DataBase.rs(Lib.statement, query1);
					while (rs.next()) {
						if (rs.getString("Faction").equalsIgnoreCase(Faction)) {
							InputStream in = rs.getBlob("Blocks").getBinaryStream();
							ObjectInputStream oin = new ObjectInputStream(in);
							blocksold = (List<Block>) oin.readObject();
							List<Block> blocks = addList(blocksnew, blocksold);
							OutputStream out = rs.getBlob("Blocks").setBinaryStream(1);
							ObjectOutputStream oout = new ObjectOutputStream(out);
							oout.writeObject(blocks);
							rs.updateRow();
							rs.close();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
		}
	}

	List<Block> getBlocks(Point max, Point min, World w) {
		List<Block> blocks = new ArrayList<Block>();
		float x1 = min.getX();
		float y1 = min.getY();
		float z1 = min.getZ();
		float x2 = max.getX();
		float y2 = max.getY();
		float z2 = max.getZ();
		while (x1 <= x2) {
			while (y1 <= y2) {
				while (z1 <= z2) {
					blocks.add(w.getBlock(x1, y1, z1));
					z1++;
				}
				y1++;
			}
			x1++;
		}

		return blocks;

	}

	List<Block> addList(List<Block> a, List<Block> b) {
		List<Block> blocks = new ArrayList<Block>();
		int size1 = a.size();
		int size2 = b.size();
		int index = 0;
		while (size2 <= index) {
			blocks.add(b.get(index));
			index++;
		}
		index = 0;
		while (size1 <= index) {
			if (!blocks.contains(a.get(index))) {
				blocks.add(a.get(index));
			}
		}
		return blocks;

	}
}
