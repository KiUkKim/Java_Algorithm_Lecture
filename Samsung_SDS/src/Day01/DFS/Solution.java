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
		// 1. üũ�� : ���ڻ��, ��� ���� ��
		visited[idx] = true;
		selectedCount++;
		// 2. �������ΰ�? ��� ���ڼ� == K;
		if(selectedCount == K)
		{
			// �� Ȯ��
			max = Math.max(countWord(), max);
		}
		
		else {
		
			// 3. ����� ���� ��ȸ : index + 1 ~ 'z';
			for (int i = idx+1; i < 26; i++) {
				// 4. �� �� �ִ� �� : ���õ��� ���� ����
				if(visited[i] == false)
				{
					// 5. ����.
					dfs(i);
				}
			}
		}
		
		
		// 6. üũ�ƿ� : ���ڻ��, ��� ���� ��
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
