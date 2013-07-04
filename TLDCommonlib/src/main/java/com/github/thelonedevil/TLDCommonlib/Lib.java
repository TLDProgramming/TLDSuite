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
	public static YamlConfiguration config;
	public static String logged = "Plugin has been ";
	public static String error = "An error has happened...... incoming stack trace....";
	public static List<String> Quotes;
	public String testQuote;
	public File libFolder = new File("plugins/TLDCommonLib/");
	public static int reserve;
	public static final Map<Integer, List<String>> rules = new HashMap<Integer, List<String>>();
	public static boolean onjoin = true;
	public static final List<String> onJoin = new ArrayList<String>();
	public static String firstlogin;
	public static String otherlogin;
	public static HashMap<String, Integer> logins = new HashMap<String, Integer>();
	public static HashMap<String, Boolean> afk = new HashMap<String, Boolean>();
	public static int Length;
	public static int amount;
	public static HashMap<String, Boolean> Namount = new HashMap<String, Boolean>();
	public static long idletime;
	public static List<String> admins = new ArrayList<String>();
	public static int reserved;
	private Data data = new Data(this);
	private DataBase sql = new DataBase(this);
	public static Statement statement;
	public static Connection connection;
	public static HashMap<String, String> factions = new HashMap<String, String>();

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
		getEngine().getEventManager().registerEvents(new EListener(this), this);
		data.loginMessages();
		data.notes();
		data.rules();
		data.afk();
		data.reserve();
		data.randomQuote();

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

	public static Configuration FetchConfig() {
		return config;
	}

	public void errorLogger() {
		getLogger().info(error);
	}

	public static Lib instance;

	private static void setInstance(Lib plugin) {
		instance = plugin;
	}

	public static Lib getInstance() {
		return instance;
	}
}
