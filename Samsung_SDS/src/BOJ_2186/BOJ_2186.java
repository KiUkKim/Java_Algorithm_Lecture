package BOJ_2186;

import java.util.*;
import java.io.*;

public class BOJ_2186 {

	static int N, M, K;
	
	static Queue<Pair> q = new LinkedList<>();
	
	static final int dy[] = {-1, 1, 0, 0};
	static final int dx[] = {0, 0, -1, 1};
	
	static int res;
	static char[][] map;
	static int dp[][][];
	static char[] ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N + 1][M + 1];
		
		for(int i = 0; i < N; i ++)
		{
			st = new StringTokenizer(br.readLine());
			
			String str = st.nextToken();
			
			for(int j = 0; j < M; j++)
			{
				map[i][j] = str.charAt(j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		
		dp = new int[N + 1][M + 1][str.length()];
		
		ans = new char[str.length()];
		
		for(int i = 0; i < str.length(); i++)
		{
			ans[i] = str.charAt(i);
		}
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				if(map[i][j] == ans[0])
				{
					dp[i][j][0] = 1;
					q.add(new Pair(i, j));
				}
			}
		}
		
		bfs();

		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				res += dp[i][j][ans.length - 1];
			}
		}
		
		
		bw.write(res + " ");
		bw.flush();
		bw.close();
	}
	
	static void bfs()
	{
		int len = 0;
		
		while(!q.isEmpty())
		{
			int qSize = q.size();
			
			len++;
			
			if(len == ans.length)
				break;
			
			while(qSize-- > 0)
			{
				Pair cur = q.poll();
				
				for(int i = 0; i < 4; i++)
				{
					for(int k = 1; k <= K; k++)
					{
						int ny = cur.y + (dy[i] * k);
						int nx = cur.x + (dx[i] * k);
						
						if(ny < 0 || ny >= N || nx < 0 || nx >= M)
						{
							continue;
						}
						
						if(map[ny][nx] != ans[len])
						{
							continue;
						}
							
						if(dp[ny][nx][len] == 0)
						{
							q.add(new Pair(ny, nx));
						}
						
						dp[ny][nx][len] += dp[cur.y][cur.x][len - 1];
					}
				}
			}

		}
		
	}
	
	public static class Pair{
		int y;
		int x;
		Pair(int y, int x)
		{
			this.y = y;
			this.x = x;
		}
	}

}
