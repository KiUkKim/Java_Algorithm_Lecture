package BOJ_10164;

import java.util.*;
import java.io.*;

public class BOJ_10164 {

	static int ans = 0;
	static int N, M, K;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][M + 1];
		
		K = Integer.parseInt(st.nextToken());
		
		dp[1][1] = 1;
		
		if(K != 0)
		{
			int row, col;
			
			if(K % M == 0)
			{
				row = (K / M);
				col = M;
			}
			
			else {
				row = (K / M) + 1;
				col = K % M;
			}
			
			for(int i = 1; i <= row; i++)
			{
				for(int j = 1; j <= col; j++)
				{
					if(i == 1 && j == 1)
						continue;
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}
			}
			
			for(int i = row; i <= N; i++)
			{
				for(int j = col; j <= M; j++)
				{
					if(i == row && j == col)
						continue;
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}
			}
		}
		
		else {
			for(int i = 1; i <= N; i++)
			{
				for(int j = 1; j <= M; j++)
				{
					if(i == 1 && j == 1)
						continue;
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}
			}
		}

		bw.write(dp[N][M] + " ");
		bw.flush();
		bw.close();
	}

}
