package edu.whu;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Prov{
    
    String name;
    Map<String,String> edge = new HashMap<String,String>();//±ß
    List<Map<String,Integer>> nodes = new LinkedList<Map<String,Integer>>();//½Úµã
    
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public Map<String, String> getEdge() {
      return edge;
    }
    public void setEdge(Map<String, String> edge) {
      this.edge = edge;
    }
    public List<Map<String, Integer>> getNodes() {
      return nodes;
    }
    public void setNodes(List<Map<String, Integer>> nodes) {
      this.nodes = nodes;
    }
    
    
 }
