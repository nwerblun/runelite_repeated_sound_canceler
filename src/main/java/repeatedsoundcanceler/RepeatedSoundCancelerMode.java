package repeatedsoundcanceler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RepeatedSoundCancelerMode {

    WHITELIST_MODE("Whitelist"),
    BLACKLIST_MODE("Blacklist");

    private final String option;

    @Override
    public String toString() {
        return option;
    }
}
