package Day04.BOJ_1256;

import java.util.*;
import java.io.*;

public class BOJ_1256 {

	static final int MAX = 1000000001;
	static int K, N, M;
	static int[][] dp;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][M+1]; // 'a' n개, 'z' m개로 만들 수 있는 경우의 수
		
		for(int i = 0; i < N+1;i++)
		{
			Arrays.fill(dp[i], -1);
				
		}
		
		if(K > getDP(N, M))
			System.out.println(-1);
		
		else {
			getWord(N, M, K);
			System.out.println(sb.toString());
		}
		
//		System.out.println(getDP(N, M));
	}
	
	static int getDP(int A, int Z)
	{
		// 기저 조건
		if(A == 0 || Z == 0)
			return 1;
		
		if(dp[A][Z] != -1)
			return dp[A][Z];
		
		// dp[x][y] = dp[x-1][y] + dp[x][y-1] // x로 만든것 + y로 만든것
		return dp[A][Z] = Math.min(getDP(A-1, Z) + getDP(A, Z-1), MAX);
	}
	
	static void getWord(int A, int Z, int order)
	{
		if(A == 0)
		{
			for(int i = 0; i < Z; i++)
			{
				sb.append("z");
			}
			return;
		}
		
		if(Z == 0)
		{
			for(int i = 0; i < A; i++)
			{
				sb.append("a");
			}
			return;
		}
		
		int aFrontCnt = getDP(A - 1, Z);
		
		if(order <= aFrontCnt)
		{
			sb.append("a");
			getWord(A-1, Z, order);
		}
		
		else {
			sb.append("z");
			getWord(A, Z-1, order - aFrontCnt);
		}		
	}
}