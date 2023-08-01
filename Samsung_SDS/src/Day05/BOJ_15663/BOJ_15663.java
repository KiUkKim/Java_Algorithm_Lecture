package Day05.BOJ_15663;

import java.util.*;
import java.io.*;

public class BOJ_15663 {

	static final int MAX = 10001;
	
	static int N, M;
	static int[] arr;
	static List<Integer> list;
	static StringBuilder sb;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		
		list = new ArrayList<>();
		
		arr = new int[N];
		
		visited = new boolean[MAX];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		dfs();
		
		System.out.println(sb.toString());
	}
	
	static void dfs()
	{
		int prev = 0;
		
		// 목적지인가
		if(list.size() == M)
		{
			for(int num : list)
			{
				sb.append(num + " ");
			}
			
			sb.append("\n");
			
			return;
		}
		
		for(int i = 0; i < N; i++)
        {
            if(!visited[i] && prev != arr[i])
            {
                visited[i] = true;
                prev = arr[i];
                list.add(arr[i]);
                dfs();
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
	}

}
