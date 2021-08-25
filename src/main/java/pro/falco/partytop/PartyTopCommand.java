package pro.falco.partytop;

import com.gmail.nossr50.datatypes.party.Party;
import com.gmail.nossr50.party.PartyManager;
import org.jetbrains.annotations.NotNull;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Comparator;
import java.util.List;

public class PartyTopCommand implements Listener, CommandExecutor {
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            final List<Party> parties = PartyManager.getParties();

            parties.sort(Comparator.comparingInt(Party::getLevel).reversed());

            parties.forEach((party) -> {
                sender.sendMessage("Sorted: " + party.getName() + ", level: " + party.getLevel());
            });

            return true;
        }

        return false;
    }
}