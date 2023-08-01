package Day04.BOJ_3955;

import java.util.*;
import java.io.*;

public class BOJ_3955 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day04\\BOJ_3955\\input.txt"));
		
		// X : �δ� ������ ������ ��
		// Y : ���� ������ ��
		// A * x + 1 = B * Y;
		// Ax + By = C�� ���·� ��ȯ
		
		// -Ax + By = 1;
		
		// A(-x) + By = 1 ���� ó��, x������ �ݴ밡 �ȴ�.
		
		// D = gcd(A, B)
		// ������ �׵�� : Ax + By = C �� ��, C % D == 0 �̾�߸� �ظ� ���� �� �ִ�.
		
		// s , t, r
		
		// x0 = s * C / D;
		
		// y0 = t * C / D;
		
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
		
		
	}
	
	static EgResult calcEgcd(long a, long b)
	{
		long s0 = 1, t0 = 0, r0 = a;
		long s1 = 0, t1 = 1, r1 = b;
		
		
		long temp;
		
		while(r1 != 0)
		{
			long q = r0 / r1; // ��
			
			temp = r0 - q * r1;
			
			r0 = r1;
			
			r1 = temp;
			
			temp = s0 - q * s1;
			s0 = s1;
			s1 = temp;
			
			temp = t0 - q * t1;
			t0 = t1;
			t1 = temp;
		}
		
		return new EgREsult(s0, t0, r0);
	}
}

class EgResult{
	long s;
	long t;
	long r;
	
	public EgResult(long s, long t, long r) {
		super();
		this.s = s;
		this.t = t;
		this.r = r;
	}
	
	
}
