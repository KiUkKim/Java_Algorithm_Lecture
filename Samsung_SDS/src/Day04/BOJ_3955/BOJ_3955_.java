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
			// X : 인당 나눠줄 사탕수
			// Y : 사탕 봉지의 수
			// A*X + 1 = B * Y; 의 형태가 나오게 된다.
			// 그러면 A * (-X) + B * Y = 1의 형태로 변환시킨다.
			
			A = Long.parseLong(st.nextToken());
			
			B = Long.parseLong(st.nextToken());
			
			EGresult result = extend_GCD(A, B);
			
			// 최적해를 찾지못한다.
			if(result.r != 1)
			{
				System.out.println("IMPOSSIBLE");
			}
			else {
				// x0 = s * C / D;
				long x0 = result.s;
				
				// y0 = t * C / D;
				long y0 = result.t;
				
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
		// 최적해까지 구하기
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
