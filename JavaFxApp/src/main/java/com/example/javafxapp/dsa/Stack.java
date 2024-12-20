package com.example.javafxapp.dsa;

public class Stack {

    Node top;

    public void push(int data){

        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    public  void printStack(){
        Node temp = top;

        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void pop(){
        top = top.next;

    }
}
