package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server started...");
        int port = 81;
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
            {
                System.out.printf("New connection accepted Port: %d. Write your name?\n",
                        clientSocket.getPort());
                final String name = in.readLine();
                System.out.println(name);
                out.println(String.format("Hi %s, your port is %d. Are you child? (yes/no)",
                        name, clientSocket.getPort()));
                final String answer = in.readLine();
                System.out.println(answer);
                if (answer.equals("yes")) {
                    out.println("Welcome to the kids area, " + name + "! Let's play!");
                } else if (answer.equals("no")) {
                    out.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                } else {
                    out.println(name + "! Your answer ir wrong!");
                }
                final String bye = in.readLine();
                System.out.println(bye);
                out.println(bye);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}