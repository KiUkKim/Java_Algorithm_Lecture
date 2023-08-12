package Day11.BOJ_1102;

import java.util.*;
import java.io.*;

public class BOJ_1102 {

	static int N, P;
	static int[][] cost, dp;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		cost = new int[N][N];
		
		dp = new int[N][1<<N]; // 2^16에 따른 발전소
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j = 0; j < 1 << N; j++)
			{
				dp[i][j] = Integer.MAX_VALUE;
			}
			
		}
		
		String state = br.readLine();
		
		P = Integer.parseInt(br.readLine());
		
		// 발전기의 상태를 비트마스크 형태로 저장
		// OR 연산을 통해 i번째 해당하는 발전소를 on
		int cnt = 0;
		int bit = 0;
		
		for(int i = 0; i < N; i++)
		{
			// 켜져있는 경우
			if(state.charAt(i) == 'Y')
			{
				bit = bit | (1 << i);
				cnt++;
			}
		}
		
		int answer = dfs(cnt, bit);
		
		if(answer == Integer.MAX_VALUE)
		{
			System.out.println("-1");
		}
		else {
			System.out.println(answer);
		}
		
		

	}
	
	static int dfs(int cnt, int bit)
	{
		// 발전소 수리가 더 이상 필요없음
		if(cnt >= P)
		{
			return 0;
		}
		
		// 최소 비용을 리턴
		if(dp[cnt][bit] != Integer.MAX_VALUE)
		{
			return dp[cnt][bit];
		}
		
		for(int i = 0; i < N; i++)
		{
			// 현재 해당 번호의 발전기가 켜져있을떄
			if((bit & (1 << i)) != 0)
			{
				for(int j  = 0; j < N; j++)
				{
					// 만약 해당 발전소가 켜져있거나 ( 켜져있으면 다시 작동시킬 이유가 없다.)
					// 
					if(i == j || ((bit & (1 << j)) != 0))
					{
						continue;
					}
					
					// 최솟값 구하기
					// Top-Down 방식
					dp[cnt][bit] = Math.min(dp[cnt][bit], dfs(cnt + 1, bit | (1 << j )) + cost[i][j]);
				}
			}
		}	
	
		return dp[cnt][bit];
	}

}
