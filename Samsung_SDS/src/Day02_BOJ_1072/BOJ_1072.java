package Day02_BOJ_1072;

import java.io.*;
import java.util.*;

public class BOJ_1072 {

	static long X, Y, Z;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\\\Users\\\\KIUK\\\\eclipse-workspace\\\\Samsung_SDS\\\\src\\\\Day02_BOJ_1072\\\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Long.parseLong(st.nextToken());
		
		Y = Long.parseLong(st.nextToken());
		
		Z = (Y * 100) / X;
		
		long start = 1;
		long end = X;
		long ans = -1;
		
		while(start <= end)
		{
			long mid = (start + end) / 2;
			
			long result = (100 * (Y + mid)) / (X + mid);
			
			// 해당 숫자랑 같다면 게임을 더 많이해야한다.
			if(result <= Z)
			{
				start = mid + 1;
			}
			
			// 커진다면 범위를 줄여야한다.
			else if(result > Z)
			{
				end = mid - 1;
				
				ans = mid;
			}
		}
		
		if(ans != -1)
		{
			System.out.println(ans);	
		}
		else {
			System.out.println(-1);
		}
		
		// 현재까지 한 게임만큼했는데 안오르면 오르지 않는다.
	}

}
