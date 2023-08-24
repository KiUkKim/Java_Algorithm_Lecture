package BOJ_17472;

import java.util.*;
import java.io.*;

public class BOJ_17472 {

	static int N, M;
	
	static char[][] map;
	static int visitNum = 1;
	static boolean[][] visit;
	static List<Node> road;
	static int [] parent;
	static int ans;
	static int[][] dist;
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		visit = new boolean[N + 1][M + 1];
		
		map  = new char[N + 1][ M + 1];
		
		road = new ArrayList<>();
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
						
			for(int j = 0; j < M; j++)
			{
				map[i][j] = (char) (Integer.parseInt(st.nextToken()) + '0');
			}
			
		}
		
		for(int i = 0; i < N; i++)
		{
			// 먼저 해당 좌표의 숫자를 바꿔준다.
			for(int j = 0; j < M; j++)
			{
				if(!visit[i][j] && map[i][j] == '1')
				{
					bfs(i, j);

					visitNum++;
				}
			}
		}
		

		for(int i = 0; i < N; i++)
		{		
			for(int j = 0; j < M; j++)
			{
				if(map[i][j] != '0')
				{
					checkArea(i, j, map[i][j]);
				}
			}
		}

		Collections.sort(road, (o1, o2) -> {
			return o1.weight - o2.weight;
		});
		
		parent = new int[visitNum];
		
		Arrays.fill(parent, -1);
		
//		for(Node x : road)
//		{
//			System.out.println(x.from + " " + x.to + " " + x.weight);
//		}
		
		boolean flag = false;
		
		for(Node x: road)
		{
			int parentA = find(x.from);
			
			int parentB = find(x.to);
			
			if(parentA != parentB)
			{
				union(x.from, x.to);
								
				int NumCycle = visitNum - 1;
				
				ans += x.weight;
				
				if(parent[x.to] == -NumCycle)
				{
					break;
				}
			}
		}
		
		
		for(int i = 1; i < visitNum; i++)
		{
			if(parent[i] == -(visitNum - 1))
			{
				flag = true;
			}
		}

		if(!flag)
		{
			ans = -1;
		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
	}
	
	static void union(int a, int b)
	{
		a = find(a);
		
		b = find(b);
		
		if(a != b)
		{
			parent[b] += parent[a];
			
			parent[a] = b;
		}
	}
	
	static int find(int a)
	{
		if(parent[a] < 0)
			return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static void checkArea(int y, int x, char Num)
	{
		Queue<Node> q = new LinkedList<>();
		
		visit = new boolean[N + 1][M+1];
	
		for(int k = 0; k < 4; k++)
		{
			visit[y][x] = true;
			
			q.add(new Node(y, x, 0));
			
			while(!q.isEmpty())
			{
				Node cur = q.poll();
				
				int ny = cur.from + dy[k];
				int nx = cur.to + dx[k];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= M || visit[ny][nx])
				{
					continue;
				}
				
				if(map[ny][nx] != Num)
				{
					if(map[ny][nx] != '0')
					{
						char from = Num;
						
						char to = map[ny][nx];
												
						int Len = cur.weight;
						
						if(Len >= 2)
						{
							road.add(new Node((int)from - '0', (int)to - '0', Len));
							break;
						}
					}
					
					else {
						visit[ny][nx] = true;
						q.add(new Node(ny, nx, cur.weight + 1));
					}
				}
			}
			
			q.clear();
		}
	}
	
	static void bfs(int i, int j)
	{
		// 해당 그룹을 찾는다
		Queue<Pair> q = new LinkedList<>();
		
		int uy = i;
		int ux = j;
		int by = 0;
		int bx = 0;
		
		q.add(new Pair(i, j));
		
		map[i][j] = (char) (visitNum + '0');
		
		visit[i][j] = true;
		
		while(!q.isEmpty())
		{
			Pair cur = q.poll();
			
			for(int k = 0; k < 4; k++)
			{
				int ny = dy[k] + cur.y;
				int nx = dx[k] + cur.x;
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= M)
					continue;
				
				if(visit[ny][nx] == true)
					continue;
				
				if(map[ny][nx] == '0')
					continue;
				
				map[ny][nx] = (char) (visitNum + '0');
				
				visit[ny][nx] = true;
				
				q.add(new Pair(ny, nx));
			}
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


class Node{
	int from;
	int to;
	int weight;
	
	Node(int from, int to, int weight)
	{
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
