package pro.falco.partytop;

import java.util.Objects;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private final PartyTopCommand partyTopCommandCommand = new PartyTopCommand();

    public void onEnable() {
        this.getLogger().info("mcMMO extension party top started");
        Objects.requireNonNull(getCommand("partytop")).setExecutor(partyTopCommandCommand);
    }
}
