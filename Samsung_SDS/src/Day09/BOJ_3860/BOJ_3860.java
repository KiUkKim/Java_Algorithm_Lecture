package Day09.BOJ_3860;

import java.util.*;
import java.io.*;

public class BOJ_3860 {

	static int N, M, RipSize, HollSize;
	
	static int[][] map; // 0�� �ܵ�, 1�� ����, 2�� �ͽ� ������ ���մϴ�.
	static List<Edge> route; // �� �� �ִ� �� ��Ʈ
	static boolean[][] visited;
	static int[][] dist;
	static StringTokenizer st;
	static boolean Cycle;
	
	static final int dy[] = { -1, 1, 0, 0};
	static final int dx[] = { 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day09\\BOJ_3860\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0)
				break;
			
			map = new int[N + 1][ M + 1];
			
			visited = new boolean[N + 1][M + 1];
			
			dist = new int[N + 1][M + 1];
			
			route = new ArrayList<>();
			
			for(int i = 0; i <= N; i++)
			{
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			st = new StringTokenizer(br.readLine());
			
			// ���� �Է�
			RipSize = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < RipSize; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				
				int y = Integer.parseInt(st.nextToken());
				
//				System.out.println(y + " " + x);
				
				map[y][x] = 1;
			}
			
			st = new StringTokenizer(br.readLine());
			
			HollSize = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < HollSize; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				
				int y = Integer.parseInt(st.nextToken());
				
				int cx = Integer.parseInt(st.nextToken());
				
				int cy = Integer.parseInt(st.nextToken());
				
				int time = Integer.parseInt(st.nextToken());
				
				map[y][x] = 2;
				
				// �ش� ���ۿ��� �̵� �ϴ� ��θ� �̸� �����Ѵ�.
				route.add(new Edge(new Pair(y, x), new Pair(cy, cx), time));
			}
			
			// bfs�� ���ؼ� �� �� �ִ� ��θ� �����صд�.
			bfs();
			
			Cycle = false;
			
			BellmanFord(); // �������带 �������ش�.
			
			
			if(Cycle)
			{
				bw.write("Never");
			}
			
			else if(!Cycle)
			{
				if(dist[N - 1][M - 1] == Integer.MAX_VALUE)
				{
					bw.write("Impossible");
				}
				
				else {
					bw.write(dist[N-1][M-1] + " ");
				}
			}

			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	
	static void bfs()
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				// ������ ������ ���� �̹� ������ �ص����ϱ� -> �ܵ��� ������ ������ ����
				if(map[i][j] != 0 || (i == N -1 && j == M - 1))
				{
					continue;
				}
				
				for(int k = 0; k < 4; k++)
				{
					int ny = i + dy[k];
					int nx = j + dx[k];
					
					if(ny < 0 || ny >= N || nx < 0 || nx >= M)
						continue;
					
					// �̋��� �ð��� 1�� �����Ѵ�.
					route.add(new Edge(new Pair(i, j), new Pair(ny, nx), 1));
				}
			}
		}
	}
	
	static void BellmanFord()
	{
		dist[0][0] = 0; // ���� ���� �ʱ�ȭ
		
		// ������ ���� -1��ŭ ����
		// ������ N * M���� ���� ����
		for(int i = 0; i < N * M; i++)
		{
			// ����� ��� ���� Ȯ��
			for(int j = 0; j < route.size(); j++)
			{
				Edge curEdge = route.get(j);
				
				Pair start = curEdge.start;
				
				Pair end = curEdge.end;
				
				int time = curEdge.time;
				
				// ���� start -> end�� �̵��� �ϸ鼭 �׾�����ϴµ�
				// start �κ��� ������Ʈ�� �ȵ��� �� �ִ�.
				// ����ó��������!
				
				if(dist[end.y][end.x] > dist[start.y][start.x] + time && dist[start.y][start.x] != Integer.MAX_VALUE)
				{
					dist[end.y][end.x] = dist[start.y][start.x] + time;
				}
			}
		}
		
		for(int i = 0; i < N * M; i++)
		{
			// ����� ��� ���� Ȯ��
			for(int j = 0; j < route.size(); j++)
			{
				Edge curEdge = route.get(j);
				
				Pair start = curEdge.start;
				
				Pair end = curEdge.end;
				
				int time = curEdge.time;
				
				// ���� start -> end�� �̵��� �ϸ鼭 �׾�����ϴµ�
				// start �κ��� ������Ʈ�� �ȵ��� �� �ִ�.
				// ����ó��������!
				
				if(dist[end.y][end.x] > dist[start.y][start.x] + time && dist[start.y][start.x] != Integer.MAX_VALUE)
				{
					Cycle = true;
					return;
				}
			}
		}
	}

}

class Edge{
	Pair start;
	Pair end;
	int time;
	
	Edge(Pair start, Pair end, int time)
	{
		this.start = start;
		this.end = end;
		this.time = time;
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
