package repeatedsoundcanceler;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("repeatedsoundcanceler")
public interface RepeatedSoundCancelerConfig extends Config
{
    @ConfigSection(
        position = 1,
        name = "General Settings",
        description = "General settings"
    )
    String generalSettingsSection = "General Settings";

	@ConfigItem(
        position = 2,
		keyName = "mode",
		name = "Filtering Mode",
		description = "How to cancel repeated sounds.\nWhitelist mode: All sounds are filtered except those" +
                "in the whitelist.\nBlacklist mode: Only sounds in the blacklist are filtered, all other sounds" +
                "are treated normally.",
        section = generalSettingsSection
	)
	default RepeatedSoundCancelerMode getMode() { return RepeatedSoundCancelerMode.WHITELIST_MODE; }

    @ConfigSection(
        position = 3,
        name = "Whitelist",
        description = "Whitelist settings"
    )
    String whitelistSettings = "Whitelist Settings";

    @ConfigItem(
        position = 4,
        keyName = "whitelistList",
        name = "Sound ID Whitelist",
        description = "List of area sound IDs separated by commas to be used when in whitelist mode.",
        section = whitelistSettings
    )
    default String getWhitelist() { return ""; }

    @ConfigSection(
        position = 5,
        name = "Blacklist",
        description = "Blacklist settings"
    )
    String blacklistSettings = "Blacklist Settings";

    @ConfigItem(
        position = 6,
        keyName = "blacklistList",
        name = "Sound ID Blacklist",
        description = "List of area sound IDs separated by commas to be used when in blacklist mode. By default" +
                "ancient spell barrage hit noises are included",
        section = blacklistSettings
    )
    default String getBlacklist() { return "102,168,175,180"; }
}
