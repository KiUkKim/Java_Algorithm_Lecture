package Day08.BOJ_3830;

import java.util.*;
import java.io.*;

public class BOJ_3830 {

	static StringTokenizer st;
	static int[] parent;
	static int[] value; // a가 b보다 얼마나 큰가를 의미한다.
	static final int MAX = 100001;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();

		parent = new int[MAX];
		value = new int[MAX];
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			
			int N, M;
			
			N = Integer.parseInt(st.nextToken());
			
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0)
			{
				break;
			}
			
			Arrays.fill(parent, -1);
			Arrays.fill(value, 0);
			
			for(int i = 0; i < M; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				Character cmd = st.nextToken().charAt(0);
				
				int a, b;
				
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if(cmd.equals('!'))
				{
					
					// 만약 !했을 때 값을 합쳐준다.
					// 그리고 [a][b]는 a가 b보다 얼마나 큰지가 담겨있는 의미를 담고 있다.
					int w;
					
					w = Integer.parseInt(st.nextToken());
					
					// 해당 두개를 합쳐준다.
					union(a, b, w);
				}
				
				else if(cmd.equals('?'))
				{
					// 이 경우 두가지 조건이 있다.
					// 각 부모를 돌면서 최종 root가 아닐 경우 값을 쌓아주는데,
					
					// 현재 b의 부모로 a를 저장했으므로
					// b가 > a보다 양수의 값을 가졌을 때를 물어보는 경우와 b < a보다 음수의 값을 가졌을 때를 물어보는 경우가 존재한다.
					// 이 경우 만약에 찾으려는 값이 -1일 경우 둘을 바꿔본다.

					if(find(a) == find(b))
					{
						long v = value[b] - value[a];
						bw.write((int)v + " ");
						bw.write("\n");
//						sb.append( (value[b] - value[a]) + "\n");
					}					
					else {
						bw.write("UNKNOWN\n");
//						sb.append("UNKNOWN\n");
					}
				}
			}
			
		}
		
		bw.flush();
		bw.close();
		
//		System.out.println(sb.toString());
	}

	
	static void union(int a, int b, int diff)
	{
		int aRoot = find(a);
		int bRoot = find(b);
	
		if(aRoot != bRoot)
		{
			int newD = value[a] + diff;
			
			int OriginalD = value[b];
			
			parent[bRoot] = aRoot;
			
			value[bRoot] = newD - OriginalD;
		}
	}
	
	static int find(int x)
	{
		if(parent[x] < 0)
			return x;
		
		int R = find(parent[x]);
		
		value[x] += value[parent[x]];
		
		return parent[x] = R;
	}
}
