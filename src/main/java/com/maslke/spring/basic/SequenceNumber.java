package com.maslke.spring.basic;

public class SequenceNumber {
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    public int getNextValue() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void ma   in(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        TestClient c1 = new TestClient(sn);
        TestClient c2 = new TestClient(sn);
        TestClient c3 = new TestClient(sn);
        c1.start();
        c2.start();
        c3.start();
    }

    private static class TestClient extends Thread {
        private SequenceNumber sn;

        public TestClient(SequenceNumber sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextValue() + "]");
            }
        }
    }
}
