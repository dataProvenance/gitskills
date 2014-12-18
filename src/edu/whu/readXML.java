package edu.whu;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class readXML {
  List<Prov> list = new LinkedList<Prov>();
  static final String filePathPrefix = "C:/Users/wxx/Desktop/dataCode/dataCode/";
  static final int entity = 1;
  static final int activity = 2;
  static final int agent = 3;
  
  List<Prov> getProvElement(){
    File file = new File(filePathPrefix+"prov.xml");
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(file);
      Element root = doc.getDocumentElement();
      String name = root.getTagName();
      NodeList children = root.getChildNodes();
      for( int i = 0;i<children.getLength();i++ ){
        org.w3c.dom.Node child = children.item(i);
        if( child instanceof Element ){
          Element childElement = (Element) child;
          Prov provElement = new Prov();
          provElement.setName(childElement.getTagName().split(":")[1]);
          NodeList elementlist = childElement.getChildNodes();
          String[] attributeValues = new String[10];
          int index = 0;
          for( int j=0;j<elementlist.getLength();j++ ){
            org.w3c.dom.Node element = elementlist.item(j);
            if( element instanceof Element ){
              Element elementNode = (Element) element;
              String attributeName = ((Element) element).getTagName();
              NamedNodeMap attributes = elementNode.getAttributes();
              for( int k=0;k<attributes.getLength();k++ ){
                org.w3c.dom.Node attribute = attributes.item(k);
                String attributeValue = attribute.getNodeValue();
                attributeValues[index++] = attributeValue;
                Map<String,Integer> node = new HashMap<String,Integer>();
                if( attributeName.contains("activity") ){
                  node.put(attributeValue, activity);
                }else if( attributeName.contains("entity") ){
                  node.put(attributeValue, entity);
                }else if( attributeName.contains("agent") ){
                  node.put(attributeValue, agent);
                }
                provElement.getNodes().add(node);
              }
            }
          }
          Map<String,String> edge = new HashMap<String,String>();
          edge.put(attributeValues[0], attributeValues[1]);
          provElement.setEdge(edge);
          list.add(provElement);
        }
      }
    } catch (ParserConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SAXException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return list;
  }
  
}
