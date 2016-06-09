package starklabs.sivodim.Drama.Model.Character;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public class CharacterImpl implements Character {
    private String name;
    private Avatar avatar;
    private String voiceID;

    public static class CharacterBuilder {
        // required parameters
        private String nameB;

        // optional parameters
        private Avatar avatarB;
        private String voiceIDB;

        // setter
        public CharacterBuilder setName(String name) {
            this.nameB = name;
            return this;
        }

        public CharacterBuilder setAvatar(Avatar avatar) {
            this.avatarB = avatar;
            return this;
        }

        public CharacterBuilder setVoice(String voiceID) {
            this.voiceIDB = voiceID;
            return this;
        }

        public CharacterImpl build() {
            if(nameB!=null) {
                return new CharacterImpl(this);
            }
            return null;
        }
    }

    private CharacterImpl(CharacterBuilder builder) {
        // required parameters
        this.name = builder.nameB;

        // optional parameters
        this.avatar = builder.avatarB;
        this.voiceID = builder.voiceIDB;
    }

    @Override
    public CharacterImpl clone(){
        return this.clone();
    }

    // setter
    @Override
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public void setVoice(String voiceID) {
        this.voiceID = voiceID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    // getter
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Avatar getAvatar() {
        return this.avatar;
    }

    @Override
    public String getVoiceID() { return this.voiceID; }
}
