package Day12.BOJ_2098;

import java.util.*;
import java.io.*;

public class BOJ_2098 {

	static int N;
	static int[][] dp;
	static int[][] W;
	static final int MAX = 20000000;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		W = new int[N+1][N+1];
		
		dp = new int[N][(1 << N)]; // 현재 i의 도시에 방문 -> 방문한 도시의 상태를 확인
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				W[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 방문하지 않았을 떄, 초기화
			
			// 방문을 다 하고 INF가 나왔는지, 방문을 하지 않았는데 INF가 나왓는지 구분이 안돼서 구분해준다.
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(0, 1));
		
		for(int i = 0; i < N; i++)
		{
			for(int x : dp[i])
			{
				System.out.print(x + " ");
			}
			System.out.println();
		}
		
//		
//		bw.write(ans + " ");
//		bw.flush();
//		bw.close();
		
	}
	
	// 현재 방문한 도시, 현재까지 방문한 도시
	static int dfs(int now, int visit)
	{
		 // 탈출 조건
		// 모든 도시를 다 방문했을 경우
		if(visit == ((1 << N) - 1) )
		{
			// 해당 구간의 비용이 0
			if(W[now][0] == 0)
			{
				return MAX;
			}
			return W[now][0];	
		}
		
		// 방문을 한 도시 일 경우 (dp 배열이 업데이트가 된 경우 )
		if(dp[now][visit] != -1)
			return dp[now][visit];
		
		
		dp[now][visit] = MAX;
		
		for(int i = 0; i < N; i++)
		{
			// 현재도시에서 다음도시로 가는 길이 없거나 아니면 방문한 도시일 경우
			if(W[now][i] == 0 || (visit & (1 << i)) != 0 )
			{
				continue;
			}
			
			// 현재 도시에서 남은 도시들을 방문하는 비용 vs 다음 도시로 간 다음에 다음 도시에서 가는 비용
			int next_bit = (visit | (1 << i));
			
			
			System.out.println(now +  " " + visit + " " + dp[now][visit]);
			for(int k = 0; k < N; k++)
			{
				for(int x : dp[k])
				{
					System.out.print(x + " ");
				}
				System.out.println();
			}
			
			System.out.println();
			
			dp[now][visit] = Math.min(dp[now][visit], dfs(i, next_bit) + W[now][i]);
		}

		return dp[now][visit];
	}

}
