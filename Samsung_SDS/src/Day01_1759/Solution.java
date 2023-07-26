package Day01_1759;

import java.util.*;
import java.io.*;

public class Solution {

	static int L, C;
	static int[] data;
	static StringBuilder sb;
	static ArrayList<String> result;
	
	public static void main(String[] args) throws FileNotFoundException, Exception{
		
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\eclipse-workspace\\Samsung_SDS\\src\\Day01_1759\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		result = new ArrayList<String>();
		
		L = Integer.parseInt(st.nextToken());
		
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		data = new int[C];
		
		for(int i = 0; i < C; i++)
		{
			data[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(data);
		
		dfs(-1, 0, 0);
	
		System.out.println(result.get(0));
//		for(String str : result)
//		{
//			System.out.println(str);
//		}
	}
	
	static void dfs(int current, int con, int vow)
	{
		
//		 * 1. 체크인 : visited, 현재까지 만들어진 암호
		if(current >= 0)
		{
			sb.append(data[current]);
		}
		
//		 * 2. 목적지 인지 확인 : length == L -> 암호 출력
		if(sb.length() == L)
		{
			if(vow >= 1 && con >= 2)
			{
				result.add(sb.toString());
			}

		}
//		 * 3. 연결된 구간 방문 : current + 1 ~ C
		else {
			for(int i = current + 1; i < C; i++)
			{
//				 * 4. 갈 수 있는가? : 자음, 모음 개수에 따라서 달라짐
				// 만약 현재 Len - 1인데, 모음이 1개도 없다면 무조건 모음이 있어야한다.
				if(data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u')
				{
					dfs(i, con, vow + 1);
				}
//				* 5. 간다. 자음, 모음 몇갠지 
				else {
					dfs(i, con+1, vow);
				}
			}	
		}
		
//		 * 6. 체크아웃
		if(current >= 0)
		{
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
