package Day01_3055;

import java.util.*;
import java.io.*;

public class BOJ_3055 {

	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	static Queue<Point> q = new LinkedList<>();
	
	static Point end, start;
	static Character map[][];
	static int dist[][];
	static int R, C;
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\eclipse-workspace\\Samsung_SDS\\src\\Day01_1759\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new Character[R][C];
		dist = new int[R][C];
		
		for(int i = 0; i < R; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			String str = st.nextToken();
			for(int j = 0; j < C; j++)
			{
				map[i][j] = str.charAt(j);
				
				if(map[i][j] == 'D')
				{
					end = new Point(i, j, 'D');
				}
				
				// 물부터 큐에 넣어줘야한다.
				if(map[i][j] == '*')
				{
					q.add(new Point(i, j, '*'));
				}
				
				// 만약 시작점이 나왔다면 나중에 넣어준다.
				if(map[i][j] == 'S')
				{
					map[i][j] = '.';
					start = new Point(i, j, '.');
				}
			}
		}
		
		// 거리를 최고 거리로 잡아둔다.
		for(int i = 0; i < R; i++)
		{
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		q.add(start);
		
		bfs();
		
		if(dist[end.y][end.x] == Integer.MAX_VALUE)
		{
			System.out.println("KAKTUS");
		}
		
		else {
			System.out.println(dist[end.y][end.x]);
		}
	}
	
	static void bfs()
	{
//		// 시작점 초기화
		dist[start.y][start.x] = 0;		
		
		while(!q.isEmpty())
		{
			// 1. Q에서 가져옴
			Point p = q.poll();
			
			// 2. 목적지 인가
			if(p.y == end.y && p.x == end.x)
			{
				break;
			}
			
			// 3. 연결된 곳을 순회
			for(int i = 0; i < 4; i++)
			{
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				// 갈수 잇는곳인가.
				// 1. 범위를 벗어나지않는 경우
				if(ny < 0 || ny >= R || nx < 0 || nx >= C)
					continue;
				
				if((p.type == '.' && dist[ny][nx] != Integer.MAX_VALUE) || (p.type == '.' && map[ny][nx] == '*'))
					continue;
				
				// 물의 경우와 고슴도치의 경우를 나눠줘야한다.
				if(p.type == '*')
				{
					// 물의 경우 갈 수 있는 경우는 .이게 된다.
					if(map[ny][nx] == '.')
					{
						// 체크인
						map[ny][nx] = '*';
						
						// 큐에 넣는다.
						q.add(new Point(ny, nx, '*'));
					}
				}
				
				// 물의 경우
				else if(p.type == '.' || p.type == 'S')
				{					
					// 체크인
					if(map[ny][nx] == '.' || map[ny][nx] == 'D')
					{
						dist[ny][nx] = dist[p.y][p.x] + 1;
						
						// 큐에 넣는다.
						q.add(new Point(ny, nx, '.'));
					}
				}
			}
		}
		
	}

}

class Point{
	int y;
	int x;
	char type; // 고슴도치인지 물인지
	
	Point(int y, int x, char type)
	{
		this.y = y;
		this.x = x;
		this.type = type;
	}
}
