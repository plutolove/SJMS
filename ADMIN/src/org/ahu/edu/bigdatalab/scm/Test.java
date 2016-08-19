package org.ahu.edu.bigdatalab.scm;

/**
 * Created by WangJiang on 2016/7/10.
 * 简单的素数程序，用来做消耗CPU
 */
public class Test {

    public static void main(String args[]) {
        Thread thread1 = new Thread(new MyThread());
        thread1.start();

        Thread thread2 = new Thread(new MyThread());
        thread2.start();

        Thread thread3 = new Thread(new MyThread());
        thread3.start();

        Thread thread4 = new Thread(new MyThread());
        thread4.start();

        Thread thread5 = new Thread(new MyThread());
        thread5.start();

        Thread thread6 = new Thread(new MyThread());
        thread6.start();

        Thread thread7 = new Thread(new MyThread());
        thread7.start();

        Thread thread8 = new Thread(new MyThread());
        thread8.start();
    }

    public static class MyThread implements Runnable {
        @Override
        public void run() {
            test();
        }

        private void test() {
            boolean flag = true;
            for (int i = 2; i < 1000000; i++) {
                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) System.out.println(i + " 是素数");
                flag = true;   //恢复初值
            }
            System.out.println("----------end----------------");
        }
    }

}
