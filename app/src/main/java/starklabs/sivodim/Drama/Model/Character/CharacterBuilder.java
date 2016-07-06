package starklabs.sivodim.Drama.Model.Character;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public interface CharacterBuilder {

    /**
     * Set the avatar for the character
     * @param avatar the avatar to set
     */
    void setAvatar(Avatar avatar);

    /**
     * set the voice for the character
     * @param voiceID the ID of the voice to set
     */
    void setVoice(String voiceID);

    /**
     * set the name for the character
     * @param name the name to set
     */
    void setName(String name);

    /**
     * Builds the character
     * @return
     */
    Character getResult();
}
