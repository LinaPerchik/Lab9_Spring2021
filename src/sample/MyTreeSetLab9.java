package sample;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.util.Random;

/**
 * A binary tree represented graphically, with toString() and add() methods
 * @param <T> the data type of the data
 * @author Polina Ibragimova in Collaboration with Seungmi Lee
 * @version 05.07.2021
 */
public class MyTreeSetLab9<T extends Comparable<T>> extends Pane {
    private MyNode root;
    public int height;
    public static final int RADIUS = 25;
    public static final int FONT_SIZE = 16;
    private class MyNode {
        public T data;
        public MyNode left, right;
        public Circle circle;
        public Line edge;
        public Text label;

        /**
         *  Initializing the fields
         * @param inLeft left child
         * @param inRight right child
         * @param inData the data
         */
        public MyNode(MyNode inLeft, MyNode inRight, T inData) {
            data = inData;
            left = inLeft;
            right = inRight;
        }
    }
    /**
     * Using recursive method to print binary tree in pre-order (wrapper)
     */
    public String toString() {
        return "[" + (toString(root, "")).substring(0, (toString(root, "")).length() - 2 )+ "]";
    }
    /**
     * Using recursive method to print binary tree in pre-order
     */
    public String toString(MyNode node, String result) {

        if (node == null)
            return result;
        result += node.data;
        result += ", ";
        result = toString(node.left, result);
        result = toString(node.right, result);
        return result;
    }

    /**
     * Adds a node with a given value to the binary tree
     * @param addMe the value to become a node
     * @return true always returns true, is only boolean for the performance purposes
     */
    public boolean add(T addMe) {
        // If there are no nodes in the tree the value becomes root
        if (root == null) {
            root = new MyNode(null, null, addMe);
            drawShapes(root, null);
            return true;
        }
        else {
            MyNode currentNode = root;
            while (true) {
                // Traversing the left side
                if (currentNode.data.compareTo(addMe) > 0) {
                    // If there is no node on the left it becomes the node on the left
                    if (currentNode.left == null) {
                        currentNode.left = new MyNode(null, null, addMe);
                        drawShapes(currentNode.left, currentNode);
                        return true;
                    }
                    // Moving to the next node
                    else {
                        currentNode = currentNode.left;
                    }
                }
                // traversing the right side
                else {
                    // If there is no node on the right it becomes the node on the right
                    if (currentNode.right == null) {
                        currentNode.right = new MyNode(null, null, addMe);
                        drawShapes(currentNode.right, currentNode);
                        return true;
                    }
                    // Moving to the next node
                    else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }

    }

    /**
     * Draws circles representing a binary tree
     *
     * @param node the node which the circle will represent
     * @param parent the parent of the node that the circle will represent
     */
    public void drawShapes(MyNode node, MyNode parent) {
        Text label = new Text(node.data.toString());
        label.setFont(new Font(FONT_SIZE));
        Random rand = new Random();
        Circle circle = new Circle();
        // Setting the circles coordinates to random numbers within the scene
        circle.setCenterX(rand.nextInt(MyLab9.SCENE_WIDTH));
        circle.setCenterY(rand.nextInt(MyLab9.SCENE_HEIGHT));
        circle.setRadius(RADIUS);
        circle.setFill(Color.WHITE);
        double textWidth = label.getLayoutBounds().getWidth();
        double textHeight = label.getLayoutBounds().getHeight();
        // Binding the circle with text placed in the middle
        label.xProperty().bind(circle.centerXProperty().subtract(textWidth / 2));
        // For the perfect placement in the middle the text needs to be 5 pixels higher
        label.yProperty().bind(circle.centerYProperty().add((textHeight / 2) - 5));
        // The root node has red stroke, others have black
        if (node.equals(root)) {
            circle.setStroke(Color.RED);
        } else {
            circle.setStroke(Color.BLACK);
        }
        Line edge = new Line();
        // Binding the edge if the node is not root
        if (node != root){
            edge.startXProperty().bind(parent.circle.centerXProperty());
            edge.startYProperty().bind(parent.circle.centerYProperty());
            edge.endXProperty().bind(circle.centerXProperty());
            edge.endYProperty().bind(circle.centerYProperty());
        }
        this.getChildren().add(edge);
        edge.toBack();
        // Moving the circles when they are dragged
        circle.setOnMouseDragged(evt -> {
            double mouseX = evt.getX(),mouseY = evt.getY();
            circle.setCenterX(mouseX);
            circle.setCenterY(mouseY);
        });
        // Updating each node's fields
        node.circle = circle;
        node.label = label;
        if(node != root){
            node.edge = edge;
        }
        this.getChildren().addAll(circle, label);
    }
}
