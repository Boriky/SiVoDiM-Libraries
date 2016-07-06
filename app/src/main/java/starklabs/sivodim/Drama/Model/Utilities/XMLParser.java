package starklabs.sivodim.Drama.Model.Utilities;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;

/**
 * Created by Riccardo Rizzo on 31/05/2016.
 */

public class XMLParser {
    // debugging
    static void vDebug(String debugString) {
        Log.v("DomParsing", debugString+"\n");
    }
    // debugging
    static void eDebug(String debugString) {
        Log.e("DomParsing", debugString+"\n");
    }

    // data structure containing parsed data
    private Screenplay parsedData;

    // return data structure with parsed screenplays
    public Screenplay getParsedData() { return parsedData; }

    private void parseCharacter(Node node){
        //NodeList charactersList = ((Element) c).getElementsByTagName("character");
        NodeList charactersList = node.getChildNodes();
        for (int k = 0; k < charactersList.getLength(); k++) {
            Node c1 = charactersList.item(k);
            if (c1.getNodeType() == Node.ELEMENT_NODE) {
                Element characterElem = (Element) c1; // cast
                String name = characterElem.getAttribute("name");
                String avatarPath = characterElem.getAttribute("avatar");
                String voice = characterElem.getAttribute("voice");

                Avatar avatar = new Avatar(avatarPath);

                Character character = new CharacterImpl.CharacterBuilder()
                        .setName(name)
                        .setAvatar(avatar)
                        .setVoice(voice)
                        .build();

                parsedData.addCharacter(character);
            }
        }
    }

    private void parseChapter(Node node){
        //NodeList chaptersList = ((Element) c).getElementsByTagName("chapter");
        NodeList chaptersList = node.getChildNodes();
        for (int k = 0; k < chaptersList.getLength(); k++) {
            Node c1 = chaptersList.item(k);

            vDebug("Verifica lettura nodi interni a capitolo :" + c1.getNodeName());
            vDebug("");

            if (c1.getNodeType() == Node.ELEMENT_NODE) {
                Element chapterElem = (Element) c1; // cast
                String chapterTitle = chapterElem.getAttribute("title");
                vDebug("Titolo capitolo: "+chapterTitle);
                String backgroundPath = chapterElem.getAttribute("background");
                String soundtrackPath = chapterElem.getAttribute("soundtrack");

                Chapter chapter = new ChapterImpl.ChapterBuilder()
                        .setTitle(chapterTitle)
                        .build();

                if(!backgroundPath.equals(""))
                    chapter.setBackground(new Background(backgroundPath));
                if(!soundtrackPath.equals(""))
                    chapter.setSoundtrack(new Soundtrack(soundtrackPath));

                NodeList speechesList = ((Element) c1).getElementsByTagName("speech");
                // tag cased
                for (int z = 0; z < speechesList.getLength(); z++) {
                    Node c2 = speechesList.item(z);
                    Element speechElem = (Element) c2; // cast
                    if(speechElem!=null) {
                        String text = speechElem.getTextContent();
                        String characterName = speechElem.getAttribute("character");
                        String emotion = speechElem.getAttribute("emotion");
                        String soundFxPath = speechElem.getAttribute("soundFx");
                        SoundFx soundFx = new SoundFx(soundFxPath);
                        String audioPath = speechElem.getAttribute("audioPath");

                        Character character = parsedData.getCharacterByName(characterName);

                        Speech speech = new SpeechImpl.SpeechBuilder()
                                .setText(text)
                                .setEmotion(emotion)
                                .setSoundFX(soundFx)
                                .setAudioPath(audioPath)
                                .build();

                        if (character != null) speech.setCharacter(character);
                        if (audioPath != null) speech.setAudioStatus(true);

                        vDebug("SPEECH: "+speech.getText());
                        chapter.addSpeech(speech);
                    }
                }

                parsedData.addChapter(chapter);
            }
        }
    }

