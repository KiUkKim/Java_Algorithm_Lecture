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
		 * 1.�湮ó��
		 * 2. �������ΰ� -> �� �� �ִ� ���� �Ѿ ���
		 * 3. ����� ���� ��ȸ, ���� �ִ°� 
		 * 4. -> �ش� ��ǥ�� ���� �� �� �ִ��� �����¿� �밢�� Ȯ��
		 * 5. ����
		 * 6. üũ�ƿ�
		 * */
		
		// 2���� �ش��ϴ� ���
		if(row == N)
		{
			
		}
		
		// �ش� �࿡�� Ž���� �� �ִ� ���� �������� ���� 0���� �����.
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
