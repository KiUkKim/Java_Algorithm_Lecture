package Day01_9663;

import java.util.*;
import java.io.*;

public class BOJ_9663_NQueen {

	static int N;
	
	static int map[][];
	
	static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws FileNotFoundException, Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src\\Day01_9663\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
	}
	
	static void dfs(int row, int col, int cnt)
	{
		/*
		 * 1.방문처리
		 * 2. 목적지인가 -> 갈 수 있는 행을 넘어선 경우
		 * 3. 연결된 곳을 순회, 갈수 있는가 
		 * 4. -> 해당 좌표에 돌을 둘 수 있는지 상하좌우 대각선 확인
		 * 5. 간다
		 * 6. 체크아웃
		 * */
		
		// 2번에 해당하는 경우
		if(row == N)
		{
			
		}
		
		// 해당 행에서 탐색할 수 있는 열을 지났으면 열을 0으로 만든다.
		if(col == N)
		{
			row++;
			col = 0;
		}
		
//		for(int i = col; i < N; i++)
//		{
//			boolean check_ = 
//		}
	}

}
