package edu.elearning.generics;

public class ClassTypeGenerics<T> {
    T val;

    public ClassTypeGenerics(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }


    public static void main(String[] args) {
        ClassTypeGenerics<Integer> generics = new ClassTypeGenerics<>(123);

        Integer val = generics.getVal();

        System.out.println(val);
    }
}
