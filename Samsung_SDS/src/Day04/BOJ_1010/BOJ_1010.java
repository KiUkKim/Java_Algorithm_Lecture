package Day04.BOJ_1010;

import java.util.*;
import java.io.*;

public class BOJ_1010 {

	static int TC;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(st.nextToken());

		dp = new int[31][31];
		
		dp[0][0] = 1;
		
		for(int i = 1 ; i <= 30; i++)
	    {
	        for(int j = i; j <= 30; j++)
	        {
	            if(i == 1)
	            {
	                dp[i][j] = j;
	            }
	            else if(i == j)
	            {
	                dp[i][j] = 1;
	            }
	                
	            else if(i < j)
	            {
	                dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
	            }
	        }
	    }    
		
		for(int i = 0; i < TC; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			int K = Integer.parseInt(st.nextToken());
			
			sb.append(dp[N][K] + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
}
