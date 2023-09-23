package BOJ_1947;

import java.util.*;
import java.io.*;

public class BOJ_1947 {

	static final long mod = 1_000_000_000;
	
	static long dp[];
	static int N;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		int size = N;
		
		if(N < 2)
		{
			size = 2;
		}
		
		dp = new long[size + 1];
		
		dp[0] = 0;
		dp[1] = 0;
		
		dp[2] = 1; // a -> b , b - >a 서로 주는 한경우
				
		for(int i = 3; i <= N; i++)
		{
			dp[i] = ((i - 1) * ( (dp[i-2] + dp[i- 1])) % mod) % mod;
		}
		
		System.out.println(dp[N]);
	}

}
