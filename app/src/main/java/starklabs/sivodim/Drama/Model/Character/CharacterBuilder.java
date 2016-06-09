package starklabs.sivodim.Drama.Model.Character;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public interface CharacterBuilder {
    void setAvatar(Avatar avatar);
    void setVoice(String voiceID);
    void setName(String name);
    Character getResult();
}
