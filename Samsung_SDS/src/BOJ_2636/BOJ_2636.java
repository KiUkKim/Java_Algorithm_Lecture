package BOJ_2636;

import java.util.*;
import java.io.*;

public class BOJ_2636 {

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static int N, M, cheeseCnt, ans, LastCheeseCnt, time;
	static int[][] map;
	static List<Pair> cheeseMap;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1)
				{
					cheeseCnt++;
				}
			}
		}
		
		bfs();
		
		bw.write(time + "\n");
		bw.write(LastCheeseCnt + "\n");
		bw.flush();
		bw.close();
		
	}

	static void bfs()
	{
		while(cheeseCnt > 0)
		{
			visited = new boolean[N][M];
			
			cheeseMap = new ArrayList<>();
			
			Queue<Pair> q = new LinkedList<>();
			
			q.add(new Pair(0, 0));
			
			visited[0][0] = true;
			
			// 한번의 진행시간동안 되는 곳 찾는다.
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
					
					// 만약 갈 수 있는 길이라면 q에 넣어준다.
					if(map[ny][nx] == 0)
					{
						visited[ny][nx] = true;
						q.add(new Pair(ny, nx));
					}
					
					else if(map[ny][nx] == 1)
					{
						visited[ny][nx] = true;
						cheeseMap.add(new Pair(ny ,nx));
					}
					
				}
			}
			
			for(Pair cur : cheeseMap)
			{
				map[cur.y][cur.x] = 0;
			}
			
			LastCheeseCnt = cheeseCnt;
			
			cheeseCnt -= cheeseMap.size();
			
			time++;
		}
		
		
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

