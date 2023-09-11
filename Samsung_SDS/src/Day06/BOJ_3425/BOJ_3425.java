package Day06.BOJ_3425;

import java.util.*;
import java.io.*;

public class BOJ_3425 {
	
	static final long MAX = 1000000000;
	static Stack<Long> st;
	static List<Integer> arr, arrTmp;
	static List<String> cmdList;
	static StringTokenizer srt;
	static boolean CanAction;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true)
		{
			// 명령어 조합
			// Num x -> x를 스택 위에 올림
			// pop -> 가장 위를 제거
			// INV -> 첫번쨰 수의 부호 바꿈 ( 42 -> -42) 생각 : 이부분은 x * 2 + x -> 변환이된다.
			// DUP -> 첫번쨰 숫자 복사해서 위로올림
			// swp -> 첫번쨰 숫자와 두번째 숫자 바꿈
			// ADD -> 첫번째 두번쨰 숫자 더함
			// SUB -> 두번째 - 첫번째
			// MUL -> 첫번쟤 * 두번쨰
			// DIV -> 첫번쨰 숫자로 두번쨰 숫자를 나눈다. // 음수가 하나일때 부호 음수 ex) 13 div -4 = -3
			// MOD -> 첫번쨰 숫자로 두번쨰 숫자 나눈 나머지  // 피제수의 부호와 같음 ex) -13 mod -4 = -1 -13 mod 4 = -1
			// 숫자가 부족해서 연산 x or 0으로 나눌떄, 절댓값이 10^9을 넘어갈때 모두 에러
			cmdList = new ArrayList<>();
			arr = new ArrayList<>();
			boolean flag = false;
			
			while(true)
			{
				srt = new StringTokenizer(br.readLine());

				String cmd = srt.nextToken();
				
//				System.out.println(cmd);
				
				if(cmd.equals("QUIT"))
				{
					flag = true;
					break;
				}
				
				else if(cmd.equals("END"))
					break;
				
				else if(cmd.equals("NUM"))
				{
					arr.add(Integer.parseInt(srt.nextToken()));
				}

				
				cmdList.add(cmd);	
			}
			
			if(flag)
				break;
			
			else {
				// 여기서부터 스택을 계산한다.	
				srt = new StringTokenizer(br.readLine());
				
				int N = Integer.parseInt(srt.nextToken());
				
				for(int i = 0; i < N; i++)
				{
					CanAction = true;
					
					srt = new StringTokenizer(br.readLine());
					
					st = new Stack<>();
					
					arrTmp = new ArrayList<>(arr);
					
					st.add(Long.parseLong(srt.nextToken()));
					
					// 명령어 순서대로 실행
					for(String cmd : cmdList)
					{
						action_(cmd);
						
//						System.out.println("check_ : " + CanAction);
						
						// 명령어에서 오류가 발생한 경우
						if(!CanAction)
							break;
					}
					
//					System.out.println();
					
					// 마지막에 저장된 수가 2개이상이거나 오류가 발생한경우 
					if(st.size() > 1 || !CanAction || st.isEmpty())
					{
						sb.append("ERROR\n");
					}
					
					else {
						sb.append(st.peek() + "\n");
					}
				}
			}
			
			sb.append("\n");
			
			
			srt = new StringTokenizer(br.readLine());
		}
		
		System.out.println(sb.toString());
	}
	
	static void action_(String cmd) {
		// 명령어 조합
		// 숫자가 부족해서 연산 x or 0으로 나눌떄, 절댓값이 10^9을 넘어갈때 모두 에러
		
		// Num x -> x를 스택 위에 올림
		if(cmd.equals("NUM"))
		{
			if(arrTmp.size() > 0)
			{
				st.add((long)arrTmp.get(0));				
				arrTmp.remove(0);
//				System.out.println("peek" + st.peek());
			}
			
		}
		
		// pop -> 가장 위를 제거
		else if(cmd.equals("POP"))
		{
			if(st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			st.pop();
		}
		
		// INV -> 첫번쨰 수의 부호 바꿈 ( 42 -> -42) 생각 : 이부분은 x * 2 + x -> 변환이된다.
		else if(cmd.equals("INV"))
		{
			if(st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			long num = st.pop();
			
			// 양수일때
			if(num > 0)
			{
				num = num - (num * 2);
			}
			
			else if(num < 0)
			{
				num = Math.abs(num) * 2 + num;
			}

			st.add(num);
		}
		
		// DUP -> 첫번쨰 숫자 복사해서 위로올림
		else if(cmd.equals("DUP"))
		{
			if(st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			long num = st.pop();
			
			st.add(num);
			st.add(num);
			
		}
		
		// swp -> 첫번쨰 숫자와 두번째 숫자 바꿈
		else if(cmd.equals("SWP"))
		{
			if(st.size() < 2 || st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			long tmp = st.pop();
			
			long SwapNum = st.pop();
			
			st.add(tmp);
			
			st.add(SwapNum);
		}
		
		// ADD -> 첫번째 두번쨰 숫자 더함
		else if(cmd.equals("ADD"))
		{
			if(st.size() < 2 || st.isEmpty())
			{
				CanAction = false;
//				System.out.println("X " + st.peek());
				return;
			}
			
			long PeekNum = st.pop();
			
			long NextNum = st.pop();
			
			if(Math.abs(NextNum + PeekNum) > MAX)
			{
				CanAction = false;
//				System.out.println(CanAction);
				return;
			}
			
			st.add(PeekNum + NextNum);			
		}
		
		// SUB -> 두번째 - 첫번째
		else if(cmd.equals("SUB"))
		{
			if(st.size() < 2 || st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			long PeekNum = st.pop();
			
			long NextNum = st.pop();
			
			if(Math.abs(NextNum - PeekNum) > MAX)
			{
				CanAction = false;
				return;
			}
			
			st.add(NextNum - PeekNum);
		}
		
		// MUL -> 첫번쟤 * 두번쨰
		else if(cmd.equals("MUL"))
		{
			if(st.size() < 2 || st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			long PeekNum = st.pop();
			
			long NextNum = st.pop();
			
			if(Math.abs(NextNum * PeekNum) > MAX)
			{
				CanAction = false;
				return;
			}
						
			st.add(NextNum * PeekNum);
		}
		
		// 두번쨰 숫자 / 첫번째 숫자
		// DIV -> 첫번쨰 숫자로 두번쨰 숫자를 나눈다. // 음수가 하나일때 부호 음수 ex) 13 div -4 = -3
		else if(cmd.equals("DIV"))
		{
			boolean flag1 = true, flag2 = true;
			// 피연산자 중 하나만 음수일경우 음수로 변환이 필요하다.
			if(st.size() < 2 || st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			long PeekNum = st.pop();
			
			long NextNum = st.pop();
			
			if(PeekNum == 0)
			{
				CanAction = false;
				return;
			}
			
			if(PeekNum < 0)
			{
				flag1 = false;
			}
			
			if(NextNum < 0)
			{
				flag2 = false;
			}
			
			long result = Math.abs(NextNum) / Math.abs(PeekNum);
			
			// 한쪽이 음수일 경우
			if((flag1 && !flag2) || (!flag1 && flag2) )
			{
				// 양수 -> 음수로 변환 과정이 필요하다
				result = result - (result * 2);
				
				st.add(result);
			}
			
			// 아닐 경우
			else {
				st.add(result);
			}
 		}
		
		// 피제수란 두번째 숫자를 의미한다.
		// MOD -> 첫번쨰 숫자로 두번쨰 숫자 나눈 나머지  // 피제수의 부호와 같음 ex) -13 mod -4 = -1 -13 mod 4 = -1
		else if(cmd.equals("MOD"))
		{
			// 피연산자 중 하나만 음수일경우 음수로 변환이 필요하다.
			boolean flag1 = true;

			if(st.size() < 2 || st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			long PeekNum = st.pop();
			
			long NextNum = st.pop();
			
			if(PeekNum == 0)
			{
				CanAction = false;
				return;
			}
			
			if(NextNum < 0)
			{
				flag1 = false;
			}
			
			long result = Math.abs(NextNum) % Math.abs(PeekNum);
			
			// 피제수가 음수일 경우
			if(!flag1)
			{
				// 양수 -> 음수의 과정 필요
				result = result - (result * 2);
				st.add(result);
			}
			// 아닐 경우
			else {
				st.add(result);
			}
		}
	}

}
