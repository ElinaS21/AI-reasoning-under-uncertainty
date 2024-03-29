import java.util.LinkedList;

public class BayesianNetwork {

	LinkedList<Node> nodes;	
	
	public BayesianNetwork() {
		super();
		nodes = new LinkedList<Node>();
	}
	
    public static BayesianNetwork fresh_copy (BayesianNetwork original_copy)
    {
        BayesianNetwork fresh = new BayesianNetwork();
        Node oldNode;
        Node freshNode;
        int index;
        LinkedList<Node> originalParentsList;
        LinkedList<Node> originalChildrenList;
       
        for (int i = 0; i < original_copy.nodes.size(); i++) {
            oldNode = original_copy.nodes.get(i);
            if(oldNode.type==1)
                fresh.nodes.add(new F_node(oldNode.indexInNetwork, ((F_node)oldNode).vertex));
            if(oldNode.type==2)
                fresh.nodes.add(new B_node(oldNode.indexInNetwork, ((B_node)oldNode).vertex1, ((B_node)oldNode).vertex2, ((B_node)oldNode).weight,((B_node)oldNode).name));
            if(oldNode.type==3)
                fresh.nodes.add(new E_node(oldNode.indexInNetwork, ((E_node)oldNode).vertex));
        }
       
        for (int i = 0; i < original_copy.nodes.size(); i++) {
            freshNode = fresh.nodes.get(i);
            oldNode = original_copy.nodes.get(i);
            freshNode.probabilityTable=matrix_copy(oldNode.probabilityTable);
           
            originalParentsList = original_copy.nodes.get(i).parents;
            for (int j = 0; j < originalParentsList.size(); j++) {
                index=originalParentsList.get(j).indexInNetwork;
                freshNode.parents.add(fresh.nodes.get(index));
            }
           
            originalChildrenList = original_copy.nodes.get(i).children;
            for (int j = 0; j < originalChildrenList.size(); j++) {
                index=originalChildrenList.get(j).indexInNetwork;
                freshNode.children.add(fresh.nodes.get(index));
            }
           
        }
        return fresh;
    }
   
    public static double[][] matrix_copy(double[][] original) {
        double fresh[][] = new double [original.length][0];
        for (int i = 0; i < original.length; i++) {
            fresh[i] = original[i].clone();
        }
        return original;
    }
    
	public void printProbabilityTable() {
		for(Node n: nodes)
			n.printProbabilityTable();
	}
	
}
