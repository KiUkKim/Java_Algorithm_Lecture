package Day08.BOJ_3830;

import java.util.*;
import java.io.*;

public class BOJ_3830 {

	static StringTokenizer st;
	static int[] parent;
	static int[] value; // a�� b���� �󸶳� ū���� �ǹ��Ѵ�.
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
					
					// ���� !���� �� ���� �����ش�.
					// �׸��� [a][b]�� a�� b���� �󸶳� ū���� ����ִ� �ǹ̸� ��� �ִ�.
					int w;
					
					w = Integer.parseInt(st.nextToken());
					
					// �ش� �ΰ��� �����ش�.
					union(a, b, w);
				}
				
				else if(cmd.equals('?'))
				{
					// �� ��� �ΰ��� ������ �ִ�.
					// �� �θ� ���鼭 ���� root�� �ƴ� ��� ���� �׾��ִµ�,
					
					// ���� b�� �θ�� a�� ���������Ƿ�
					// b�� > a���� ����� ���� ������ ���� ����� ���� b < a���� ������ ���� ������ ���� ����� ��찡 �����Ѵ�.
					// �� ��� ���࿡ ã������ ���� -1�� ��� ���� �ٲ㺻��.

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
