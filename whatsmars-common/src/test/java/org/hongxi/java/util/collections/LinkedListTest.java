package org.hongxi.java.util.collections;

/**
 * @author shenhongxi 2019/8/12
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }
}
