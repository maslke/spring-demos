package com.maslke.spring.interview;

class BaseClass {
    private void printName() {
        System.out.println("base class");
    }
    public BaseClass() {
        printName();
    }
}

public class SubClass extends BaseClass {

    private void printName() {
        System.out.println("sub class");
    }

    public SubClass() {

    }

    public static void main(String[] args) {
        BaseClass sub = new SubClass();
    }


}


