package Day11.BOJ_1102;

import java.util.*;
import java.io.*;

public class BOJ_1102_solve {

	static int N, P;
	
	static int[][] dp;
	static int[][] cost;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][1 << N]; // 2^16만큼 저장 그러면 0번부터 15번까지 모두 저장된 곳 까지 포함할 수 있다.
		
		cost = new int[N+1][N+1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 0부터 최대범위까지 초기화해준다.
			for(int j = 0; j < 1 << N; j++)
			{
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		String state = st.nextToken();
		
		int bit = 0;
		int cnt = 0;
		
		for(int i = 0; i < state.length(); i++)
		{
			// 만약 발전소가 열려있다면
			if(state.charAt(i) == 'Y')
			{
				bit |= (1 << i);
				cnt++;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		P = Integer.parseInt(st.nextToken());
		
		int answer = dfs(cnt, bit);
		
		// 이 경우 해당 cnt를 가진 값을 찾을 수 없음을 의미한다.
		if(answer == Integer.MAX_VALUE)
		{
			bw.write(-1 + " ");
		}
		
		else {
			bw.write(answer + " ");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int dfs(int cnt, int bit)
	{
		if(cnt >= P)
			return 0;
		
		// 최소비용을 찾음을 의미한다.
		if(dp[cnt][bit] != Integer.MAX_VALUE)
		{
			return dp[cnt][bit];
		}
		
		
		for(int i = 0; i < N; i++)
		{
			// 현재 비트 상태를 확인한다.
			
			// 의미는 현재 비트와 비교해서 켜져있는 발전소를 활용하려한다. 
			if( (bit & (1 << i)) != 0)
			{
				for(int j = 0; j < N; j++)
				{
					// 자기자신이거나, 이미 켜진 비트가 아닐 경우 가능하다.
					
					// 뒤의 코드는 의미는 켜진 발전기중에서 어떤 발전기를 키려고 할때, 이미 켜져있다면 넘기는 것을 의미한다.
					if(i == j || (bit & (1 << j)) != 0)
					{
						continue;
					}
					
					// 안꺼진 연구소를 활성화 시킨다.
					// 이제 i번을 통해서 j번을 켜줬으니 값을 cost[i][j]더해줘야한다.
					dp[cnt][bit] = Math.min(dp[cnt][bit], dfs(cnt + 1, bit | (1 << j)) + cost[i][j]);
				}
			}
		}
		
		return dp[cnt][bit];
	}

}
