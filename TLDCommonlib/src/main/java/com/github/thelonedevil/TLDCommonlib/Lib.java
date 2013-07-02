package com.github.thelonedevil.TLDCommonlib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;

import org.spout.api.UnsafeMethod;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.plugin.Plugin;
import org.spout.api.util.config.Configuration;
import org.spout.api.util.config.yaml.YamlConfiguration;

public class Lib extends Plugin {

	public final String configPath = "plugins/TLDCommonLib/config.yml";
	public YamlConfiguration config;
	public String logged = "Plugin has been ";
	public String error = "An error has happened...... incoming stack trace....";
	public List<String> Quotes;
	public String testQuote;
	public File libFolder = new File("plugins/TLDCommonLib/");
	public int reserve;
	public final Map<Integer, List<String>> rules = new HashMap<Integer, List<String>>();
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
	private Data data = new Data(this);
	private DataBase sql = new DataBase(this);
	public static Statement statement;
	public static Connection connection;
	public HashMap<String, String> factions = new HashMap<String, String>();

	@Override
	public void onLoad() {
		setInstance(this);
		libFolder.mkdirs();
		try {
			File f = new File(configPath);
			if (!f.exists()) {
				extract(configPath, "config.yml");
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
		getLogger().info(logged + "Loaded!");

	}

	void extract(String path, String name) throws IOException {
		JarFile jarfile = new JarFile(getFile());
		Enumeration<JarEntry> enu = jarfile.entries();
		while (enu.hasMoreElements()) {
			JarEntry je = enu.nextElement();
			if (je.getName().equalsIgnoreCase(name)) {
				File fl = new File(path);
				if (je.isDirectory()) {
					continue;
				} else if (!je.isDirectory()) {
					if (!fl.exists()) {
						fl.getParentFile().mkdirs();
						fl = new File(path);
						fl.createNewFile();
						getLogger().log(Level.INFO, "Generating config.yml...");
						InputStream is = jarfile.getInputStream(je);
						FileOutputStream fo = new FileOutputStream(path);
						while (is.available() > 0) {
							fo.write(is.read());
						}
						fo.close();
						is.close();
					}
				}
			}
		}
		jarfile.close();
	}

	@Override
	@UnsafeMethod
	public void onEnable() {
		List<Plugin> plugins = getEngine().getPluginManager().getPlugins();
		List<String> names = new ArrayList<String>();
		int i = 0;
		int s = plugins.size();
		while (i < s) {
			names.add(plugins.get(i).getName());
			i++;
		}
		if (names.contains("TLDFactions")) {
			try {
				File file = new File("plugins/TLDCommonLib/data.sqlite");
				file.createNewFile();
				connection = sql.connect("plugins/TLDCommonLib/data.sqlite");
				statement = sql.state(connection, 30);
				sql.createTable(statement, "Factions", "Player", "string", "Faction", "string", "Rank", "string");
				data.factionsdat();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		getEngine().getEventManager().registerEvents(new EListener(this), this);
		if(names.contains("TLDWelcomer")){
			data.loginMessages();
		}
		if(names.contains("TLDNotes")){
			data.notes();	
		}
		if(names.contains("TLDRules")){
			data.rules();
		}
		if(names.contains("TLDAfk")){
			data.afk();	
		}
		if(names.contains("TLDReserve")){
			data.reserve();	
		}
		if(names.contains("TLDQuotes")){
			data.randomQuote();
		}
		getLogger().info(logged + "Enabled!");
	}

	@Override
	@UnsafeMethod
	public void onDisable() {
		try {
			SLAPI.save(logins, "plugins/TLDCommonlib/logins.dat");
			SLAPI.save(admins, "plugins/TLDCommonlib/reserve.dat");
			SLAPI.save(factions, "plugins/TLDCommonlib/FactionMottos.dat");
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

	private static Lib instance;

	private static void setInstance(Lib plugin) {
		instance = plugin;
	}

	public static Lib getInstance() {
		return instance;
	}
}
