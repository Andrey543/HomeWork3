public class Main {
    public static void main(String[] args) {
       MyTreeMap<Integer> test = new MyTreeMap<>();
       test.put(34);
        test.put(17);
        test.put(55);
        test.put(26);
        test.put(45);
        test.put(73);
        test.put(86);
        test.put(120);
        test.put(112);
        test.put(93);
        test.printTree();
        test.remove(73);
        test.printTree();
        System.out.println(test.get(112));

    }
}