public class MyTreeMap<K> {
    private int size = 0;
    private Node root = null;

    private class Node {
        public K key;
        public Node left = null;
        public Node right = null;

        public Node(K key ) {
            this.key = key;

        }
    }

    public K put(K key) {
        if (root == null) {
            root = new Node(key);
            size++;
            return null;
        }
        return putHelper(root, key);
    }

    private Node findNode(Object target) {
        Comparable<? super K> k = (Comparable<? super K>) target;
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            }
            if (cmp > 0) {
                node = node.right;
            }
            if (cmp == 0) return node;
        }
        return null;
    }

    private Node findParent(Object target) {
        Comparable<? super K> k = (Comparable<? super K>) target;
        Node node = root;
        Node parent = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if (cmp < 0) {
                parent = node;
                node = node.left;
            }
            if (cmp > 0) {
                parent = node;
                node = node.right;
            }
            if (cmp == 0) {
                return parent;
            }
        }
        return root;
    }

    public K get(Object key) {
        Node node = findNode(key);
        if (node == null) return null;
        return node.key;
    }

    private K putHelper(Node node, K key) {
        Comparable<? super K> k = (Comparable<? super K>) key;
        int cmp = k.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(key);
                size++;
                return null;
            }
            return putHelper(node.left, key);
        }
        if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(key);
                size++;
                return null;
            }
            return putHelper(node.right, key);
        }
        K oldValue = node.key;
        node.key = key;
        return oldValue;
    }

    public K remove(Object key) {
        K value = get(key);
        if (key == root.key) root = delRecursive(key);
        else delRecursive(key);
        return value;
    }

    private Node delRecursive(Object key) {
        Node node = findNode(key);
        Node parent = findParent(key);
        if (node == null) throw new NullPointerException();
        if (node.left == null && node.right == null) {
            if (parent.left == node) parent.left = null;
            if (parent.right == node) parent.right = null;
            size--;
            return parent;
        }
        if (node.right == null) {
            if (parent.left == node) parent.left = node.left;
            if (parent.right == node) parent.right = node.left;
            size--;
            return parent;
        }
        if (node.left == null) {
            if (parent.left == node) parent.left = node.right;
            if (parent.right == node) parent.right = node.right;
            size--;
            return parent;
        }
        Node tempNode = findSmallestValue(node.right);
        delRecursive(tempNode.key);
        node.key = tempNode.key;
        return parent;
    }

    private Node findSmallestValue(Node root) {
        if (root.left == null) {
            return root;
        } else {
            return findSmallestValue(root.left);
        }
    }
    public void printTree() {
        Node node = root;
        recPrintTree(node);
        System.out.println("____");
    }
    private void recPrintTree(Node node) {
        if (node.left != null) recPrintTree(node.left);
        System.out.println(node.key);
        if (node.right != null) recPrintTree(node.right);
    }
}