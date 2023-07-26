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
		
//		 * 1. üũ�� : visited, ������� ������� ��ȣ
		if(current >= 0)
		{
			sb.append(data[current]);
		}
		
//		 * 2. ������ ���� Ȯ�� : length == L -> ��ȣ ���
		if(sb.length() == L)
		{
			if(vow >= 1 && con >= 2)
			{
				result.add(sb.toString());
			}

		}
//		 * 3. ����� ���� �湮 : current + 1 ~ C
		else {
			for(int i = current + 1; i < C; i++)
			{
//				 * 4. �� �� �ִ°�? : ����, ���� ������ ���� �޶���
				// ���� ���� Len - 1�ε�, ������ 1���� ���ٸ� ������ ������ �־���Ѵ�.
				if(data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u')
				{
					dfs(i, con, vow + 1);
				}
//				* 5. ����. ����, ���� ��� 
				else {
					dfs(i, con+1, vow);
				}
			}	
		}
		
//		 * 6. üũ�ƿ�
		if(current >= 0)
		{
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
