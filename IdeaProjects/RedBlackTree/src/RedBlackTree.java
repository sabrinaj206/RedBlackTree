public class RedBlackTree {
    enum Color {RED, BLACK}

    RBNode root;
    RBNode potato;

    void insert(int value) {
        RBNode insertNode = BSTInsert(value, root);
        rotate(insertNode);
    }

    RBNode BSTInsert(int value, RBNode node) {
        RBNode insertNode = new RBNode(value, Color.RED);
        if (root == null) {
            insertNode.setColor(Color.BLACK);
            root = insertNode;
        } else if (node.value == value) {
            System.err.println("Error: value already exists in Tree.");
            return node;
        } else if (node.value > value) {
            if (node.left == null) {
                node.left = insertNode;
                insertNode.parent = node;
                return insertNode;
            } else {
                return BSTInsert(value, node.left);
            }
        } else if (node.value < value) {
            if (node.right == null) {
                node.right = insertNode;
                insertNode.parent = node;
                return insertNode;
            } else {
                return BSTInsert(value, node.right);
            }
        }
        return insertNode;
    }

    void rotate(RBNode node) {
        if (node.parent != null) {
            //case 1: K's parent P is black - do nothing
            if (node.parent.color == Color.BLACK) {
                return;
            }
            //case 2: K's parent P is red.
            else if (node.parent.color == Color.RED) {
                //case 2a: P's sibling S is null or
                if (getUncleColor(node) != Color.RED) {
                    if (!isRightChild(node) && !isRightChild(node.parent)) {
                        rotateRight(node.parent);
                        node.parent.setColor(Color.BLACK);
                        node.parent.right.setColor(Color.RED);
                    } else if (isRightChild(node) && isRightChild(node.parent)) {
                        rotateLeft(node.parent);
                        node.parent.setColor(Color.BLACK);
                        node.parent.left.setColor(Color.RED);
                    } else if (isRightChild(node) && !isRightChild(node.parent)) {
                        rotateLeft(node);
                        rotateRight(node);
                        node.setColor(Color.BLACK);
                        node.right.setColor(Color.RED);
                    } else if (!isRightChild(node) && isRightChild(node.parent)){
                        rotateRight(node);
                        rotateLeft(node);
                        node.setColor(Color.BLACK);
                        node.left.setColor(Color.RED);
                    }
                }
                //case 2b: P's sibling S is red
                else if (getUncleColor(node) == Color.RED) {
                    node.parent.setColor(Color.BLACK);
                    if (node.parent.parent != root) {
                        node.parent.parent.setColor(Color.RED);
                    }
                    setUncleColor(node, Color.BLACK);
                }
            }
        }
    }

    void rotateRight(RBNode node) {
        if (root == node) {
            return;
        }
        RBNode parent = node.parent;
        RBNode right = node.right;
        RBNode parentParent = parent.parent;
        if (root == parent) {
            root = node;
        } else {
            if (isRightChild(parent)) {
                parentParent.right = node;
            } else if (!isRightChild(parent)) {
                parentParent.left = node;
            }
        }
        node.parent = parentParent;
        node.right = parent;
        parent.parent = node;
        parent.left = right;
        if (right != null) {
            right.parent = parent;
        }
    }

    void rotateLeft(RBNode node) {
        if (root == node) {
            return;
        }
        RBNode parent = node.parent;
        RBNode left = node.left;
        RBNode parentParent = parent.parent;
        if (root == parent) {
            root = node;
        } else {
            if (isRightChild(parent)) {
                parentParent.right = node;
            } else if (!isRightChild(parent)) {
                parentParent.left = node;
            }
        }
        node.parent = parentParent;
        node.left = parent;
        parent.parent = node;
        parent.right = left;
        if (left != null) {
            left.parent = parent;
        }
    }

    Color getUncleColor(RBNode node) {
        if (node.parent != null) {
            node = node.parent;
            if (node.parent != null) {
                if (isRightChild(node)) {
                    if (node.parent.left != null) {
                        return node.parent.left.color;
                    }
                } else if (!isRightChild(node)) {
                    if (node.parent.right != null) {
                        return node.parent.right.color;
                    }
                }
            }
        }
        return null;
    }

    void setUncleColor(RBNode node, Color color) {
        if (node.parent != null) {
            node = node.parent;
            if (node.parent != null) {
                if (isRightChild(node)) {
                    if (node.parent.left != null) {
                        node.parent.left.setColor(color);
                    }
                } else if (!isRightChild(node)) {
                    if (node.parent.right != null) {
                        node.parent.right.setColor(color);
                    }
                }
            }
        }
    }

    boolean isRightChild(RBNode node) {
        if (node.parent != null) {
            if (node == node.parent.left) {
                return false;
            } else if (node == node.parent.right) {
                return true;
            }
        }
        return false;
    }

    RBNode findNode(int value) {
        RBNode node = root;
        while (true) {
            if (node == null) {
                return null;
            } else if (value == node.value) {
                return node;
            } else {
                if (value > node.value) {
                    node = node.right;
                } else if (value < node.value) {
                    node = node.left;
                }
            }
        }
    }

    String print() {
        return pre(0, "", root);
    }

    String pre(int offset, String output, RBNode node) {
        for (int i = 0; i < offset; i++) {
            output += "  ";
        }
        output += node.value + " " + node.color + "\n";
        if (node.left != null) {
            output += "L:" + pre(offset + 1, "", node.left);
        }
        if (node.right != null) {
            output += "R:" + pre(offset + 1, "", node.right);
        }
        return output;
    }

    public class RBNode {
        Color color;
        RBNode left;
        RBNode right;
        RBNode parent;
        int value;

        RBNode(int value, Color color) {
            this.value = value;
            this.color = color;
        }

        void setColor(Color color) {
            this.color = color;
        }

        String print() {
            String output = "Value: " + value + "\nColor: " + color;
            if (left != null) {
                output += "\nLeft: " + left.value;
            }
            if (right != null) {
                output += "\nRight: " + right.value;
            }
            if (parent != null) {
                output += "\nParent: " + parent.value;
            }
            return output;
        }
    }
}