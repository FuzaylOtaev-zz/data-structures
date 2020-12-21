package com.company;

public class Main {

    public static void main(String[] args) {
//        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
//        list.addLast(10);
//        list.addLast(11);
//        list.addLast(5);
//        list.addLast(6);
//        list.addLast(78);
//        list.addLast(50);
//
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.get(2));
//        System.out.println(list.get(3));
//        System.out.println(list.get(4));
//        System.out.println(list.get(5));
//
//        System.out.println(list.toString());

//        Make make = new Make();
//        make.name = "make";

        Car car = new Car();
        car.make = new Make();
        car.make.name = "make";

        Make make = car.make;

        car = null;
        System.out.println(make.name);
    }
}



class Car {
    public Make make;
}

class Make {
    public String name;
}