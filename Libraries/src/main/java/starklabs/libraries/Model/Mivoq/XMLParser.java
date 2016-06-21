package starklabs.libraries.Model.Mivoq;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by AlbertoAndriolo on 21/06/2016.
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

    private ArrayList<MivoqVoice> parsedData;

    public ArrayList<MivoqVoice> getParsedVoice(){
        return parsedData;
    }

    public void parseXML(File file){
        Document doc;
        try {
            // instance of document from a file
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            // get the root element
            Element root = doc.getDocumentElement();

            // new MivoqVoice object to store parsed data
            String title = root.getAttribute("title");
            // new MivoqVoice List object to store parsed data
            parsedData = new ArrayList<MivoqVoice>();

            // debug
            vDebug("Root element :" + root.getNodeName());
            vDebug("");

            // get the child elements from root
            NodeList voiceNodes = root.getChildNodes();

            // cycle through every node
            for (int i = 0; i < voiceNodes.getLength(); i++) {
                Node node = voiceNodes.item(i);
                // check if the node is a tag
                vDebug("Verifica lettura nodi PRIMA if :" + node.getNodeName());
                vDebug("");
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    vDebug("Verifica lettura nodi DOPO if :" + node.getNodeName());
                    vDebug("");
                    // from Node to Element "cast"
                    Element voiceElem = (Element) node;
                    String nodeName = voiceElem.getNodeName();

                    // tag cases
                    if (nodeName.equals("voice")) {
                        String name = voiceElem.getAttribute("name");
                        String voiceName = voiceElem.getAttribute("voiceName");
                        String gender = voiceElem.getAttribute("gender");
                        String language = voiceElem.getAttribute("language");

                        MivoqVoice mivoqVoice = new MivoqVoice(name, voiceName, new Language(language));
                        mivoqVoice.setGender(gender);

                        NodeList effectNodes = node.getChildNodes();

                        for (int j = 0; j < effectNodes.getLength(); j++) {
                            Node effect = effectNodes.item(j);
                            if (effect.getNodeType() == Node.ELEMENT_NODE) {
                                Element effectElem = (Element) effect;
                                String effectName = effectElem.getAttribute("name");
                                String effectValue = effectElem.getAttribute("value");

                                Effect voiceEffect = new EffectImpl(effectName);
                                voiceEffect.setValue(effectValue);

                                mivoqVoice.setEffect(voiceEffect);
                            }
                        }
                        parsedData.add(mivoqVoice);
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

    public void saveXML(File file, ArrayList<MivoqVoice> voiceList){
        System.out.println("FUNZIONE SAVE");
        if (voiceList != null) {
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

                // create root element "voices"
                Element voicesElem = doc.createElement("voices");
                doc.appendChild(voicesElem);

                for(int i=0; i<voiceList.size(); i++){
                    Element voiceElem = doc.createElement("voice");
                    voicesElem.appendChild(voiceElem);
                    MivoqVoice tempVoice= voiceList.get(i);

                    voiceElem.setAttribute("name", tempVoice.getName());
                    voiceElem.setAttribute("voiceName", tempVoice.getVoiceName());
                    voiceElem.setAttribute("gender", tempVoice.getGender());
                    voiceElem.setAttribute("language", tempVoice.getLanguage());

                    ArrayList<Effect> effectList = tempVoice.getEffects();
                    for(int j=0; j<effectList.size(); j++){
                        Element effectElem = doc.createElement("effect");
                        voiceElem.appendChild(effectElem);

                        effectElem.setAttribute("name", effectList.get(j).getName());
                        effectElem.setAttribute("value", effectList.get(j).getValue());
                    }
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
