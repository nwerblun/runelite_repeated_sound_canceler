package repeatedsoundcanceler;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.AreaSoundEffectPlayed;
import net.runelite.api.events.SoundEffectPlayed;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Filter;
import java.util.stream.Collectors;

@Slf4j
@PluginDescriptor(
	name = "Repeated Sound Canceler"
)
public class RepeatedSoundCancelerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RepeatedSoundCancelerConfig config;

    private int tickToCheck;
    private List<Integer> idsSeenThisTick;
    private Set<Integer> blacklistIDs, whitelistIDs;

	@Override
	protected void startUp() throws Exception
	{
        tickToCheck = 0;
        idsSeenThisTick = new ArrayList<Integer>();
        blacklistIDs = configListToSet(config.getBlacklist());
        whitelistIDs = configListToSet(config.getWhitelist());
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged configChanged) {
        blacklistIDs = configListToSet(config.getBlacklist());
        whitelistIDs = configListToSet(config.getWhitelist());
    }

    @Subscribe
    public void onAreaSoundEffectPlayed(AreaSoundEffectPlayed areaSoundEffectPlayed) {
        // Wrap it in an interface for function reuse
        FilterableSoundEffect played = new FilterableSoundEffect() {
            @Override
            public void consume() {
                areaSoundEffectPlayed.consume();
            }

            @Override
            public int getSoundId() {
                return areaSoundEffectPlayed.getSoundId();
            }
        };

        filterDupeSounds(played);
    }

    @Subscribe
    public void onSoundEffectPlayed(SoundEffectPlayed soundEffectPlayed) {
        // Wrap in interface for function reuse
        FilterableSoundEffect played = new FilterableSoundEffect() {
            @Override
            public void consume() {
                soundEffectPlayed.consume();
            }

            @Override
            public int getSoundId() {
                return soundEffectPlayed.getSoundId();
            }
        };

        filterDupeSounds(played);
    }

	@Provides
    RepeatedSoundCancelerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RepeatedSoundCancelerConfig.class);
	}

    private static Set<Integer> configListToSet(String configList) {
        return Arrays.stream(configList.split(","))
                .map(String::trim)
                .filter(NumberUtils::isParsable)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private void filterDupeSounds(FilterableSoundEffect sound) {
        int currTickCount = client.getTickCount();
        // Or condition is if someone somehow leaves their client on for maxint ticks I guess.
        // Reset sounds seen if we start a new tick.
        if (currTickCount > tickToCheck || currTickCount == 0) {
            tickToCheck = currTickCount;
            idsSeenThisTick.clear();
        }

        boolean filter = false;
        if (config.getMode().equals(RepeatedSoundCancelerMode.BLACKLIST_MODE)) {
            filter = blacklistIDs.contains(sound.getSoundId());
        } else if (config.getMode().equals(RepeatedSoundCancelerMode.WHITELIST_MODE)) {
            filter = !whitelistIDs.contains(sound.getSoundId());
        }

        if (filter) {
            if (!idsSeenThisTick.contains(sound.getSoundId())) {
                idsSeenThisTick.add(sound.getSoundId());
            } else {
                sound.consume();
            }
        }
    }
}
