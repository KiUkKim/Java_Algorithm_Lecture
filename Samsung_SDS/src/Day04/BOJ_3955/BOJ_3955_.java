package Day04.BOJ_3955;

import java.util.*;
import java.io.*;

public class BOJ_3955_ {

	static int N;
	static long A, B;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day04\\BOJ_3955\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			// X : �δ� ������ ������
			// Y : ���� ������ ��
			// A*X + 1 = B * Y; �� ���°� ������ �ȴ�.
			// �׷��� A * (-X) + B * Y = 1�� ���·� ��ȯ��Ų��.
			
			A = Long.parseLong(st.nextToken());
			
			B = Long.parseLong(st.nextToken());
			
			EGresult result = extend_GCD(A, B);
			
			// �����ظ� ã�����Ѵ�.
			if(result.r != 1)
			{
				System.out.println("IMPOSSIBLE");
			}
			else {
				// x0 = s * C / D;
				long x0 = result.s;
				
				// y0 = t * C / D;
				long y0 = result.t;
				
				// ���� �ȿ� ������ �ظ� ã�� �Ϲ��� ����
				// �ܿ��
				// x = x0 + B / D * k;
				// y = y0 - A / D * K;
				
				// x < 0
				// x0 + B * k < 0
				// k < -x0 / B ( x�� ���� k ����)
				
				// 0 < y <= 1e9
				// 0 < y0 - A * k <= 1e9 ( �Ϲ��� ����)
				// -y0 < -A * K <= 1e9 - y0 (-�� ������ �ε�ȣ �ٲ�)
				
				
				// �� �������� 3.5�� ���� �Ҽ����� ������ ������ �ȴ�.
				// ���� -> �̸��� ���� �ø��� ��������, -1�� ������Ѵ�.
				
				// <=�� ������ �׳� ������ȴ�.
				
				// (y0 - 1e9) / A <= k < y0 / A;   k�� ���ؼ� �ּڰ��� ã�ƾ��Ѵ�. k -1�� ����ū k�� (y0 - 1e9)�κ��� ���� ���� k
				// 		             k < -x0 / B ( x�� ���� k ����)
				
				long kFromY = (long) Math.ceil((double) y0 / (double) A) - 1;
				
				long kFromX = (long) Math.ceil((double) -x0/ (double) B) - 1;
				
				long k = Math.min(kFromX, kFromY);
				
				long kLimitFromY = (long) Math.ceil((y0 - 1e9) / (double) A);
                if (kLimitFromY <= k) {
                    System.out.println(y0 - A * k);
                } else {
                    System.out.println("IMPOSSIBLE");
                }
			}
		}
	}
	
	static EGresult extend_GCD(long a, long b)
	{
		long s0 = 1, t0 = 0, r0 = a;
		long s1 = 0, t1 = 1, r1 = b;
		
		long tmp;
		// �����ر��� ���ϱ�
		while (r1 != 0) {
            long q = r0 / r1;

            tmp = r0 - q * r1;
            r0 = r1;
            r1 = tmp;

            tmp = s0 - q * s1;
            s0 = s1;
            s1 = tmp;

            tmp = t0 - q * t1;
            t0 = t1;
            t1 = tmp;
        }
		
        return new EGresult(s0, t0, r0);
	}
	
	
}

class EGresult{
	long s;
	long r;
	long t;

    public EGresult(long s, long t, long r) {
        super();
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
