package src.less14.cuncurrent;

import java.util.concurrent.Semaphore;

//Mutual exclusion
public class SimpleSemaphore {
    public static void main(String[] args) {
        final Semaphore smp = new Semaphore(4);
        Runnable limitedCall = new Runnable() {
                    int count = 0;
                    public void run() {
                        int time = 3 + (int) (Math.random() * 4.0);
                        int num = count++;
                        try {
                            smp.acquire(); // � ���� ������ ����� ����������� �������,
                            // ���� � ��� ���� ��������� �����, ���� ���� ���,
                            // ���� ���� �� �������� �����
                            System.out.println("����� #" + num + " �������� ��������� ����� ������ �������� "
                                    + time + " ���.");
                            Thread.sleep(time * 10); // ������ ���, ��� ����� ��������� ������ ������
                            System.out.println("����� #" + num + " �������� ������!");
                            smp.release(); // ����������� �������, ����� ������ ����� ��� ��� ������
                        } catch (InterruptedException intEx) {
                            intEx.printStackTrace();
                        }
                    }
                };
        for (int i = 0; i < 10; i++)
            new Thread(limitedCall).start(); // �������� ��������� ������������ 10 �������
    }
}
