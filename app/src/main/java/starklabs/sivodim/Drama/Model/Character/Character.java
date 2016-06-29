package starklabs.sivodim.Drama.Model.Character;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public interface Character {
    /**
     * Set the avatar to the character
     * @param avatar the Avatar to set
     */
    void setAvatar(Avatar avatar);

    /**
     * set the voice to the character
     * @param voiceID the ID of the voice to set
     */
    void setVoice(String voiceID);

    /**
     * set the name of the character
     * @param name the name to set
     */
    void setName(String name);

    /**
     * Gives the avatar of the character
     * @return
     */
    Avatar getAvatar();

    /**
     * Gives the ID of the voice of the character
     * @return
     */
    String getVoiceID();

    /**
     * Gives the name of the character
     * @return
     */
    String getName();

    /**
     * Returns a copy of the character
     * @return
     */
    CharacterImpl clone();
}
