package com.slapgaming.slaphomebrew;

import com.earth2me.essentials.Essentials;
import com.slapgaming.slaphomebrew.commands.CommandHandler;
import com.slapgaming.slaphomebrew.commands.TabHandler;
import com.slapgaming.slaphomebrew.controller.ChatChannels;
import com.slapgaming.slaphomebrew.controller.PlayerTracker;
import com.slapgaming.slaphomebrew.listeners.AbstractListener;
import com.slapgaming.slaphomebrew.listeners.player.PlayerCommandListener;
import com.slapgaming.slaphomebrew.listeners.player.PlayerDeathListener;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SlapPlugin extends JavaPlugin {

    public static final String VAULT_PLUGIN = "Vault";

    private static SlapPlugin instance;

    // Plugins
    private Economy economy;
    private Chat chat;
    private Permission permissions;
    private Essentials essentials;
    private LuckPerms luckPerms;


    // Handlers
    private CommandHandler commandHandler;
    private TabHandler tabHandler;

    // Controllers
    private ChatChannels chatChannels;
    private PlayerTracker playerTracker;

    // Listeners

    // Maps
    private Map<UUID, Integer> rainbowPlayerTasks;

    @Override
    public void onEnable() {
        SlapPlugin.instance = this;
        this.setupPlugins();
        this.setupHandlers();
        this.setupControllers();
        this.setupListeners();
        this.setupTrackers();
    }

    private void setupPlugins() {
        // Find the vault plugin
        if (getServer().getPluginManager().getPlugin(SlapPlugin.VAULT_PLUGIN) == null) {
            throw new RuntimeException("Vault is required to run this plugin");
        }

        // Setup vault services
        this.chat = fetchService(Chat.class);
        this.economy = fetchService(Economy.class);
        this.permissions = fetchService(Permission.class);

        // Others
        this.essentials = fetchPlugin(Essentials.class, "Essentials");
        this.luckPerms = fetchService(LuckPerms.class);
    }

    private <T> T fetchPlugin(Class<T> pluginClass, String name) {
        org.bukkit.plugin.Plugin plugin = getServer().getPluginManager().getPlugin(name);
        if (plugin == null) {
            throw new RuntimeException("Missing required plugin " + name);
        }
        if (!pluginClass.isInstance(plugin)) {
            throw new RuntimeException("Plugin is incorrect class: " + plugin.getClass().getName());
        }
        return (T) plugin;
    }


    private <T> T fetchService(Class<T> serviceClass) {
        RegisteredServiceProvider<T> provider = getServer().getServicesManager().getRegistration(serviceClass);
        if (provider == null) {
            throw new RuntimeException("No provider registered for " + serviceClass);
        }

        T service = provider.getProvider();
        if (service == null) {
            throw new RuntimeException("Missing service provider " + serviceClass);
        }

        return service;
    }

    private void setupHandlers() {
        this.commandHandler = new CommandHandler();
        this.tabHandler = new TabHandler();
        this.playerTracker = new PlayerTracker();
    }

    private void setupControllers() {
        this.chatChannels = new ChatChannels();
    }

    private void setupListeners() {
        register(
                //Listeners
//                new AnimalTameListener(horses),
//                new BlockPlaceListener(),
//                new CreatureDeathListener(horses),
//                new CreatureSpawnListener(),
//                new ChunkLoadListener(horses),
//                new ChunkUnloadListener(horses),
//                new DispenseListener(),
//                new DuelArenaListener(duelArena),
//                new EntityChangeBlockListener(),
//                new EntityDamageByEntityListener(horses),
//                new EntityDamageListener(jails),
//                new PlayerChangedWorldListener(lottery, mail),
//                new PlayerChatListener(afk, jails, chatChannels, mention, muteController),
                new PlayerCommandListener(playerTracker),
                new PlayerDeathListener(playerTracker)
//                new PlayerInteractEntityListener(horses),
//                new PlayerInteractListener(jails, spartaPads),
//                new PlayerInventoryEvent(lottery),
//                new PlayerJoinListener(mail, jails, tabController, homes),
//                new PlayerLoginListener(whitelist),
//                new PlayerMoveListener(extras, afk),
//                new PlayerPortalListener(),
//                new PlayerQuitListener(afk, jails, tabController, chatChannels, homes),
//                new PlayerRespawnListener(),
//                new PlayerTabCompleteListener(),
//                new PlayerTeleportListener(jails, afk),
//                new PlayerToggleFlightListener(extras),
//                new PotionListener(),
//                new ProjectileHitListener(),
//                new ProjectileLaunchListener(),
//                new VehicleListener(horses)
        );
    }

    /**
     * Register a Abstract EventListener
     * @param listeners The listeners
     */
    private void register(AbstractListener... listeners) {
        PluginManager pm = getServer().getPluginManager();
        for (AbstractListener listener : listeners) {
            pm.registerEvents(listener, this);
        }
    }

    private void setupTrackers() {
        this.rainbowPlayerTasks = new HashMap<>();
    }


    @Override
    public void onDisable() {
        SlapPlugin.instance = null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String usedAlias, String[] args) {
        return this.commandHandler.handle(sender, command, usedAlias, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return this.tabHandler.handle(sender, command, alias, args);
    }

    public Chat getChat() {
        return chat;
    }

    public Permission getPermissions() {
        return permissions;
    }

    public Economy getEconomy() {
        return economy;
    }

    public Essentials getEssentials() {
        return essentials;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public ChatChannels getChatChannels() {
        return chatChannels;
    }

    public Map<UUID, Integer> getRainbowPlayerTasks() {
        return rainbowPlayerTasks;
    }

    public PlayerTracker getPlayerTracker() {
        return playerTracker;
    }

    public static SlapPlugin getInstance() {
        return instance;
    }
}
