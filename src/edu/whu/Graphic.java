
package edu.whu;
import java.io.*;
import java.util.*;

import processing.core.*;

public class Graphic extends PApplet {
  int nodeCount;
  List<Node> nodes = new LinkedList<Node>();
  HashMap nodeTable = new HashMap();

  int edgeCount;
  List<Edge> edges = new LinkedList<Edge>();
  
  readXML readxml = new readXML();
  
  int dx = 950;
  int dy = 320;
  
  static final int whiteColor = 0xFFFFFF;
  static final int yellowColor = 0xFFF3F350;
  static final int buleColor = 0xFF1AE6E6;
  static final int blackColor = 0xFF000000;
  static final int redColor = 0xFFFFCC99;
  static final int squareWidth = 220;
  static final int width = 100;
  static final int height = 50;
  static final int entity = 1;
  static final int activity = 2;
  static final int agent = 3;

  PFont font;
  PShape Node;
  public void setup(){
    size(1100,500,P2D);
    background(whiteColor);
    List<Prov> list = readxml.getProvElement();
    for( Prov prov : list ){
      for( Map<String,Integer> node : prov.getNodes() ){
        String value = null;
        for( String str : node.keySet() ){
          value = str;
        }
        findNode(value,node.get(value));
      }
      String keyValue = null;
      for( String key : prov.getEdge().keySet() ){
        keyValue = key;
      }
      edges.add(new Edge(findNode(keyValue,0),findNode(prov.getEdge().get(keyValue),0),prov.getName()));
    }
  }
  
  Node findNode( String label,int nodeType ){
    label = label.toLowerCase();
    Node Node = (Node) nodeTable.get(label);
    if( Node == null ){
      Node = addNode(label,nodeType);
    }
    return Node;
  }
  
  Node addNode( String label,int nodeType ){
    Node Node = new Node(label,nodeType,dx,dy);
    this.dx -= squareWidth;
    nodeTable.put(label, Node);
    nodes.add(Node);
    return Node;
  }
  
  public void draw(){
    fill(blackColor);
    for( Node Node : nodes ){
      Node.draw();
      fill(blackColor);
    }
    for( Edge edge : edges ){
      edge.draw();
      fill(blackColor);
    }
    fill(blackColor);
    noLoop();
  }
  class Node {
    
    PShape square;  // The PShape object
   // PShape Node;
    int nodeType;
    String label;
    int x;
    int y;
    int SquareType;
    int color;
    
    Node( String label,int nodeType,int x,int y ){
      this.nodeType = nodeType;
      this.label = label;
      this.x = x;
      this.y = y;
      switch( this.nodeType ){
      case entity:
        SquareType = ELLIPSE;
        color = yellowColor;
        break;
      case activity:
        SquareType = RECT;
        color = buleColor;
        break;
      case agent:
        SquareType = RECT;
        color = redColor;
        this.x += squareWidth;
        this.y -= squareWidth;
        if( label.equals("teacher wang") ){
          this.y -= 120;
        }
        dx += squareWidth;
        break;
      }
    }

    public void draw() {
      square = createShape(SquareType, 0, 0, width, height);
      square.setFill(color);
      square.setStroke(false);
      if( this.nodeType == agent ){
        shape(square, this.x, this.y+100);
        float w = textWidth(label);
        float offset = (width-w)/2;
        text(label,this.x+offset,this.y+130);
      }else{
        shape(square, this.x, this.y);
        text(label,this.x+7,this.y+27);
      }
    }
  }

  class Edge {
    Node from;
    Node to;
    String label;
    
    Edge( Node from,Node to,String label ){
      this.from = from;
      this.to = to;
      this.label = label;
    }
    
    public void draw(){
      if( to.nodeType == agent ){
        if( from.x == 290 && from.y == 100 ){
          from.y =200;
        }
        line(from.x+width/2,from.y+25-height/2,from.x+width/2,from.y-height-20);
        line(from.x+width/2,from.y-height-20,from.x+width/2-5,from.y-height-15);
        line(from.x+width/2,from.y-height-20,from.x+width/2+5,from.y-height-15);
        float w = textWidth(label);
        float offset = (squareWidth-w)/2;
        text(label,from.x+width/2-offset+10,from.y-height/2);
      }else{
        line(from.x,from.y+25,to.x+100,to.y+25);
        line(to.x+100,to.y+25,to.x+105,to.y+20);
        line(to.x+100,to.y+25,to.x+105,to.y+30);
        float w = textWidth(label);
        float offset = (squareWidth-w)/2;
        text(label,to.x+50+offset,to.y+25);
      }
    }
  }
  
  
  static public void main(String[] args) {
    PApplet.main(new String[] { "Graphic" });
  } 
}
