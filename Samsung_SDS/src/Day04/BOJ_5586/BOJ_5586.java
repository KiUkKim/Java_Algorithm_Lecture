package Day04.BOJ_5586;

import java.util.*;
import java.io.*;

public class BOJ_5586 {

	static int n, k;
	
	static Set<Integer> set;
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		set = new HashSet<>();
		
		n = Integer.parseInt(st.nextToken());
		
		k = Integer.parseInt(br.readLine());
		
		arr = new int[11];

		visited = new boolean[11];
		
		for(int i = 0; i < n; i++)
		{
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		backt(0, 0);
		
		System.out.println(set.size());
	}

	static void backt(int cnt, int ret)
	{
		if(cnt == k)
		{
			set.add(ret);
			return;
		}
		
		for(int i = 0; i < n; i++)
		{
			if(visited[i] == true)
				continue;
			
			int temp = arr[i] >= 10 ? 100 : 10;
			visited[i] = true;
			backt(cnt + 1, ret * temp + arr[i]);
			visited[i] = false;
		}
	}
}
