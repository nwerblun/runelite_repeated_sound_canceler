package repeatedsoundcanceler;

import net.runelite.api.events.AreaSoundEffectPlayed;

public class FilterableAreaSoundEffectPlayed implements FilterableSoundEffect{
    
    private final AreaSoundEffectPlayed sfx;

    public FilterableAreaSoundEffectPlayed(AreaSoundEffectPlayed areaSoundEffectPlayed) {
        this.sfx = areaSoundEffectPlayed;
    }

    @Override
    public void consume() {
        this.sfx.consume();
    }

    @Override
    public int getSoundId() {
        return this.sfx.getSoundId();
    }
}
