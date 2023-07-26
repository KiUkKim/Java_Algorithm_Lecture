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
				
				// ������ ť�� �־�����Ѵ�.
				if(map[i][j] == '*')
				{
					q.add(new Point(i, j, '*'));
				}
				
				// ���� �������� ���Դٸ� ���߿� �־��ش�.
				if(map[i][j] == 'S')
				{
					map[i][j] = '.';
					start = new Point(i, j, '.');
				}
			}
		}
		
		// �Ÿ��� �ְ� �Ÿ��� ��Ƶд�.
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
//		// ������ �ʱ�ȭ
		dist[start.y][start.x] = 0;		
		
		while(!q.isEmpty())
		{
			// 1. Q���� ������
			Point p = q.poll();
			
			// 2. ������ �ΰ�
			if(p.y == end.y && p.x == end.x)
			{
				break;
			}
			
			// 3. ����� ���� ��ȸ
			for(int i = 0; i < 4; i++)
			{
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				// ���� �մ°��ΰ�.
				// 1. ������ ������ʴ� ���
				if(ny < 0 || ny >= R || nx < 0 || nx >= C)
					continue;
				
				if((p.type == '.' && dist[ny][nx] != Integer.MAX_VALUE) || (p.type == '.' && map[ny][nx] == '*'))
					continue;
				
				// ���� ���� ����ġ�� ��츦 ��������Ѵ�.
				if(p.type == '*')
				{
					// ���� ��� �� �� �ִ� ���� .�̰� �ȴ�.
					if(map[ny][nx] == '.')
					{
						// üũ��
						map[ny][nx] = '*';
						
						// ť�� �ִ´�.
						q.add(new Point(ny, nx, '*'));
					}
				}
				
				// ���� ���
				else if(p.type == '.' || p.type == 'S')
				{					
					// üũ��
					if(map[ny][nx] == '.' || map[ny][nx] == 'D')
					{
						dist[ny][nx] = dist[p.y][p.x] + 1;
						
						// ť�� �ִ´�.
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
	char type; // ����ġ���� ������
	
	Point(int y, int x, char type)
	{
		this.y = y;
		this.x = x;
		this.type = type;
	}
}
