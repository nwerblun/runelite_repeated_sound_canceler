# Repeated Sound Canceler
When multiple area sounds (like ice barrage hitting a clump of enemies) 
play on the same tick, they stack up and play at the same time. When this happens, it produces
a sound effect that is much louder than normal. This plugin will check if any area 
sounds are played multiple times on the same tick and filter out all but one instance 
of that sound to keep volume levels more consistent.

Note: as of now only area sounds are supported. If there is a desire and reasonable application for
it, I might add ambient sounds or regular sounds. The biggest offenders of this issue are usually
area sounds.

## Features

### Whitelist Mode
All area sounds will be filtered except those listed in the whitelist. Use this if you generally
want to filter sounds except for a couple.

Add sound IDs to the whitelist separated by a comma. Take care to get the proper area sound that
is repeated, such as the hit noise of ice barrage and not the cast noise. You can find most
sound IDs [here](https://oldschool.runescape.wiki/w/List_of_sound_IDs), or make use of the
[visual sounds plugin on RuneLite](https://runelite.net/plugin-hub/show/visual-sounds)

### Blacklist Mode
No area sounds will be filtered, except those in the blacklist. Use this if you want a mostly
standard experience but just want a couple annoying sounds to be less loud at times.

Add sound IDs to the blacklist separated by a comma. See the above links for help in finding IDs.