package 알고리즘기초;

import java.util.*;
import java.io.*;

public class BOJ_1103 {

	static int N, M;
	static int[][] map;
	
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static boolean flag = false;
	static boolean[][] visited;
	
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][M + 1];
		
		visited = new boolean[N + 1][M + 1];
		
		map = new int[N + 1][M + 1];

		for(int i = 0; i < N; i ++)
		{
			st = new StringTokenizer(br.readLine());
			
			String numStr = st.nextToken();
	
			for(int j = 0; j < numStr.length(); j++)
			{
				if(numStr.charAt(j) == 'H')
				{
					map[i][j] = -1;
				}
				
				else {
					map[i][j] = (int)(numStr.charAt(j) -'0');
				}

			}
			
		}

		// 기저조건
		for(int i = 0; i < N; i++)
		{
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(0, 0) + 1);

	}
	
	static int dfs(int y, int x)
	{
		// 체크인 
		visited[y][x] = true;
		
		// 목적지인가
		// 이미 되돌아온곳이면 종료
		
		// 만약 저장되어있는 값이 있다면 return
		if(dp[y][x] != -1)
		{
			return dp[y][x];
		}
		
		dp[y][x] = 0;

		// 갈수 있는 곳 순회
		for(int i = 0; i < 4; i++)
		{
			int ny = y + (dy[i] * map[y][x]);
			int nx = x + (dx[i] * map[y][x]);
			
//			System.out.println(ny + " " + nx);
			
			// 갈수있는가
			if(ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] == -1)
				continue;
			
			if(visited[ny][nx] == true)
			{
				System.out.println("-1");
				System.exit(0);
			}

			// 간다.	
			dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
		}
		
		// 체크 아웃
		visited[y][x] = false;
		
		return dp[y][x];
	}

}
