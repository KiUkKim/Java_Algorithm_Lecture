package Day10.BOJ_7579;

import java.util.*;
import java.io.*;

public class BOJ_7579 {

	static int N, M;
	
	static int[] AMemory; 
	static int[] ApollMemory;
	static int[][] dp;
	
	static int ans = 200000;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		AMemory = new int[N+1];
		
		ApollMemory = new int[N+1];
		
		dp = new int[N + 1][100001]; // 최대 발생할 수 있는 cost 의 경우 : N * c 		
		
		for(int i = 0; i < N; i++)
		{
			AMemory[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			ApollMemory[i] = Integer.parseInt(st.nextToken());
		}
		
		// 1번 가방부터 dp수행
		for(int i = 0; i < N; i++)
		{
			int c = ApollMemory[i];
			int m = AMemory[i];
			
			for(int j = 0; j <= 10000; j++)
			{
				if(i == 0)
				{
					if(j >= c)
					{
						dp[i][j] = m; // 첫번째 행에 대한 초기화
					}
				}
				
				else {
					if(j >= c)
					{
						dp[i][j] = Math.max(dp[i-1][j-c] + m, dp[i-1][j]);
					}
					else {
						dp[i][j] = dp[i-1][j];
					}
				}
				
				if(dp[i][j] >= M)
				{
					ans = Math.min(ans,  j);
				}
			}
		}
		
		bw.write(ans + " ");
		bw.newLine();
		bw.flush();
		bw.close();
	}

}