package pro.falco.partytop;

import com.gmail.nossr50.datatypes.party.Party;
import com.gmail.nossr50.party.PartyManager;
import com.gmail.nossr50.util.text.StringUtils;
import org.jetbrains.annotations.NotNull;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class PartyTopCommand implements Listener, CommandExecutor {
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            final List<Party> parties = PartyManager.getParties();

            switch (args.length) {
                case 0:
                    print(1, sender);
                    return true;
                case 1:
                    if (StringUtils.isInt(args[0])) {
                        print(Math.abs(Integer.parseInt(args[0])), sender);
                        return true;
                    }
            }
        }
        return false;
    }

    private void print(int page, @NotNull CommandSender sender) {
        final List<Party> parties = PartyManager.getParties();
        parties.sort(Comparator.comparingInt(Party::getLevel).reversed());

        List<Party> partyList = new ArrayList<Party>();
        IntStream.range(0, parties.size()).forEach(index -> {
            partyList.add(parties.get(index));
        });

        sender.sendMessage("§e--mcMMO §9Party §eLeaderboard--");
        partyList.forEach((party) -> {
            sender.sendMessage("#. §a" + party.getName() + " - §f" + party.getLevel());
        });
        sender.sendMessage("§6Tip: Use §c/party info §6to see all of your party stats!");
    }
}
