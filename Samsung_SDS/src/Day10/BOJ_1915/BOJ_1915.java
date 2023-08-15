package Day10.BOJ_1915;

import java.util.*;
import java.io.*;

public class BOJ_1915 {

	static int N, M;
	static int[][] map;
	static int[][] dp;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		
		map = new int[N+1][M+1];
		
		dp = new int[N+1][M+1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			String numStr = st.nextToken();
			
			for(int j = 0; j < M; j++)
			{
				map[i][j] = (int) (numStr.charAt(j) - '0');
			}
		}
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= M; j++)
			{
				if(map[i-1][j-1] != 0)
				{
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
				}
				
				ans = Math.max(ans, dp[i][j] * dp[i][j]);
			}
		}
		

		bw.write(ans + " ");
		bw.newLine();
		bw.flush();
		bw.close();
	}

}
