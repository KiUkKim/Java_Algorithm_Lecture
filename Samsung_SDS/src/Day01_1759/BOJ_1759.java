package Day01_1759;

import java.util.*;
import java.io.*;

public class BOJ_1759 {


	// 자음 : cons, 모음 : vowl
	
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
		
//		 * 1. 체크인 : visited, 현재까지 만들어진 암호
		if(idx >= 0)
		{
			password += pass_.get(idx);	
		}
//		 * 2. 목적지 인지 확인 : length == L -> 암호 출력
		if(len == L)
		{
			int vowl_cnt = check_func();
			int cons_cnt = password.length() - vowl_cnt;
			
			if(vowl_cnt >= 1 && cons_cnt >= 2)
			{
				sb.append(password + "\n");
			}

		}
//		 * 3. 연결된 구간 방문 : current + 1 ~ C
		else {
			for(int i = idx + 1; i < pass_.size(); i++)
			{
//				 * 4. 갈 수 있는가? : 자음, 모음 개수에 따라서 달라짐
				// 만약 현재 Len - 1인데, 모음이 1개도 없다면 무조건 모음이 있어야한다.
				
//				* 5. 간다. 자음, 모음 몇갠지 
				dfs(i, len + 1);
			}	
		}
		
//		 * 6. 체크아웃
		if(idx >= 0)
		{
			password = password.substring(0, password.length() - 1);
		}
	}
	
	static int check_func()
	{
		// 자음 모음 개수 따져주기
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
