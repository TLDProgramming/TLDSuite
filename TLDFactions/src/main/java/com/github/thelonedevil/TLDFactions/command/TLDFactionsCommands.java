/*
 * This file is part of the Spout plugin TLDFactions. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDFactions.command;

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
import org.spout.api.geo.Protection;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;
import com.github.thelonedevil.TLDCommonlib.DataBase;
import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDFactions.TLDFactionsPlugin;
import com.github.thelonedevil.TLDFactions.Protection.Protect;
import com.github.thelonedevil.TLDFactions.Protection.service;
import java.util.HashMap;

/**
 * Provides an example of a class to hold commands.
 */
public class TLDFactionsCommands {

    private TLDFactionsPlugin plugin;

    public TLDFactionsCommands(TLDFactionsPlugin instance) {
	this.plugin = instance;
    }
    private Lib lib = new Lib();
    private DataBase database = new DataBase();

    /**
     * Provides an example command that can be issued to the Spout server.
     */
    @CommandDescription(aliases = {"create", "found"}, desc = "Lets you create a Faction")
    @Permissible("TLDFactions.create")
    @Filter(PlayerFilter.class)
    public void create(CommandSource source, CommandArguments args) throws CommandException {
	String name = source.getName();
	String faction = args.popString("Faction");
	String motto = args.popRemainingStrings("Faction Motto");
	HashMap<String, Object> map = null;
	try {
	    map = database.getRow(Lib.statement, "Factions", name, "Player");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	if (map != null && !map.isEmpty()) {
	    try {
		if (!database.getStrings(Lib.statement, "Factions", "Faction").contains(faction)) {
		    database.updateRow(Lib.statement, "Factions", name, "Player", map);
		    lib.factions.put(faction, motto);
		    lib.factionsclaims.put(faction, 0);
		    source.sendMessage("You have Founded the Faction " + faction + ".");
		} else {
		    source.sendMessage("The Faction: " + faction + " already exsists.");
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    @CommandDescription(aliases = {"join"}, desc = "Lets you join a faction")
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

    @CommandDescription(aliases = {"leave"}, desc = "Lets you leave a faction")
    @Permissible("TLDFactions.leave")
    @Filter(PlayerFilter.class)
    public void leave(CommandSource source, CommandArguments args) throws CommandException {
	String name = source.getName();
	HashMap<String, Object> map = null;
	try {
	    map = database.getRow(Lib.statement, "Factions", name, "Player");
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	if (map != null && !map.isEmpty()) {
	    String faction = (String) map.get("Faction");
	    map.put("Faction", "null");
	    map.put("Rank", "null");
	    try {
		database.updateRow(Lib.statement, "Factions", name, "Player", map);
		source.sendMessage("You have left the Faction " + faction + ".");
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    @CommandDescription(aliases = {"list"}, desc = "Lists factions")
    @Permissible("TLDFactions.list")
    public void list(CommandSource source, CommandArguments args) throws CommandException {
	try {
	    List<String> pl = database.getStrings(Lib.statement, "Factions", "Faction", true);
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

    @CommandDescription(aliases = {"members"}, desc = "lists members")
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

    @CommandDescription(aliases = {"claim"}, desc = "claims land for your faction")
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
		int amount = lib.factionsclaims.get(Faction);
		int x = args.popInteger("x");
		int y = args.popInteger("y");
		int z = args.popInteger("z");
		Point point = p.getPhysics().getPosition();
		Point max = point.add(x, y, z);
		Point min = point.subtract(x, y, z);
		World w = point.getWorld();
		List<Block> blocksnew = getBlocks(max, min, w);
		List<Point> pointsnew = getPoints(blocksnew);
		try {
		    String query1 = "SELECT Faction,amount,x,y,z FROM FactionClaims";
		    ResultSet rs = DataBase.rs(Lib.statement, query1);
		    List<Point> points = new ArrayList<Point>();
		    while (rs.next()) {
			if (rs.getString("Faction").equalsIgnoreCase(Faction)) {
			    int x1 = rs.getInt("x");
			    int y1 = rs.getInt("y");
			    int z1 = rs.getInt("z");
			    Point point1 = new Point(w, x1, y1, z1);
			    points.add(point1);
			    rs.deleteRow();
			}
		    }
		    List<Point> points1 = addList(points, pointsnew);
		    List<Integer> x2 = getXs(points1);
		    List<Integer> y2 = getYs(points1);
		    List<Integer> z2 = getZs(points1);
		    addCoods(x2, y2, z2, Faction);
		    int newamount = amount++;
		    String faction = Faction + ":" + newamount;
		    Protection protect = new Protect(faction, w, point, x);
		    plugin
			    .getEngine().getServiceManager().getRegistration(service.class).getProvider().addProtection(protect);
		    lib.factionsclaims.put(Faction, newamount);

		    rs.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}

	    } else {
		source.sendMessage("You are not in a Faction so you cannot claim land");
	    }
	}
    }

    private List<Point> getPoints(List<Block> blocksnew) {
	List<Point> points = new ArrayList<Point>();
	int i = 0;
	while (blocksnew.get(i) != null) {
	    Block block = blocksnew.get(i);
	    Point point = block.getPosition();
	    points.add(point);
	    i++;
	}
	return points;
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

    List<Point> addList(List<Point> a, List<Point> b) {
	List<Point> blocks = new ArrayList<Point>();
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

    List<Integer> getXs(List<Point> points) {
	List<Integer> xs = new ArrayList<Integer>();
	int index = 0;
	while (points.get(index) != null) {
	    xs.add(points.get(index).getBlockX());
	    index++;
	}
	return xs;
    }

    List<Integer> getYs(List<Point> points) {
	List<Integer> ys = new ArrayList<Integer>();
	int index = 0;
	while (points.get(index) != null) {
	    ys.add(points.get(index).getBlockY());
	    index++;
	}
	return ys;
    }

    List<Integer> getZs(List<Point> points) {
	List<Integer> zs = new ArrayList<Integer>();
	int index = 0;
	while (points.get(index) != null) {
	    zs.add(points.get(index).getBlockZ());
	    index++;
	}
	return zs;
    }

    void addCoods(List<Integer> X, List<Integer> Y, List<Integer> Z, String faction) throws SQLException {
	int index = 0;
	while (X.get(index) != null && Y.get(index) != null && Z.get(index) != null) {
	    int x = X.get(index);
	    int y = Y.get(index);
	    int z = Z.get(index);
	    String sql = "INSERT INTO FactionsClaims Values '" + faction + "'," + x + "," + y + "," + z;
	    Lib.statement.executeUpdate(sql);
	}

    }
}
