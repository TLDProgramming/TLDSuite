package com.github.thelonedevil.TLDCommonlib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;

import org.spout.api.Server;
import org.spout.cereal.config.ConfigurationNode;
import org.yaml.snakeyaml.Yaml;

public class Data {

    private Lib plugin;

    public Data(Lib instance) {
	this.plugin = instance;
    }

    void load() {
	loginMessages();
	notes();
	rules();
	afk();
	reserve();
	randomQuote();
	dateformat();
	factions();
	allowedyaml();
	dobyaml();
    }

    void save() throws Exception {
	if (!plugin.logins.isEmpty()) {
	    SLAPI.save(plugin.logins, "plugins/TLDCommonlib/logins.dat");
	}
	if (!plugin.admins.isEmpty()) {
	    SLAPI.save(plugin.admins, "plugins/TLDCommonlib/reserve.dat");
	}
	if (!plugin.factions.isEmpty()) {
	    SLAPI.save(plugin.factions, "plugins/TLDCommonlib/FactionMottos.dat");
	}

	allowedyamls();

	dobyamls();

    }

    /**
     *
     * @param path
     * @param name
     * @throws IOException
     */
    void extract(String path, String name) throws IOException {
	JarFile jarfile = new JarFile(plugin.getFile());
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
			plugin.getLogger().log(Level.INFO, "Generating " + name);
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

    public void loginMessages() {
	plugin.firstlogin = plugin.FetchConfig().getNode("Logins.First").getString();
	plugin.otherlogin = plugin.FetchConfig().getNode("Logins.Other").getString();
	loginsdat();
    }

    public void rules() {
	ruleCheck();
	rulesOnJoin();
    }

    public void notes() {
	plugin.Length = plugin.FetchConfig().getNode("Notes.length").getInt();
	plugin.amount = plugin.FetchConfig().getNode("Notes.amount").getInt();
    }

    public void afk() {
	plugin.idletime = plugin.FetchConfig().getNode("AFK.IdleTime").getInt() * 60000;
    }

    public void reserve() {
	reservedat();
	plugin.reserve = plugin.admins.size();
	plugin.reserved = ((Server) plugin.getEngine()).getMaxPlayers() - (plugin.reserve);

    }

    public void factions() {
	factionsdat();
	claimsdat();
    }

    public void dateformat() {
	plugin.dateformat = plugin.FetchConfig().getNode("DateFormat").getString("DD/MM/YYYY");

    }

    public void randomQuote() {
	try {
	    plugin.Quotes = (plugin.FetchConfig().getNode("Quotes").getStringList());
	    if (plugin.Quotes != null) {
		plugin.testQuote = plugin.Quotes.get(new Random().nextInt(plugin.Quotes.size()));
		if (plugin.testQuote != null) {
		    plugin.getLogger().info("Quotes loaded");
		} else {
		    plugin.getLogger().info("Quotes not loaded");
		}
	    }
	} catch (IllegalArgumentException e) {
	    plugin.getLogger().info("Quotes missing, creating default config.yml");
	}

    }

    public void ruleCheck() {
	ConfigurationNode node = plugin.FetchConfig().getNode("Rules");
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
	if (plugin.FetchConfig().getNode("onPlayerJoin.enabled").getBoolean() == true) {
	    plugin.onjoin = true;
	    ConfigurationNode node = plugin.FetchConfig().getNode("onPlayerJoin.rules");
	    List<String> list = node.getStringList();
	    if (list != null) {
		plugin.onJoin.addAll(list);
	    }
	    plugin.onJoin.add("To see the rest of the rules use /rules 1");
	    plugin.getLogger().info("Player join rules are loaded");
	} else {
	    plugin.onjoin = false;
	    plugin.getLogger().info("Rules displayed on player join has been disabled");
	}
    }

    @SuppressWarnings("unchecked")
    void reservedat() {
	File afkdat = new File("plugins/TLDCommonlib/reserve.dat");
	if (afkdat.exists() && afkdat.canRead()) {
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
	if (loginsdat.exists() && loginsdat.canRead()) {
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
	if (afkdat.exists() && afkdat.canRead()) {
	    try {
		plugin.factions = (HashMap<String, String>) SLAPI.load("plugins/TLDCommonlib/FactionMottos.dat");
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

    @SuppressWarnings("unchecked")
    void claimsdat() {
	File afkdat = new File("plugins/TLDCommonlib/FactionClaims.dat");
	if (afkdat.exists() && afkdat.canRead()) {
	    try {
		plugin.factionsclaims = (HashMap<String, Integer>) SLAPI.load("plugins/TLDCommonlib/FactionClaims.dat");
	    } catch (Exception e) {
		plugin.errorLogger();
		e.printStackTrace();
	    }
	    plugin.getLogger().info("Faction Motto data has been Loaded from disk");
	    try {
		SLAPI.save(plugin.factionsclaims, "plugins/TLDCommonlib/FactionClaims.dat");
	    } catch (Exception e) {
		plugin.errorLogger();
		e.printStackTrace();
	    }
	} else if (!afkdat.exists()) {
	    plugin.getLogger().info("FactionClaims.dat is missing... creating...");
	    try {
		plugin.getDataFolder().mkdirs();
		afkdat.createNewFile();
	    } catch (IOException e) {
		plugin.errorLogger();
		e.printStackTrace();
	    }
	    plugin.getLogger().info("FactionClaims.dat has been created");
	}

    }

    @SuppressWarnings("unchecked")
    void dobyaml() {
	File dobyaml1 = new File(plugin.libFolder + "/DOB.yml");
	if (dobyaml1.exists()) {
	    try {
		InputStream input = new FileInputStream(dobyaml1);
		Yaml yaml = new Yaml();
		plugin.DOB = (HashMap<String, Calendar>) yaml.load(input);
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    }
	}
    }

    @SuppressWarnings("unchecked")
    void allowedyaml() {
	File allowedyaml1 = new File(plugin.libFolder + "/allowed.yml");
	if (allowedyaml1.exists()) {
	    try {
		InputStream input = new FileInputStream(allowedyaml1);
		Yaml yaml = new Yaml();
		plugin.allowed = (HashMap<String, Boolean>) yaml.load(input);
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    }
	}
    }

    void dobyamls() {
	File dobyaml1 = new File(plugin.libFolder + "/DOB.yml");
	Yaml yaml = new Yaml();
	String output = yaml.dump(plugin.DOB);
	try {
	    plugin.libFolder.mkdirs();
	    dobyaml1.createNewFile();
	    PrintStream out = new PrintStream("plugins/AgeChecker/DOB.yml");
	    out.print(output);
	    out.close();
	} catch (FileNotFoundException e) {
	    plugin.errorLogger();
	    e.printStackTrace();
	} catch (IOException e) {
	    plugin.errorLogger();
	    e.printStackTrace();
	}
    }

    void allowedyamls() {
	File allowedyaml1 = new File(plugin.libFolder + "/allowed.yml");
	Yaml yaml = new Yaml();
	String output = yaml.dump(plugin.allowed);
	try {
	    plugin.libFolder.mkdirs();
	    allowedyaml1.createNewFile();
	    PrintStream out = new PrintStream("plugins/AgeChecker/allowed.yml");
	    out.print(output);
	    out.close();
	} catch (FileNotFoundException e) {
	    plugin.errorLogger();
	    e.printStackTrace();
	} catch (IOException e) {
	    plugin.errorLogger();
	    e.printStackTrace();
	}
    }
}
