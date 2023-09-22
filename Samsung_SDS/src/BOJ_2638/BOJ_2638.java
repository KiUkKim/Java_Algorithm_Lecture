package BOJ_2638;

import java.util.*;
import java.io.*;

public class BOJ_2638 {

	static int N, M;
	static int[][] map;
	static int cnt, ans;
	static boolean[][] visited;
	static int[][] count;
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M + 1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1)
					cnt++;
			}
		}
		
		
		while(cnt > 0)
		{
			bfs(0, 0);
			
			ans++;
		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
	}
	
	static void bfs(int y, int x)
	{
		Queue<Pair> q = new LinkedList<>();
		
		count = new int[N+1][M+1];
		
		visited = new boolean[N+1][M+1];
		
		visited[y][x] = true;
		
		q.add(new Pair(y, x));
		
		while(!q.isEmpty())
		{
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++)
			{
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= M)
					continue;
				
				if(visited[ny][nx])
					continue;
				
				if(map[ny][nx] == 1)
				{
					count[ny][nx]++;
					
					if(count[ny][nx] >= 2)
					{
						visited[ny][nx] = true;
						map[ny][nx] = 0;
						cnt--;
					}
				}
				
				else {
					visited[ny][nx] = true;
					
					q.add(new Pair(ny, nx));
				}
			}
		}
		
//		for(int i = 0; i < N; i++)
//		{
//			for(int j = 0; j < M; j++)
//			{
//				System.out.print(count[i][j] + " ");
//			}
//			System.out.println();
//			
//		}
		
	}

}

class Pair{
	int y;
	int x;
	
	Pair(int y, int x)
	{
		this.y = y;
		this.x = x;
	}
}
