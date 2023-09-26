package BOJ_20500;

import java.util.*;
import java.io.*;

public class BOJ_20500 {
	
	static int N;
	static long[][] dp;
	static final int mod = 1_000_000_007;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		dp = new long[3][N + 2];
		
		dp[0][2] = dp[1][2] = 1;
		
		for(int i = 3; i <= N; i++)
		{
			// i번쨰 길이까지 나머지가 3으로 나눴을 때 0이라면
			// 이 경우는 i-1번까지 길이에서 1이남은 경우와 2가 남은 경우를 계산
			dp[0][i] = (dp[1][i - 1] + dp[2][i-1]) % mod;
			
			dp[1][i] = (dp[0][i-1] + dp[2][i-1] ) % mod;
			
			dp[2][i] = (dp[0][i  - 1] + dp[1][i - 1]) % mod;
		}
		
		bw.write(dp[0][N] + " ");
		bw.flush();
		bw.close();
	}

}
