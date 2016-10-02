
/* package whatever; // don't place package name! */

import java.io.*;
import java.util.*;

class myCode
{
   //maintaining a adjacency list
   private static Map<Long, HashSet<Long>> adjList = new HashMap<Long, HashSet<Long>>();
   
    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        //running until a blank line is found in the input
        while((input=br.readLine())!=null){
            try{
                String[] splitInput = input.split(" ");
                if(input.startsWith("add")){
                    addEdge(Long.parseLong(splitInput[1]),Long.parseLong(splitInput[2]));
                } else if (input.startsWith("remove")) {
                    removeEdge(Long.parseLong(splitInput[1]),Long.parseLong(splitInput[2]));
                } else if (input.startsWith("is linked")) {
                    System.out.println(isLinked(Long.parseLong(splitInput[2]),Long.parseLong(splitInput[3])));
                }
            } catch (Exception e){
                
            }
        }
    }
    
    /**
     * method to add edge into the graph
     * @param start
     * @param end
       time complexity = o(2)
     */
    private static void addEdge(Long start, Long end){
        //check if the start node is in the graph , if not then add a new node in graph
        if(!adjList.containsKey(start)){
            adjList.put(start,new HashSet<Long>());
        }
        
         //check if the end node is in the graph , if not then add a new node in graph
        if(!adjList.containsKey(end)){
            adjList.put(end,new HashSet<Long>());
        }
        
        //doing the linkage between the start node and end node
        adjList.get(start).add(end);
        adjList.get(end).add(start);
    }
    
    /**
     * method to remove edge from the graph
     * @param start
     * @param end
       time complexity = o(2)
     */
     private static void removeEdge(Long start, Long end){
        //check if the start node and end node is in the graph , if yes then remove the node from the graph
        if(adjList.containsKey(start) && adjList.containsKey(end)){
            adjList.get(start).remove(end);
            adjList.get(end).remove(start);
            //if start node has no neighbour left then remove node from the graph
            if(adjList.get(start).size()==0){
                adjList.remove(start);
            }
            //if end node has no neighbour left then remove node from the graph
             if(adjList.get(end).size()==0){
                adjList.remove(end);
            }
        }
  
    }
    
    /**
     * method to check if there exits a path from start node to end node
     * @param start
     * @param end
     * @return boolean true or false
     */
    private static boolean isLinked(Long start, Long end){
        //implementing DFS to check is link exist or not
        Stack<Long> stack = new Stack<Long>();
        //pushing one node(start) into the stack
        stack.push(start);
        //maintaining a linked list for the visited nodes
        LinkedList<Long> vistedList = new LinkedList<Long>();
        //running until stack is not empty
        while(!stack.isEmpty()){
            //get th top from the stack
            Long currentElement = stack.pop();
            //add this node into visited linked list
            vistedList.add(currentElement);
            //if the neighbors of the current node contains the end node then exist with return value true
            if(adjList.containsKey(currentElement)){
                HashSet<Long> neighbours = adjList.get(currentElement);
                if(neighbours.contains(end)){
                    return true;
                } else {
                    //if non of the neighbor is end node then add all neighbor to stack
                    for (Long item : neighbours){
                        //check the visited linked list if that neighbor is already visited , if not then add to stack
                        if(!vistedList.contains(item))
                            stack.push(item);
                    }
                }
            }
        }
        //if no match found return false
        return false;
    }
    
}
