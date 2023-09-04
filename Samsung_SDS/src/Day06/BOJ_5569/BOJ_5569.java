package Day06.BOJ_5569;

import java.util.*;
import java.io.*;

public class BOJ_5569 {

	static final int mod = 100000;
	static int W, H;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		
		H = Integer.parseInt(st.nextToken());
		
		
		dp = new int[W + 2][H + 2][4];
		
		// 기저조건
		for(int i = 2; i <= W; i++)
		{
			dp[i][1][0] = 1;
		}
		
		for(int j = 2; j <= H; j++)
		{
			dp[1][j][1] = 1; 
		}
		
		dp[2][2][2] = 1;
		dp[2][2][3] = 1;
		
		for(int i = 2; i<= W; i++)
		{
			for(int j = 2; j <= H; j++)
			{
				if(i == 2&& j == 2)
					continue;
				
				// 1. 오른쪽으로 가는 경우
				if(i > 2)
				{
					dp[i][j][0] = (dp[i-2][j][0] + dp[i-2][j][1] + dp[i-2][j][2]) % mod;
				}
				
				// 2. 위쪽으로 직진
				if(j > 2)
				{
					dp[i][j][1] = (dp[i][j-2][0] + dp[i][j-2][1] + dp[i][j-2][3]) % mod;
				}
				
				// 3. 위로가다 오른쪽으로 
				dp[i][j][2] = (dp[i-1][j-1][1] + dp[i-1][j-1][3]) % mod;
				
				// 4. 오른쪽 가다 위로
				dp[i][j][3] = (dp[i-1][j-1][0] + dp[i-1][j-1][2]) % mod;
			}
		}
		
		
		System.out.println((dp[W][H][0] + dp[W][H][1] + dp[W][H][2] + dp[W][H][3]) % mod );
	}

}
