package BOJ_5212;

import java.util.*;
import java.io.*;

public class BOJ_5212 {

	static final int dy[] = {-1, 1, 0, 0};
	static final int dx[] = {0, 0, -1, 1};
	
	static int n, m;
	static char[][] map;
	
	static Queue<Pair> q;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken());
		
		m = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		
		map = new char[n + 1][m + 1];
		
		for(int i = 0; i < n; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			String str = st.nextToken();
			
			for(int j = 0; j < m; j++)
			{
				char c = str.charAt(j);
				
				map[i][j] = c;
			}
		}
		
		int tmp[][] = new int[n][m];
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++)
			{
				int cnt = 0;
				
				if(map[i][j] == 'X')
				{
					for(int k = 0; k < 4; k++)
					{
						int ny = i + dy[k];
						int nx = j + dx[k];
						
						if(ny < 0 || ny >= n || nx < 0 || nx >= m)
							continue;
						
						if(map[ny][nx] == 'X')
						{
							cnt += 1;
						}
					}
				}
				
				tmp[i][j] = cnt;
			}
		}
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++)
			{
				if(tmp[i][j] < 2)
				{
					map[i][j] = '.';
				}
			}
		}
		
		int ly = Integer.MAX_VALUE;
		int lx = Integer.MAX_VALUE;
		int ry = Integer.MIN_VALUE;
		int rx = Integer.MIN_VALUE;
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++)
			{
				if(map[i][j] == 'X')
				{
					if(i < ly)
					{
						ly = i;
					}
					
					if(i > ry)
					{
						ry = i;
					}
					
					if(j < lx)
					{
						lx = j;
					}
					
					if(j > rx)
					{
						rx = j;
					}
				}
			}
		}
		
		for(int i = ly; i <= ry; i++)
		{
			for(int j = lx; j <= rx; j++)
			{
				System.out.print(map[i][j]);
			}
			System.out.println();
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
