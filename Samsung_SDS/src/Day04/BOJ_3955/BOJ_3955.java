package Day04.BOJ_3955;

import java.util.*;
import java.io.*;

public class BOJ_3955 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day04\\BOJ_3955\\input.txt"));
		
		// X : 인당 나눠줄 사탕의 수
		// Y : 사탕 봉지의 수
		// A * x + 1 = B * Y;
		// Ax + By = C의 형태로 변환
		
		// -Ax + By = 1;
		
		// A(-x) + By = 1 음수 처리, x범위가 반대가 된다.
		
		// D = gcd(A, B)
		// 배주의 항등식 : Ax + By = C 일 때, C % D == 0 이어야만 해를 가질 수 있다.
		
		// s , t, r
		
		// x0 = s * C / D;
		
		// y0 = t * C / D;
		
		// 범위 안에 들어오는 해를 찾는 일반해 공식
		// 외우기
		// x = x0 + B / D * k;
		// y = y0 - A / D * K;
		
		// x < 0
		// x0 + B * k < 0
		// k < -x0 / B ( x에 대한 k 범위)
		
		// 0 < y <= 1e9
		// 0 < y0 - A * k <= 1e9 ( 일반해 공식)
		// -y0 < -A * K <= 1e9 - y0 (-로 나눠서 부등호 바뀜)
		
		
		// 이 구간에서 3.5와 같은 소수점이 나오면 문제가 된다.
		// 따라서 -> 미만의 경계는 올림을 먼저한후, -1을 해줘야한다.
		
		// <=이 있으면 그냥 내리면된다.
		
		// (y0 - 1e9) / A <= k < y0 / A;   k에 대해서 최솟값을 찾아야한다. k -1이 가장큰 k값 (y0 - 1e9)부분이 가장 작은 k
		// 		             k < -x0 / B ( x에 대한 k 범위)
		
		
	}
	
	static EgResult calcEgcd(long a, long b)
	{
		long s0 = 1, t0 = 0, r0 = a;
		long s1 = 0, t1 = 1, r1 = b;
		
		
		long temp;
		
		while(r1 != 0)
		{
			long q = r0 / r1; // 몫
			
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
