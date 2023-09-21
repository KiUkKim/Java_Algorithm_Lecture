package BOJ_16507;

import java.util.*;
import java.io.*;

public class BOJ_16507 {

	static int map[][];
	static int subSum[][];
	static int R, C, Q;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R + 1][C + 1];
		
		subSum = new int[R + 1][C + 1];
		
		Q = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= R; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= C; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				
				subSum[i][j] = subSum[i][j-1] + map[i][j];
			}
		}

		for(int i = 0; i < Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int r1, c1, r2, c2;
			
			r1 = Integer.parseInt(st.nextToken());
			
			c1 = Integer.parseInt(st.nextToken());
			
			r2 = Integer.parseInt(st.nextToken());
			
			c2 = Integer.parseInt(st.nextToken());
			
			bw.write(check(r1, c1, r2, c2) + "\n");
		}
		
		bw.flush();
		bw.close();
	}

	static int check(int sy, int sx, int gy, int gx)
	{
		int res = 0;
		int cnt = 0;
		
		for(int i = sy; i <= gy; i++)
		{
			res += subSum[i][gx] - subSum[i][sx - 1];
			
			cnt += (gx - sx) + 1;
		}		
		
		return res / cnt;
	}
	
}
