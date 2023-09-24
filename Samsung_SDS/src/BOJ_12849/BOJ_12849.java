package BOJ_12849;

import java.util.*;
import java.io.*;

public class BOJ_12849 {
	
	static final int mod = 1_000_000_007;
	static int D;
	static long [][] dp;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		D = Integer.parseInt(st.nextToken());
		
		dp = new long[D + 1][8];
		
		dp[0][0] = 1;
		
		for(int i = 0; i < D; i++)
		{
			dp[i + 1][0] = (dp[i][1] + dp[i][2]) % mod;
			dp[i + 1][1] = (dp[i][0] + dp[i][2] + dp[i][3]) % mod;
			dp[i + 1][2] = (dp[i][0] + dp[i][1] + dp[i][3] + dp[i][5]) % mod;
			dp[i + 1][3] = (dp[i][4] + dp[i][1] + dp[i][2] + dp[i][5]) % mod;
			dp[i + 1][4] = (dp[i][3] + dp[i][5] + dp[i][6]) % mod;
			dp[i + 1][5] = (dp[i][2] + dp[i][3] + dp[i][4] + dp[i][7] ) % mod;
			dp[i + 1][6] = (dp[i][4] + dp[i][7] ) % mod;
			dp[i + 1][7] = (dp[i][5] + dp[i][6]) % mod;
		}
		
		bw.write(dp[D][0] + " ");
		bw.flush();
		bw.close();

	}

}
