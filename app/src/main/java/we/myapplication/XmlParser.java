package we.myapplication;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by 一樹 on 2016/11/01.
 */

public class XmlParser {
    public static Book domParse(InputStream file) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        Element root = document.getDocumentElement();

        // ルート要素の子ノードを取得する

        NodeList rootChildren = root.getChildNodes();
        for (int i = 0; i < rootChildren.getLength(); i++) {
            Node node = rootChildren.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (element.getNodeName().equals("records")) {
                    NodeList personChildren = node.getChildNodes();
                    for (int j = 0; j < personChildren.getLength(); j++) {
                        Node personNode = personChildren.item(j);
                        if (personNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element element2 = (Element) personNode;
                            if (element2.getNodeName().equals("record")) {
                                NodeList personChildren1 = personNode.getChildNodes();
                                for (int k = 0; k < personChildren1.getLength(); k++) {
                                    Node personNode1 = personChildren1.item(k);
                                    if (personNode1.getNodeType() == Node.ELEMENT_NODE) {
                                        Element element3 = (Element) personNode1;
                                        if (element3.getNodeName().equals("recordData")) {
                                            NodeList personChildren2 = personNode1.getChildNodes();
                                            for (int l = 0; l < personChildren2.getLength(); l++) {
                                                Node personNode2 = personChildren2.item(l);
                                                if (personNode2.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element element4 = (Element) personNode2;
                                                    if (element4.getNodeName().equals("rdf:RDF")) {
                                                        NodeList personChildren3 = personNode2.getChildNodes();
                                                        for (int m = 0; m < personChildren3.getLength(); m++) {
                                                            Node personNode3 = personChildren3.item(m);
                                                            if (personNode3.getNodeType() == Node.ELEMENT_NODE) {
                                                                Element element5 = (Element) personNode3;
                                                                if (element5.getNodeName().equals("dcndl:BibResource")) {
                                                                    NodeList personChildren4 = personNode3.getChildNodes();
                                                                    Book book = new Book();
                                                                    for (int n = 0; n < personChildren4.getLength(); n++) {
                                                                        Node personNode4 = personChildren4.item(n);
                                                                        if (personNode4.getNodeType() == Node.ELEMENT_NODE) {
                                                                            // ここでやっとデータ部の中に入れる
                                                                            if (personNode4.getNodeName().equals("dcterms:title")) {
                                                                                // タイトルの取得
                                                                                book.setTitle(EditText(personNode4.getNodeName(), personNode4.getTextContent()));
                                                                            } else if (personNode4.getNodeName().equals("dcterms:creator")) {
                                                                                NodeList personChildren5 = personNode4.getChildNodes();
                                                                                for (int o = 0; o < personChildren5.getLength(); o++) {
                                                                                    Node personNode5 = personChildren5.item(o);
                                                                                    if (personNode5.getNodeType() == Node.ELEMENT_NODE) {
                                                                                        Element element6 = (Element) personNode5;
                                                                                        if (element6.getNodeName().equals("foaf:Agent")) {
                                                                                            NodeList personChildren6 = personNode5.getChildNodes();
                                                                                            for (int p = 0; p < personChildren6.getLength(); p++) {
                                                                                                Node personNode6 = personChildren6.item(p);
                                                                                                if (personNode6.getNodeType() == Node.ELEMENT_NODE) {
                                                                                                    Element element7 = (Element) personNode6;
                                                                                                    String name = "", namekana = "";
                                                                                                    if (element7.getNodeName().equals("foaf:name")) {
                                                                                                        name = EditText(personNode6.getNodeName(), personNode6.getTextContent());
                                                                                                    }
                                                                                                    if (element7.getNodeName().equals("dcndl:transcription")) {
                                                                                                        namekana = EditText(personNode6.getNodeName(), personNode6.getTextContent());
                                                                                                    }
                                                                                                    book.addAuthor(name, namekana);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            } else if (personNode4.getNodeName().equals("dcterms:publisher")) {
                                                                                NodeList personChildren5 = personNode4.getChildNodes();
                                                                                for (int o = 0; o < personChildren5.getLength(); o++) {
                                                                                    Node personNode5 = personChildren5.item(o);
                                                                                    if (personNode5.getNodeType() == Node.ELEMENT_NODE) {
                                                                                        Element element6 = (Element) personNode5;
                                                                                        if (element6.getNodeName().equals("foaf:Agent")) {
                                                                                            NodeList personChildren6 = personNode5.getChildNodes();
                                                                                            for (int p = 0; p < personChildren6.getLength(); p++) {
                                                                                                Node personNode6 = personChildren6.item(p);
                                                                                                if (personNode6.getNodeType() == Node.ELEMENT_NODE) {
                                                                                                    Element element7 = (Element) personNode6;
                                                                                                    if (element7.getNodeName().equals("foaf:name")) {
                                                                                                        book.setPublisher(EditText(personNode6.getNodeName(), personNode6.getTextContent()));
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            } else if (personNode4.getNodeName().equals("rdfs:seeAlso")) {
                                                                                String buf = EditText(personNode4.getNodeName(), personNode4.getTextContent());
                                                                                String[] tmp = buf.split("/");
                                                                                if (tmp[tmp.length - 3].equals("isbn"))
                                                                                    book.setISBN(tmp[tmp.length - 2].substring(0, tmp[tmp.length - 2].length() - 1));
                                                                            }
                                                                        }
                                                                    }
                                                                    return book;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return new Book();
    }


    public static String EditText(String remove, String text) {
        return text.replaceAll(String.format("<%s>", remove), "").replaceAll(String.format("</%s>", remove), "");
    }
}

