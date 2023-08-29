package DP_EX.BOJ_1463;

import java.util.*;
import java.io.*;

public class BOJ_1463 {

	static final int MAX = 987654321;
	static int n;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		dp = new int[ n + 1];
		
		Arrays.fill(dp, MAX);
		
		dp[n] = 0;
		
		for(int i = n; i > 1; i--)
		{
			dp[i-1] = Math.min(dp[i-1], dp[i] + 1);
			
			if( (i % 3) == 0) 
			{
				dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
			}
			
			if( (i % 2) == 0)
			{
				dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
			}
		}
		
		bw.write(dp[1] + " ");
		bw.newLine();
		bw.flush();
		bw.close();
	}

}
