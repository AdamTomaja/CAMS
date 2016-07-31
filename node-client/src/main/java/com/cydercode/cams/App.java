package com.cydercode.cams;

/**
 * @author Adam Tomaja
 */
public class App {

    public static void main(String[] args) {
        NodeClient client = new NodeClient("http://cydercode.pl:8083/cams-node/");
        System.out.println(client.getStatus().toString());
    }
}
