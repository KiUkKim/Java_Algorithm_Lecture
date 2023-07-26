package Day01_1759;

import java.util.*;
import java.io.*;

public class BOJ_1759 {


	// ���� : cons, ���� : vowl
	
	static int L, C;
	static ArrayList<Character> pass_;
	static Character[] vowls = {'a', 'e', 'i', 'o', 'u'};
	static StringBuilder sb;
	static String password = "";
	
	public static void main(String[] args) throws FileNotFoundException, Exception{
		
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\eclipse-workspace\\Samsung_SDS\\src\\Day01_1759\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		L = Integer.parseInt(st.nextToken());
		
		C = Integer.parseInt(st.nextToken());
		
		pass_ = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < C; i++)
		{
			pass_.add(st.nextToken().charAt(0));
		}
		
		Collections.sort(pass_);
		
//		for(int i = 0; i < pass_.size(); i++)
//		{
//			dfs(i, 1);
//		}
		
		dfs(-1, 0);
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int idx, int len)
	{
		
//		 * 1. üũ�� : visited, ������� ������� ��ȣ
		if(idx >= 0)
		{
			password += pass_.get(idx);	
		}
//		 * 2. ������ ���� Ȯ�� : length == L -> ��ȣ ���
		if(len == L)
		{
			int vowl_cnt = check_func();
			int cons_cnt = password.length() - vowl_cnt;
			
			if(vowl_cnt >= 1 && cons_cnt >= 2)
			{
				sb.append(password + "\n");
			}

		}
//		 * 3. ����� ���� �湮 : current + 1 ~ C
		else {
			for(int i = idx + 1; i < pass_.size(); i++)
			{
//				 * 4. �� �� �ִ°�? : ����, ���� ������ ���� �޶���
				// ���� ���� Len - 1�ε�, ������ 1���� ���ٸ� ������ ������ �־���Ѵ�.
				
//				* 5. ����. ����, ���� ��� 
				dfs(i, len + 1);
			}	
		}
		
//		 * 6. üũ�ƿ�
		if(idx >= 0)
		{
			password = password.substring(0, password.length() - 1);
		}
	}
	
	static int check_func()
	{
		// ���� ���� ���� �����ֱ�
		int cnt = 0;
		
		for(int i = 0; i < password.length(); i++)
		{
			for(int j = 0; j < vowls.length; j++)
			{
				if(password.charAt(i) == vowls[j])
				{
					cnt++;
					break;
				}
			}
		}
		
		return cnt;
	}	
}
