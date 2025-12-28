package repeatedsoundcanceler;

import net.runelite.api.events.SoundEffectPlayed;

public class FilterableSoundEffectPlayed implements FilterableSoundEffect {

    private final SoundEffectPlayed sfx;

    public FilterableSoundEffectPlayed(SoundEffectPlayed soundEffectPlayed) {
        this.sfx = soundEffectPlayed;
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
