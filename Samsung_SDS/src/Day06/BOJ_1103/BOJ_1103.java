package Day06.BOJ_1103;

import java.util.*;
import java.io.*;

public class BOJ_1103 {
	
	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, -1, 1};
	static int[][] map;
	static int ans = 0;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			String numStr = st.nextToken();
			
			for(int j = 0; j < M; j++)
			{
				map[i][j] = numStr.charAt(j);
			}
		}
		
		bfs();
	}
	
	static void bfs()
	{
		
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
