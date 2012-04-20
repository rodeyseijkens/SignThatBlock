package nl.rodey.SignThatBlock;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignThatBlockMain extends JavaPlugin {
	private Logger log = Logger.getLogger("Minecraft");
	
	private FileConfiguration config;
	public final SignThatBlockPlayerListener playerListener = new SignThatBlockPlayerListener(this);
	
	public Boolean debug = false;
	public String definedBlocks;
	
	ArrayList<String> registeredPlayers = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		
		log.info("["+getDescription().getName()+"] version "+getDescription().getVersion()+" loading...");
		
		log = getServer().getLogger();
	
        // Load configuration
        loadConfig();
       
        // Register Player Listeners
        registerEvents();
        
        log.info("["+getDescription().getName()+"] version "+getDescription().getVersion()+" is enabled");
	}

	@Override
	public void onDisable() {		
		log.info("["+getDescription().getName()+"] version "+getDescription().getVersion()+" is disabled!");
	}	

	public void registerEvents()
    {		
		PluginManager pm = this.getServer().getPluginManager();
		
		/* Register Player Events */
		pm.registerEvents(playerListener, this);
    }
	
	public void loadConfig(){
		// Ensure config directory exists
		File configDir = this.getDataFolder();
		if (!configDir.exists())
			configDir.mkdir();

		// Check for existance of config file
		File configFile = new File(this.getDataFolder().toString() + "/config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		
		// Adding Variables
		if(!config.contains("Debug"))
		{
			config.addDefault("Debug", false);
	        config.addDefault("Blocks", "23,25,26,54,58,61,62,84,116,118,117");
		}
        
        // Loading the variables from config
    	debug = (Boolean) config.get("Debug");
    	definedBlocks = (String) config.get("Blocks");
 
        config.options().copyDefaults(true);
        try {
            config.save(configFile);
        } catch (IOException ex) {
            Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + configFile, ex);
        }
    }
	
    public void reportError(Exception e, String message, boolean dumpStackTrace)
    {
        PluginDescriptionFile pdfFile = this.getDescription();
        log.severe("["+getDescription().getName()+"] " + pdfFile.getVersion() + " - " + message);
        if (dumpStackTrace)
            e.printStackTrace();
    }
}