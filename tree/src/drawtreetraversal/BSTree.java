package drawtreetraversal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author CE160353 PhamThanhNghiem
 */
public class BSTree implements Serializable{

    private BSTNode root;
    private int width;
    private String traversal;
    private ArrayList<BSTNode> path;
    private String pathOfNode;
    
    private ArrayList<BSTNode> asscendingList;
    

    public BSTree(int width) {
        this.root = null;
        this.width = width;
        this.traversal = "";
        this.path = new ArrayList<>();
        this.asscendingList = new ArrayList<>();
    }

    public void addNode(int newNodeData) {
        if (root == null) {
            this.root = new BSTNode(newNodeData, width);
        } else {
            boolean isAdded = false;
            BSTNode bst = this.root;
            while (!isAdded) {
                if (bst.getData() == newNodeData) {
                    bst.setCount(bst.getCount() + 1);
                    isAdded = true;
                } else if (bst.getData() > newNodeData) {
                    if (bst.hasLeftChild()) {
                        bst = bst.getLeftChild();
                    } else {
                        bst.setLeftChild(new BSTNode(newNodeData));
                        isAdded = true;
                    }
                } else {
                    if (bst.hasRightChild()) {
                        bst = bst.getRightChild();
                    } else {
                        bst.setRightChild(new BSTNode(newNodeData));
                        isAdded = true;
                    }
                }
            }
        }
        System.out.println("Add Successfully!");
    }

    public BSTNode getRoot() {
        return root;
    }

    public void getPreOrder() {
        this.traversal = "";
        getPreOrder(root);
        this.traversal = this.traversal.substring(0, this.traversal.length() - 2);
    }

    private void getPreOrder(BSTNode node) {
        if (node != null) {
            for (int i = 0; i < node.getCount(); i++) {
                this.traversal += node.getData() + ", ";
            }
            getPreOrder(node.getLeftChild());
            getPreOrder(node.getRightChild());
        }
    }

    public void getInOrder() {
        this.traversal = "";
        this.asscendingList.clear();
        getInOrder(root);
        this.traversal = this.traversal.substring(0, this.traversal.length() - 2);
    }

    private void getInOrder(BSTNode node) {
        if (node != null) {
            getInOrder(node.getLeftChild());
            this.asscendingList.add(node);
            for (int i = 0; i < node.getCount(); i++) {
                this.traversal += node.getData() + ", ";
            }
            getInOrder(node.getRightChild());
        }
    }

    public void getPostOrder() {
        this.traversal = "";
        getPostOrder(root);
        this.traversal = this.traversal.substring(0, this.traversal.length() - 2);
    }

    private void getPostOrder(BSTNode node) {
        if (node != null) {
            getPostOrder(node.getLeftChild());
            getPostOrder(node.getRightChild());
            for (int i = 0; i < node.getCount(); i++) {
                this.traversal += node.getData() + ", ";
            }
        }
    }

    public String getTraversal() {
        return traversal;
    }

    public ArrayList<BSTNode> getPath() {
        return path;
    }

    public void setPath(ArrayList<BSTNode> path) {
        this.path = path;
    }

    public String getPathOfNode() {
        return pathOfNode;
    }

    public void setPathOfNode(String pathOfNode) {
        this.pathOfNode = pathOfNode;
    }

    public void clearPath() {
        this.path.clear();
        this.pathOfNode = "";
    }

    public BSTNode findNode(int findNodeData) {
        this.clearPath();
        BSTNode node = this.root;
        while (node != null) {
            this.path.add(node);
            this.pathOfNode += node.getData() + "=>";
            if (node.getData() == findNodeData) {
                return node;
            } else if (node.getData() > findNodeData) {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }
        this.clearPath();
        return null;
    }

    public boolean deleteNode(int data) {
        BSTNode node = findNode(data);
        return deleteNode(node);
    }

    public boolean deleteNode(BSTNode node) {
        if (node == null) {
            return false;
        }
        node.setCount(node.getCount() - 1);
        if (node.getCount() == 0) {
            if (node.isLeaf()) {
                if (node.getParent() == null) {
                    clear(); 
                    return true;
                }
                node.getParent().removeLeafNode(node);
                return true;
            } else {
                BSTNode incomer = null;
                if (node.hasLeftChild()) {
                    incomer = node.getLeftChild().findMaxNode();
                } else if (node.hasRightChild()) {
                    incomer = node.getRightChild().findMinNode();
                }
                node.setData(incomer.getData());
                node.setCount(incomer.getCount());
                incomer.setCount(1);
                return deleteNode(incomer);
            }
        } else {
            return true;
        }
    }
    
    public void clear() {
        clearNode(this.root);
        this.root = null;
    }
    
    public void clearNode(BSTNode node) {
        if (node != null) {
            if (node.hasLeftChild()) {
                clearNode(node.getLeftChild());
            }
            if (node.hasRightChild()) {
                clearNode(node.getRightChild());
            }
            if (!node.isRoot()) {
                node.getParent().removeLeafNode(node);
            }
        }
    }
    
    public void balancing() {
        getInOrder();
        clear();
        balancing(0, this.asscendingList.size() - 1);
    }
    
    public void balancing(int leftIndex, int rightIndex) {
        if (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;
            int nodeData = this.asscendingList.get(middleIndex).getData();
            int nodeCount = this.asscendingList.get(middleIndex).getCount();
            for (int i = 0; i < nodeCount; i++) {
                addNode(nodeData);
            }
            balancing(leftIndex, middleIndex - 1);
            balancing(middleIndex + 1, rightIndex);
        }
    }
    
    public void getBFS() {
        this.traversal = "";
        Queue<BSTNode> q = new LinkedList<>();
        BSTNode node;
        q.add(this.root);
        while (!q.isEmpty()) {
            node = q.poll();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    this.traversal += "," + node.getData();
                }
                q.add(node.getLeftChild());
                q.add(node.getRightChild());
            }
        }
        this.traversal = traversal.substring(1, traversal.length());
    }
    
    public void getDFS() {
        this.traversal = "";
        Stack<BSTNode> s = new Stack<>();
        BSTNode node;
        s.push(this.root);
        while (!s.isEmpty()) {
            node = s.pop();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    this.traversal += "," + node.getData();
                }
                s.push(node.getRightChild());
                s.push(node.getLeftChild());
            }
        }
        this.traversal = traversal.substring(1, traversal.length());
    }
}
