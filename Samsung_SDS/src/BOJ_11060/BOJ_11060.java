package BOJ_11060;

import java.util.*;
import java.io.*;

public class BOJ_11060 {

	static int N;
	static final int MAX = 2_000_000;
	static int[] dp, arr;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		dp = new int[N + 1];
		
		Arrays.fill(dp, MAX);
		
		arr = new int[N + 1];
		
		dp[1] = 0;
		
		for(int i = 1; i <= N; i++)
		{
			int num = Integer.parseInt(st.nextToken());
			
			int prev = dp[i];
			
			for(int j = i + 1; j <= i + num && j <= N; j++)
			{
				dp[j] = Math.min(dp[j], prev + 1);
			}
		}

		if(dp[N] == MAX)
		{
			dp[N] = -1;
		}
		
		bw.write(dp[N] + " ");
		bw.flush();
		bw.close();
	}

}
