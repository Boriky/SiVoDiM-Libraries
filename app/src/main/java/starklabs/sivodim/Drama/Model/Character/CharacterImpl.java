package starklabs.sivodim.Drama.Model.Character;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public class CharacterImpl implements Character {
    /**
     * String that represents the name of a character
     */
    private String name;
    /**
     * Avatar object that represents the avatar image associated to a Character
     */
    private Avatar avatar;
    /**
     * String that represents the voice associated to a Character (voices are created in Libraries application)
     */
    private String voiceID;

    /* ----- BUILDER DESIGN PATTERN ----- */

    /**
     * SpeechBuilder: internal static class for building Speech objects (BUILDER DESIGN PATTERN)
     */
    public static class CharacterBuilder {
        // required parameters
        /**
         * Builder name attribute
         */
        private String nameB;

        // optional parameters
        /**
         * Builder avatar attribute
         */
        private Avatar avatarB;
        /**
         * Builder voice attribute
         */
        private String voiceIDB;

        // setter

        /**
         * Set the Character's name
         * @param name
         * @return
         */
        public CharacterBuilder setName(String name) {
            this.nameB = name;
            return this;
        }

        /**
         * Set the Character's avatar
         * @param avatar
         * @return
         */
        public CharacterBuilder setAvatar(Avatar avatar) {
            this.avatarB = avatar;
            return this;
        }

        /**
         * Set the Character's voice
         * @param voiceID
         * @return
         */
        public CharacterBuilder setVoice(String voiceID) {
            this.voiceIDB = voiceID;
            return this;
        }

        /**
         * Build che Character
         * @return
         */
        public CharacterImpl build() {
            if(nameB!=null) {
                return new CharacterImpl(this);
            }
            return null;
        }
    }

    /* ----- CONSTRUCTORS ----- */

    /**
     * Create object with Builder
     * @param builder
     */
    private CharacterImpl(CharacterBuilder builder) {
        // required parameters
        this.name = builder.nameB;

        // optional parameters
        this.avatar = builder.avatarB;
        this.voiceID = builder.voiceIDB;
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Clone the Character object (DEEP COPY)
     * @return
     */
    @Override
    public CharacterImpl clone(){
        return this.clone();
    }

    /* ----- SETTER METHODS ----- */

    /**
     * Edit existing avatar or set new one
     * @param avatar the Avatar to set
     */
    @Override
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    /**
     * Edit existing voice or set new one
     * @param voiceID the ID of the voice to set
     */
    @Override
    public void setVoice(String voiceID) {
        this.voiceID = voiceID;
    }

    /**
     * Edit existing name or set new one
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /* ----- GETTER ----- */

    /**
     * Return the Character's name
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Return the Character's avatar
     * @return
     */
    @Override
    public Avatar getAvatar() {
        return this.avatar;
    }

    /**
     * Return the Character's voice
     * @return
     */
    @Override
    public String getVoiceID() { return this.voiceID; }
}
