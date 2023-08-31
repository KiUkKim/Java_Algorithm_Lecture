package BOJ_1025;

import java.util.*;
import java.io.*;

public class BOJ_1025 {

	static int N, M;
	static int[][] arr;
	static StringBuilder sb;
	static final int dy[] = {-1, 1, 0, 0};
	static final int dx[] = {0, 0, -1, 1};
	static List<Integer> list;
	static int ans = -1;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		list = new ArrayList<>();
				
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][M + 1];
		
		visited = new boolean[N + 1][M + 1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			String tmp = st.nextToken();
			
			for(int j = 0; j < tmp.length(); j++)
			{
				arr[i][j] = (int) (tmp.charAt(j) - '0');
			}
		}
		
		dfs_up(0, 0);

		Collections.sort(list, (o1, o2) -> o2 - o1);
		
		for(int x : list)
		{
			int sqrt = (int)Math.sqrt(x);
			
			if(Math.pow(sqrt, 2) == x)
			{
				ans = x;
				break;
			}
		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
	}
	
	static void dfs_up(int row, int col)
	{
		if(sb.length() >= 2)
		{
			if((int)sb.charAt(sb.length() - 1) - '0' > (int)sb.charAt(sb.length() - 2) - '0' 
					|| ((int)sb.charAt(sb.length() - 1) - '0' == 0) && ( (int)sb.charAt(sb.length() - 2) - '0' == 0)
					|| (int)sb.charAt(sb.length() - 1) - '0' < (int)sb.charAt(sb.length() - 2) - '0' )
				list.add(Integer.valueOf(sb.toString()));
			
			else {
				return;
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			int ny = dy[i] + row;
			int nx = dx[i] + col;
			
			if(ny > N || nx > M || ny < 0 || nx < 0)
				continue;
			
			if(visited[ny][nx])
				continue;
			
			// 마지막 꺼보다 현재 참조하는 게 더 큰 경우
			visited[ny][nx] = true;
			sb.append(arr[ny][nx]);
			dfs_up(ny, nx);
			sb.deleteCharAt(sb.length() - 1);
			visited[ny][nx] = false;
		}
		
	}

}
