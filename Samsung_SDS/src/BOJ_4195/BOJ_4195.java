package BOJ_4195;

import java.util.*;
import java.io.*;

public class BOJ_4195 {

	static int TC, M;
	static int cnt;
	static int[] parent;
	static Map<String, Integer> map;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		
		while(TC-- > 0)
		{
			map = new HashMap<>();
			
			cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[M * 2];
			
			Arrays.fill(parent, -1);
			
			for(int i = 0; i < M; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				String fa, fb;
				
				int faNum, fbNum;
				
				fa = st.nextToken();
				
				if(map.containsKey(fa) == false)
				{
					map.put(fa, cnt);
					
					cnt++;
				}
				
				faNum = map.get(fa);
				
				fb = st.nextToken();
				
				if(map.containsKey(fb) == false)
				{
					map.put(fb, cnt);
					
					cnt++;
				}
				
				fbNum = map.get(fb);
				
				int faParent = find(faNum);
				
				int fbParent = find(fbNum);
				
				if(faParent != fbParent)
				{
					union(faNum, fbNum);
					
				}
				
				bw.write(-(parent[find(fbNum)]) + " ");
				bw.newLine();
			}
			
//			for(int x : parent)
//			{
//				System.out.print(x + " ");
//			}
//			System.out.println();
		}
		
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
		if(parent[a] < 0 )
			return a;
		
		return parent[a] = find(parent[a]);
	}

}
