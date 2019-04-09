package com.maslke.spring;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class AtomicIntegerFieldUpdaterTest {

    static class User {
        private String name;
        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }

    @Test
    public void atomicIntegerFieldUpdaterTest() {
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
        User conan = new User("conan", 10);
        int oldValue = a.getAndIncrement(conan);
        int m = a.incrementAndGet(conan);
        int b = a.get(conan);
        assertEquals(oldValue, 10);
        assertEquals(m, 12);
        assertEquals(b, 12);
    }
}
