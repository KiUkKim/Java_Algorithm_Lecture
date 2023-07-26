package Day01.DFS;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, K;
	static String[] words;
	static boolean[] visited;
	static int selectedCount = 0;
	static int max = 0;

	public static void main(String[] args) throws FileNotFoundException, Exception{

		System.setIn(new FileInputStream("src\\Day01.DFS\\input.txt"));

		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.next());
		K = Integer.parseInt(sc.next());
		
		words = new String[N];
		
		visited = new boolean[26];
		
		for(int i = 0; i < N; i++)
		{
			words[i] = sc.next().replaceAll("[antic]", "");
		}
		
		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;
		
		for(int i = 0; i < 26; i++)
		{
			if(visited[i] == false)
			{
				dfs(i);
			}
		}
	}

	static void dfs(int idx)
	{
		// 1. 체크인 : 글자사용, 배운 글자 수
		visited[idx] = true;
		selectedCount++;
		// 2. 목적지인가? 배운 글자수 == K;
		if(selectedCount == K)
		{
			// 답 확인
			max = Math.max(countWord(), max);
		}
		
		else {
		
			// 3. 연결된 곳을 순회 : index + 1 ~ 'z';
			for (int i = idx+1; i < 26; i++) {
				// 4. 갈 수 있는 곳 : 선택되지 않은 글자
				if(visited[i] == false)
				{
					// 5. 간다.
					dfs(i);
				}
			}
		}
		
		
		// 6. 체크아웃 : 글자사용, 배운 글자 수
		visited[idx] = false;
		selectedCount--;
	}
	
	static int countWord()
	{
		int count = 0;
		
		for(int i = 0; i < N; i++)
		{
			boolean isReadable = true;
			
			String word = words[i];
			for(int j = 0; j < word.length(); j++)
			{
				if(visited[word.charAt(j) - 'a'] == false)
				{
					isReadable = false;
					break;
				}
			}
			
			if(isReadable)
			{
				count++;
			}
		}
		
		return count;
	}
}
