package Day10.BOJ_5582;

import java.util.*;
import java.io.*;

public class BOJ_5582 {
	
	static int MAX;
	static int[][] dp;
	static int ans;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str1, str2;
		
		str1 = st.nextToken();
		
		MAX = Math.max(MAX, str1.length());
		
		st = new StringTokenizer(br.readLine());
		
		str2 = st.nextToken();
		
		MAX = Math.max(MAX, str2.length());
		
		dp = new int[MAX + 1][MAX + 1];
		
		for(int i = 0; i < str1.length(); i++)
		{
			for(int j = 0; j < str2.length(); j++)
			{
				if(str1.charAt(i) == str2.charAt(j))
				{
					dp[i+1][j+1] = dp[i][j] + 1;
					
					ans = Math.max(ans,dp[i+1][j+1]);
				}
			}
		}

		bw.write(ans + " ");
		bw.flush();
		bw.close();
	}

}
