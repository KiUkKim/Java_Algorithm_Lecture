package BOJ_4485;

import java.util.*;
import java.io.*;

public class BOJ_4485 {
	
	static int N;
	static int[][] map;
	static int[][] dist;
	static final int dy[] = {0, 0, -1, 1};
	static final int dx[] = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		
		int idx = 1;
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0)
				break;
			
			map = new int[N + 1][N + 1];
			
			
			dist = new int[N + 1][N + 1];
			
			for(int i = 0; i < N; i++)
			{
				Arrays.fill(dist[i], Integer.MAX_VALUE);				
			}
			
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++)
				{
					map[i][j] = Integer.parseInt(st.nextToken());	
				}
			}
			
			bfs();
			
			bw.write("Problem " + idx + ": " + dist[N-1][N-1] + "\n");
			
			idx++;
		}
		
		bw.flush();
		bw.close();
		
	}
	
	static void bfs()
	{
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(0, 0));
		
		dist[0][0] = map[0][0];
		
		while(!q.isEmpty())
		{
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++)
			{
				int ny = dy[i] + cur.y;
				int nx = dx[i] + cur.x;
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= N)
				{
					continue;
				}
				
				if(dist[ny][nx] > dist[cur.y][cur.x] + map[ny][nx])
				{
					dist[ny][nx] = dist[cur.y][cur.x] + map[ny][nx];
					
					q.add(new Pair(ny, nx));
				}
			}
		}
	}
	
	static class Pair{
		int y;
		int x;
		
		Pair(int y, int x)
		{
			this.y = y;
			this.x = x;
		}
	}

}
