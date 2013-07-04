package com.github.thelonedevil.TLDCommonlib;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import java.util.Random;

import org.spout.api.Server;
import org.spout.api.util.config.ConfigurationNode;

public class Data {
	private Lib plugin;

	public Data(Lib instance) {
		this.plugin = instance;
	}

	public void loginMessages() {
		Lib.firstlogin = Lib.config.getNode("Logins.First").getString();
		Lib.otherlogin = Lib.config.getNode("Logins.Other").getString();
		loginsdat();
	}

	public void rules() {
		ruleCheck();
		rulesOnJoin();
	}

	public void notes() {
		Lib.Length = Lib.config.getNode("Notes.length").getInt();
		Lib.amount = Lib.config.getNode("Notes.amount").getInt();
	}

	public void afk() {
		Lib.idletime = Lib.config.getNode("AFK.IdleTime").getInt() * 60000;
	}

	public void reserve() {
		reservedat();
		Lib.reserve = Lib.admins.size();
		Lib.reserved = ((Server) Lib.getInstance().getEngine()).getMaxPlayers() - (Lib.reserve);

	}

	public void randomQuote() {
		try {
			Lib.Quotes = (Lib.config.getNode("Quotes").getStringList());
			if (Lib.Quotes != null) {
				plugin.testQuote = Lib.Quotes.get(new Random().nextInt(Lib.Quotes.size()));
				if (plugin.testQuote != null) {
					plugin.getLogger().info("Quotes loaded");
				} else
					plugin.getLogger().info("Quotes not loaded");
			}
		} catch (IllegalArgumentException e) {
			plugin.getLogger().info("Quotes missing, creating default config.yml");
		}

	}

	public void ruleCheck() {
		ConfigurationNode node = Lib.config.getNode("Rules");
		int i = 1;
		for (String key : node.getKeys(true)) {
			ConfigurationNode node1 = node.getChild(key);
			List<String> list = node1.getStringList();
			if (list != null) {
				Lib.rules.put(i, list);
				i++;
			}
		}
		plugin.getLogger().info("Rules have been loaded");
	}

	public void rulesOnJoin() {
		if (Lib.config.getNode("onPlayerJoin.enabled").getBoolean() == true) {
			Lib.onjoin = true;
			ConfigurationNode node = Lib.config.getNode("onPlayerJoin.rules");
			List<String> list = node.getStringList();
			if (list != null) {
				Lib.onJoin.addAll(list);
			}
			Lib.onJoin.add("To see the rest of the rules use /rules 1");
			plugin.getLogger().info("Player join rules are loaded");
		} else {
			Lib.onjoin = false;
			plugin.getLogger().info("Rules displayed on player join has been disabled");
		}
	}

	@SuppressWarnings("unchecked")
	void reservedat() {
		File afkdat = new File("plugins/TLDCommonlib/reserve.dat");
		if (afkdat.exists()) {
			try {
				Lib.admins = (List<String>) SLAPI.load("plugins/TLDCommonlib/reserve.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("Reserve data has been Loaded from disk");
			try {
				SLAPI.save(Lib.admins, "plugins/TLDCommonlib/reserve.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
		} else if (!afkdat.exists()) {
			plugin.getLogger().info("reserve.dat is missing... creating...");
			try {
				plugin.getDataFolder().mkdirs();
				afkdat.createNewFile();
			} catch (IOException e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("reserve.dat has been created");
		}

	}

	@SuppressWarnings("unchecked")
	void loginsdat() {
		File loginsdat = new File("plugins/TLDCommonlib/logins.dat");
		if (loginsdat.exists()) {
			try {
				Lib.logins = (HashMap<String, Integer>) SLAPI.load("plugins/TLDCommonlib/logins.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("Login data has been Loaded from disk");
			try {
				SLAPI.save(Lib.logins, "plugins/TLDCommonlib/logins.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
		} else if (!loginsdat.exists()) {
			plugin.getLogger().info("logins.dat is missing... creating...");
			try {
				plugin.getDataFolder().mkdirs();
				loginsdat.createNewFile();
			} catch (IOException e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("logins.dat has been created");
		}
	}

	@SuppressWarnings("unchecked")
	void factionsdat() {
		File afkdat = new File("plugins/TLDCommonlib/FactionMottos.dat");
		if (afkdat.exists()) {
			try {
				Lib.factions = (HashMap<String, String>) SLAPI.load("plugins/TLDCommonlib/FactionMottos.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("Faction Motto data has been Loaded from disk");
			try {
				SLAPI.save(Lib.factions, "plugins/TLDCommonlib/FactionMottos.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
		} else if (!afkdat.exists()) {
			plugin.getLogger().info("FactionMottos.dat is missing... creating...");
			try {
				plugin.getDataFolder().mkdirs();
				afkdat.createNewFile();
			} catch (IOException e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("FactionMottos.dat has been created");
		}

	}

}
