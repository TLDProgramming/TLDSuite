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
		plugin.firstlogin = plugin.config.getNode("Logins.First").getString();
		plugin.otherlogin = plugin.config.getNode("Logins.Other").getString();
		loginsdat();	
	}
	
	public void rules(){
		ruleCheck();
		rulesOnJoin();
	}
	
	public void notes(){
		plugin.Length = plugin.config.getNode("Notes.length").getInt();
		plugin.amount = plugin.config.getNode("Notes.amount").getInt();
	}
	
	public void afk(){
		plugin.idletime = plugin.config.getNode("AFK.IdleTime").getInt()*60000;
	}
	
	public void reserve(){
		reservedat();
		plugin.reserve = plugin.admins.size();
		plugin.reserved = ((Server) Lib.getInstance().getEngine()).getMaxPlayers() - (plugin.reserve);
		
	}

	public void randomQuote() {
		try {
			plugin.Quotes = (plugin.config.getNode("Quotes").getStringList());
			if (plugin.Quotes != null) {
				plugin.testQuote = plugin.Quotes.get(new Random().nextInt(plugin.Quotes.size()));
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
		ConfigurationNode node = plugin.config.getNode("Rules");
		int i = 1;
		for (String key : node.getKeys(true)) {
			ConfigurationNode node1 = node.getChild(key);
			List<String> list = node1.getStringList();
			if (list != null) {
				plugin.rules.put(i, list);
				i++;
			}
		}
		plugin.getLogger().info("Rules have been loaded");
	}

	public void rulesOnJoin() {
		if (plugin.config.getNode("onPlayerJoin.enabled").getBoolean() == true) {
			ConfigurationNode node = plugin.config.getNode("onPlayerJoin.rules");
			for (String key : node.getKeys(true)) {
				ConfigurationNode node1 = node.getChild(key);
				List<String> list = node1.getStringList();
				if (list != null) {
					plugin.onJoin.addAll(list);
				}
			}
			plugin.onJoin.add("To see the rest of the rules use /rules 1");
			plugin.getLogger().info("Player join rules are loaded");
		} else
			plugin.getLogger().info("Rules displayed on player join has been disabled");
	}

	@SuppressWarnings("unchecked")
	void reservedat() {
		File afkdat = new File("plugins/TLDCommonlib/reserve.dat");
		if (afkdat.exists()) {
			try {
				plugin.admins = (List<String>) SLAPI.load("plugins/TLDCommonlib/reserve.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("Reserve data has been Loaded from disk");
			try {
				SLAPI.save(plugin.admins, "plugins/TLDCommonlib/reserve.dat");
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
				plugin.logins = (HashMap<String, Integer>) SLAPI.load("plugins/TLDCommonlib/logins.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("Login data has been Loaded from disk");
			try {
				SLAPI.save(plugin.logins, "plugins/TLDCommonlib/logins.dat");
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
				plugin.factions = (HashMap<String,String>) SLAPI.load("plugins/TLDCommonlib/FactionMottos.dat");
			} catch (Exception e) {
				plugin.errorLogger();
				e.printStackTrace();
			}
			plugin.getLogger().info("Faction Motto data has been Loaded from disk");
			try {
				SLAPI.save(plugin.factions, "plugins/TLDCommonlib/FactionMottos.dat");
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
