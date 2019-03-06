package ru.javawebinar.basejava;

public class MainDeadlock {
    static class Resource {
        synchronized void lock(Resource resource) {
            System.out.printf("Lock%n");
            resource.unlock();
        }

        synchronized void unlock() {
        }
    }

    public static void main(String[] args) {
        final Resource resourceOne = new Resource();
        final Resource resourceTwo = new Resource();

        new Thread(() -> resourceOne.lock(resourceTwo)).start();
        new Thread(() -> resourceTwo.lock(resourceOne)).start();
    }
}