package com.example.obidi.miwok2;

/**
 * Created by USER on 10/17/2018.
 */
public class Word {
    private int miwokTranslationStringResourceId;
    private int defaultTranslationStringResourceId;
    // imageResourceId has a default value of -1
    private int imageResourceId = -1;
    private int audioResourceId;

    public Word(int miwokTranslationStringResourceId, int defaultTranslationStringResourceId, int imageResourceId, int audioResourceId) {
        this.miwokTranslationStringResourceId = miwokTranslationStringResourceId;
        this.defaultTranslationStringResourceId = defaultTranslationStringResourceId;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
    }

    public Word(int miwokTranslationStringResourceId, int defaultTranslationStringResourceId, int audioResourceId) {
        this.miwokTranslationStringResourceId = miwokTranslationStringResourceId;
        this.defaultTranslationStringResourceId = defaultTranslationStringResourceId;
        this.audioResourceId = audioResourceId;
    }

    public int getmiwokTranslationStringResourceId() {
        return miwokTranslationStringResourceId;
    }

    public int getdefaultTranslationStringResourceId() {
        return defaultTranslationStringResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }
}
