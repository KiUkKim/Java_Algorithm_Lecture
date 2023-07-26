package Day01_3055;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	static Queue<Point> q = new LinkedList<>();
	
	static Point end, start;
	static Character map[][];
	static int dist[][];
	static int R, C;
	static boolean foundAnswer = false;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\eclipse-workspace\\Samsung_SDS\\src\\Day01_1759\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new Character[R][C];
		dist = new int[R][C];
		
		for(int i = 0; i < R; i++)
		{
			st = new StringTokenizer(st.nextToken());
			
			String str = st.nextToken();
			
			for(int j = 0; j < C; j++)
			{
				map[i][j] = str.charAt(i);
			}
		}
		
		while(!q.isEmpty())
		{
			// 1. 큐에서 꺼내옴
			Point p = q.poll();
			
			// 2. 목적지인가? -> D
			if(p.type == 'D')
			{
				foundAnswer = true;
				break;
			}
			
			// 3. 연결된 곳을 순회 -> 좌, 우, 위, 아래
			for(int i = 0; i < 4; i++)
			{
				int ty = p.y + dy[i];
				int tx = p.x + dx[i];
				
				// 4.갈 수 있는가 ? (0 <= ty < R, 0 <= tx < C)
				if(0 <= ty && ty < R && 0 <= tx && tx < C)
				{
					if(p.type == '.' || p.type == 'S')
					{
						// 갈수 있는가? -> (고슴도치 ) -> . or D , 방문하지 않은곳
						if((map[ty][tx] == '.' || map[ty][tx] == 'D') && dist[ty][tx] == 0)
						{
							// 5.체크인 -> dp에 현재 값 +1
							dist[ty][tx] = dist[p.y][p.x] + 1;
							
							// 6. 큐에 넣는곳
							q.add(new Point(ty, tx, map[ty][tx]));
						}
					}
					
					else if(p.type == '*')
					{
						// 4. 갈 수 있는가? (물)
						if(map[ty][tx] == '.')
						{
							// 5. 체크인
							map[ty][tx] = '*';

							// 6. 큐에 넣음
							q.add(new Point(ty, tx, map[ty][tx]));
						}
					}
				}
			}
		}
		
		if(foundAnswer == true)
		{
			System.out.println(dist[end.y][end.x]);
		}
		
		else {
			System.out.println("KAKTUS");
		}
	}

}
