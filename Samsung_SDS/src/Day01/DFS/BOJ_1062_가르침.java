package Day01.DFS;

import java.util.*;
import java.io.*;

public class BOJ_1062_가르침 {

	static int N, K;
	static ArrayList<String> str;
	static boolean[] alpha = new boolean[26];
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		str = new ArrayList<>();
		
		N = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			str.add(st.nextToken());
		}
		
		// 기본적으로 anta , tica 기본적으로 넣어주기
		Character[] c = {'a', 'c', 'i', 't', 'n'};
		
		for(int i = 0; i < c.length; i++)
		{
			alpha[c[i] - 'a'] = true; 
		}
		
		K -= 5;
		
		if(K < 0)
		{
			System.out.println(0);
		}
		
		else {
			dfs(0, 0);
			
			System.out.println(ans);
		}
	}
	
	static void dfs(int idx, int cnt)
	{
		// DFS 동작 순서

		// 2. 남은 글자의 갯수가 있는지
		if(cnt == K)
		{
			int tmp_cnt = 0;
			
			for(int i = 0; i < str.size(); i++)
			{
				boolean flag = true;
				
				for(int j = 0; j < str.get(i).length(); j++)
				{
					Character c = str.get(i).charAt(j);
					
					if(alpha[c - 'a'] == false)
					{
						flag = false;
						break;
					}
				}
				
				if(flag)
				{
					tmp_cnt++;
				}
			}
			
			ans = Math.max(ans,  tmp_cnt);
			
			return;
		}
		
		if(idx >= 26)
			return;

		// 1. 체크인
		for(int i = idx; i < 26; i++)
		{
			// 3. 연결된 곳 + 갈수 있는지 // 나보다 뒤에 있고, 가르친 적이 없는 경우
			if(alpha[i] == true)
				continue;
			
			alpha[i] = true;
			// 4. 간다
			dfs(i, cnt + 1);
			// 5. 체크아웃 : 가르침 x
			alpha[i] = false;
		}
		
	}

}
