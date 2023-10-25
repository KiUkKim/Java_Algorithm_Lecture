package Day12.BOJ_11062;

import java.util.*;
import java.io.*;

public class BOJ_11062 {
	
	static int T, N;
	static int[] card;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		
		for(int tc = 0; tc < T; tc++)
		{
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			card = new int[N + 1];
			
			dp = new int[N+2][N+1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 1; i<= N; i++)
			{
				card[i] = Integer.parseInt(st.nextToken());
			}
			
			boolean gwTurn = true;
			
			if(N % 2== 0)
			{
				gwTurn = false;
			}
			
			for(int i = 1; i <= N; i++)
			{
				for(int j = 1; j <= N - i + 1; j++)
				{
					int row = j;
					int col = i + j - 1;
					
					
					if(gwTurn)
					{
						// 현재카드인지 
						dp[row][col] = Math.max(dp[row+1][col] + card[row] , dp[row][col - 1] + card[col]);
					}
					
					// 명우의 턴 최소값
					else{
						dp[row][col] = Math.min(dp[row + 1][col], dp[row][col - 1]);
					}
				}
				
				gwTurn = !gwTurn;
			}
			
			
			bw.write(dp[1][N] + " ");
			bw.newLine();
		}
		bw.flush();
		bw.close();

	}

}
