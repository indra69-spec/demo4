package com.example;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting simple student web server on port 9090...");
        
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            while (true) {
                // Wait for a browser to connect
                try (Socket client = serverSocket.accept()) {
                    OutputStream out = client.getOutputStream();
                    PrintWriter writer = new PrintWriter(out, true);
                    
                    // Send basic HTTP headers
                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-Type: text/html");
                    writer.println(); // Blank line required by HTTP protocol
                    
                    // Send simple HTML
                    writer.println("<html><body>");
                    writer.println("<h1>Hello! This is my simple Java project.</h1>");
                    writer.println("</body></html>");
                }
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
