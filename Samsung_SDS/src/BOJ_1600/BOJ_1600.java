package BOJ_1600;

import java.util.*;
import java.io.*;

public class BOJ_1600 {
	
	static int K, N, M;
	static int[][] map;
	static boolean[][][] visit;
	
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	static final int[] hy = {-2, -2, -1, -1, 1, 1, 2 ,2};
	static final int[] hx = {-1, 1, -2, 2, -2, 2, -1, 1};
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M + 1];
		
		visit = new boolean[N + 1][M + 1][K + 1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(0, 0));
	}
	
	static int bfs(int y, int x)
	{
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(y, x, 0, 0));
		
		visit[y][x][0] = false;
		
		while(!q.isEmpty())
		{
			Node cur = q.poll();
			
//			System.out.println(cur.y + " " + cur.x + " " + cur.cnt);
			if(cur.y == N - 1 && cur.x == M-1)
			{
				return cur.move;
			}
						
			for(int i = 0; i < 4; i++)
			{
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
					
				if(ny < 0 || ny >= N || nx < 0 || nx >= M)
					continue;
					
				if(map[ny][nx] == 1)
					continue;
					
				if(visit[ny][nx][cur.cnt])
					continue;
				
				visit[ny][nx][cur.cnt] = true;
				q.add(new Node(ny, nx, cur.move + 1, cur.cnt));
			}
			
			if(cur.cnt < K)
			{
				for(int i = 0; i < 8; i++)
				{
					int hny = cur.y + hy[i];
					int hnx = cur.x + hx[i];
					
					if(hny < 0 || hny >= N || hnx < 0 || hnx >= M)
						continue;
					
					if(map[hny][hnx] == 1)
						continue;
					
					if(visit[hny][hnx][cur.cnt + 1])
						continue;
					
					visit[hny][hnx][cur.cnt + 1] = true;
					q.add(new Node(hny, hnx, cur.move + 1, cur.cnt + 1));
				}
			}
		}
		
		return -1;
		
	}

}

class Node{
	int y;
	int x;
	int move;
	int cnt;
	
	Node(int y, int x, int move, int cnt)
	{
		this.y = y;
		this.x = x;
		this.move = move;
		this.cnt = cnt;
	}
}
