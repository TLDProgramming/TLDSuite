package com.github.thelonedevil.TLDCommonlib;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.spout.api.plugin.Plugin;
import org.spout.cereal.config.Configuration;
import org.spout.cereal.config.ConfigurationException;
import org.spout.cereal.config.yaml.YamlConfiguration;

public class Lib extends Plugin {

	// config
	public final String configPath = "plugins/TLDCommonLib/config.yml";
	private YamlConfiguration config;
	public List<String> Quotes;
	public String testQuote;
	public File libFolder = new File("plugins/TLDCommonLib/");
	public int reserve;
	public final Map<Integer, List<String>> rules = new HashMap<Integer, List<String>>();
	public boolean onjoin = true;
	public final List<String> onJoin = new ArrayList<String>();
	public String firstlogin;
	public String otherlogin;
	public HashMap<String, Integer> logins = new HashMap<String, Integer>();
	public HashMap<String, Boolean> afk = new HashMap<String, Boolean>();
	public int Length;
	public int amount;
	public HashMap<String, Boolean> Namount = new HashMap<String, Boolean>();
	public long idletime;
	public List<String> admins = new ArrayList<String>();
	public int reserved;
	public HashMap<String, String> factions = new HashMap<String, String>();
	public String dateformat;
	public HashMap<String, Integer> factionsclaims = new HashMap<String, Integer>();

	public HashMap<String, TrainsMap> trainowners = new HashMap<String, TrainsMap>();

	// other
	public String logged = "Plugin has been ";
	public String error = "An error has happened...... incoming stack trace....";
	private Data data = new Data(this);
	private DataBase sql = new DataBase(this);
	public static Statement statement;
	public static Connection connection;

	@Override
	public void onLoad() {
		setInstance(this);
		getLogger().info(logged + "Loaded!");

	}

	@Override
	public void onEnable() {
		libFolder.mkdirs();
		try {
			File f = new File(configPath);
			if (!f.exists()) {
				data.extract(configPath, "config.yml");
			}
			if (f.exists()) {
				SetConfig();
				if (FetchConfig() != null) {
					FetchConfig().load();
				}
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
			getLogger().log(Level.INFO, "Error handlinmg config.yml");
		}
		// database
		try {
			File file = new File("plugins/TLDCommonLib/data.sqlite");
			file.createNewFile();
			connection = sql.connect("plugins/TLDCommonLib/data.sqlite");
			statement = sql.state(connection, 30);
			sql.createTable(statement, "Factions", "Player", "string", "Faction", "string", "Rank", "string");
			sql.createTable(statement, "FactionClaims", "Faction", "string", "x", "int", "y", "int", "z", "int");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		// register listener
		getEngine().getEventManager().registerEvents(new EListener(this), this);

		// data loading
		data.loginMessages();
		data.notes();
		data.rules();
		data.afk();
		data.reserve();
		data.randomQuote();
		data.dateformat();
		data.factions();

		getLogger().info(logged + "Enabled!");
	}

	@Override
	public void onDisable() {
		try {
			SLAPI.save(logins, "plugins/TLDCommonlib/logins.dat");
			SLAPI.save(admins, "plugins/TLDCommonlib/reserve.dat");
			SLAPI.save(factions, "plugins/TLDCommonlib/FactionMottos.dat");
			connection.close();
		} catch (Exception e) {
			errorLogger();
			e.printStackTrace();
		}
		getLogger().info("Data has been saved to disk");
		getLogger().info(logged + "Disabled!");
	}

	@Override
	public void onReload() {
		try {
			FetchConfig().load();
			getLogger().info(logged + "reloaded!");
		} catch (ConfigurationException e) {
			getLogger().log(Level.WARNING, "Could not reload configuration!", e);
		}
	}

	public void SetConfig() {
		config = new YamlConfiguration(new File(configPath));
	}

	public Configuration FetchConfig() {
		return config;
	}

	public void errorLogger() {
		getLogger().info(error);
	}

	public Lib instance;

	private void setInstance(Lib plugin) {
		instance = plugin;
	}

	public Lib getInstance() {
		return instance;
	}
}
