# Repeated Sound Canceler
## UPDATE LIST
### V1.2
* Refactor based on maintainer suggestions. No feature changes.
### V1.1
* Refactored code to work with both regular sfx and area sfx. Chinning maniacal monkeys should be safe for your ears
now.

## Description
When multiple sounds (like ice barrage hitting a clump of enemies) 
play on the same tick, they stack up and play at the same time. When this happens, it produces
a sound effect that is much louder than normal. This plugin will check if any area 
sounds are played multiple times on the same tick and filter out all but one instance 
of that sound to keep volume levels more consistent.

NOTE: Ambient sound effects are not currently filtered. As far as I know, they are not really affected by this 
problem, but they could be added if there is a valid use case.

## Features

### Whitelist Mode
All sounds will be filtered except those listed in the whitelist. Use this if you generally
want to filter sounds except for a couple.

Add sound IDs to the whitelist separated by a comma. Take care to get the proper area sound that
is repeated, such as the hit noise of ice barrage and not the cast noise. You can find most
sound IDs [here](https://oldschool.runescape.wiki/w/List_of_sound_IDs), or make use of the
[visual sounds plugin on RuneLite](https://runelite.net/plugin-hub/show/visual-sounds)

### Blacklist Mode
No area sounds will be filtered, except those in the blacklist. Use this if you want a mostly
standard experience but just want a couple annoying sounds to be less loud at times.

Add sound IDs to the blacklist separated by a comma. See the above links for help in finding IDs.
