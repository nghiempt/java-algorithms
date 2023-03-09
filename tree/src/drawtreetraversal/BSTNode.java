package drawtreetraversal;

import java.io.Serializable;

/**
 *
 * @author CE160353 PhamThanhNghiem
 */
public class BSTNode implements Serializable {

    private int data;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private BSTNode parent;
    private int x, y, width;
    private int level, order;
    private int count;

    private static final int DY_LEVEL = 50;

    public BSTNode(int data) {
        this.data = data;
        this.leftChild = this.rightChild = this.parent = null;
        this.x = this.y = this.width = 0;
        this.level = this.order = 0;
        this.count = 1;
    }

    public BSTNode(int data, int width) {
        this.data = data;
        this.leftChild = this.rightChild = this.parent = null;
        this.width = width / 2;
        this.x = this.width;
        this.y = DY_LEVEL;
        this.level = this.order = 0;
        this.count = 1;
    }

    public enum NodeType {
        RightNode, LeftNode
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasChild() {
        return hasLeftChild() || hasRightChild();
    }

    public boolean isLeaf() {
        return !hasChild();
    }

    public boolean isInside() {
        return !isInside() && !isLeaf();
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BSTNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BSTNode leftChild) {
        this.leftChild = leftChild;
        if (leftChild != null) {
            this.leftChild.setParent(this, NodeType.LeftNode);
        }
    }

    public BSTNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BSTNode rightChild) {
        this.rightChild = rightChild;
        if (rightChild != null) {
            this.rightChild.setParent(this, NodeType.RightNode);
        }
    }

    public BSTNode getParent() {
        return parent;
    }

    public void setParent(BSTNode parent, NodeType nodeType) {
        this.parent = parent;
        this.level = parent.getLevel() + 1;
        if (nodeType == NodeType.LeftNode) {
            this.order = parent.getOrder() * 2 + 1;
        } else {
            this.order = parent.getOrder() * 2 + 2;
        }
        this.width = parent.getWidth() / 2;
        this.y = this.parent.getY() + DY_LEVEL;
        if (nodeType == NodeType.LeftNode) {
            this.x = parent.getX() - this.width;
        } else {
            this.x = parent.getX() + this.width;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BSTNode findMinNode() {
        BSTNode node = this;
        while (node.hasLeftChild()) {
            node = node.getLeftChild();
        }
        return node;
    }

    public BSTNode findMaxNode() {
        BSTNode node = this;
        while (node.hasRightChild()) {
            node = node.getRightChild();
        }
        return node;
    }

    public boolean removeLeafNode(BSTNode node) {
        if (node == null) {
            return false;
        }
        if (node.isLeaf()) {
            if (this.hasLeftChild()) {
                if (this.getLeftChild().getData() == node.getData()) {
                    this.setLeftChild(null);
                    return true;
                }
            }
            if (this.hasRightChild()) {
                if (this.getRightChild().getData() == node.getData()) {
                    this.setRightChild(null);
                    return true;
                }
            }
        }
        return false;
    }
    
}