    public void parseXml(File file) {
        Document doc;
        try {
            // instance of document from a file
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            // get the root element
            Element root = doc.getDocumentElement();

            // new screenplay object to store parsed data
            String title = root.getAttribute("title");
            String nextSpeechId=root.getAttribute("nextSpeechId");

            // new screenplay object to store parsed data
            parsedData = new ScreenplayImpl(title,Integer.parseInt(nextSpeechId));

            // debug
            vDebug("Root element :" + root.getNodeName());
            vDebug("");

            // get the child elements from root
            NodeList nodes = root.getChildNodes();

            // cycle through every node
            for (int i = 0; i < nodes.getLength(); i++) {
                Node c = nodes.item(i);
                // check if the node is a tag
                vDebug("Verifica lettura nodi PRIMA if :" + c.getNodeName());
                vDebug("");
                if (c.getNodeType() == Node.ELEMENT_NODE) {
                    vDebug("Verifica lettura nodi DOPO if :" + c.getNodeName());
                    vDebug("");
                    // from Node to Element "cast"
                    Element screenplayElem = (Element) c;
                    String nodeName = screenplayElem.getNodeName();

                    // tag cases
                    if (nodeName.equals("characters")) {
                        parseCharacter(c);
                    }
                    if (nodeName.equals("chapters")) {
                        parseChapter(c);
                    }
                }
            }
        } // handle exceptions
        catch (SAXException e) {
            eDebug(e.toString());
        } catch (IOException e) {
            eDebug(e.toString());
        } catch (ParserConfigurationException e) {
            eDebug(e.toString());
        } catch (FactoryConfigurationError e) {
            eDebug(e.toString());
        }
    }

    public void saveXML(File file, Screenplay screenplay) {
        if (screenplay != null) {
            if(!file.exists())
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            Document doc;
            // instance of document from a file
            try {
                doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                // create root element "screenplay"
                Element screenplayElem = doc.createElement("screenplay");
                doc.appendChild(screenplayElem);
                screenplayElem.setAttribute("title",screenplay.getTitle());
                screenplayElem.setAttribute("nextSpeechId",String.valueOf(screenplay.getNextSpeechId().getInteger()));

                // create element "character", father of all "character" elements
                Element charactersElem = doc.createElement("characters");
                screenplayElem.appendChild(charactersElem);

                ListIterator<Character> characterIterator = screenplay.getCharacters().iterator();
                while(characterIterator.hasNext()) {
                    Character character = characterIterator.next();

                    // create element "character" in a loop
                    Element characterElem = doc.createElement("character");
                    characterElem.setAttribute("name", character.getName());
                    characterElem.setAttribute("voice", character.getVoiceID());
                    Avatar avatar=character.getAvatar();
                    if(avatar!=null)
                        characterElem.setAttribute("avatar", avatar.getPath());

                    // append "character" to "characters"
                    charactersElem.appendChild(characterElem);
                }
                // create element "chapters", father of all "chapter" elements
                Element chaptersElem = doc.createElement("chapters");
                screenplayElem.appendChild(chaptersElem);

                Iterator<Chapter> chapterIterator = screenplay.getChapterIterator();
                while(chapterIterator.hasNext()) {
                    Chapter chapter = chapterIterator.next();

                    // create element "chapter" in a loop
                    Element chapterElem = doc.createElement("chapter");
                    chapterElem.setAttribute("title", chapter.getTitle());
                    if(chapter.getSoundtrack()!=null && chapter.getSoundtrack().getAudio()!=null)
                        chapterElem.setAttribute("soundtrack", chapter.getSoundtrack().getAudio().getAbsolutePath());
                    if(chapter.getBackground()!=null && chapter.getBackground().getPath()!=null)
                        chapterElem.setAttribute("background", chapter.getBackground().getPath());

                    Iterator<Speech> speechIterator = chapter.getSpeechIterator();
                    while(speechIterator.hasNext()) {
                        Speech speech = speechIterator.next();
                        if(speech!=null){
                        //create element "speech" in a loop
                        Element speechElem = doc.createElement("speech");
                        speechElem.setAttribute("character", speech.getCharacter().getName());
                        speechElem.setAttribute("emotion", speech.getEmotion());
                            String audioPath=speech.getAudioPath();
                        if(audioPath!=null)
                            speechElem.setAttribute("audioPath", audioPath);
                        speechElem.setTextContent(speech.getText());

                        // debug
                        System.out.println(speech.getText());

                        // append "speech" to "chapter"
                        chapterElem.appendChild(speechElem);
                        }
                    }
                    // append "chapter" to "chapters"
                    chaptersElem.appendChild(chapterElem);
                }

                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");


                    // send DOM to file
                    transformer.transform(new DOMSource(doc),
                            new StreamResult(new FileOutputStream(file)));


                } catch (TransformerException te) {
                    System.out.println(te.getMessage());
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
            } catch (ParserConfigurationException e) {
                eDebug(e.toString());
            } catch (FactoryConfigurationError e) {
                eDebug(e.toString());
            }
        }
    }
}