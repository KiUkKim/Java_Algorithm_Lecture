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
			// ��ɾ� ����
			// Num x -> x�� ���� ���� �ø�
			// pop -> ���� ���� ����
			// INV -> ù���� ���� ��ȣ �ٲ� ( 42 -> -42) ���� : �̺κ��� x * 2 + x -> ��ȯ�̵ȴ�.
			// DUP -> ù���� ���� �����ؼ� ���οø�
			// swp -> ù���� ���ڿ� �ι�° ���� �ٲ�
			// ADD -> ù��° �ι��� ���� ����
			// SUB -> �ι�° - ù��°
			// MUL -> ù���� * �ι���
			// DIV -> ù���� ���ڷ� �ι��� ���ڸ� ������. // ������ �ϳ��϶� ��ȣ ���� ex) 13 div -4 = -3
			// MOD -> ù���� ���ڷ� �ι��� ���� ���� ������  // �������� ��ȣ�� ���� ex) -13 mod -4 = -1 -13 mod 4 = -1
			// ���ڰ� �����ؼ� ���� x or 0���� ������, ������ 10^9�� �Ѿ�� ��� ����
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
				// ���⼭���� ������ ����Ѵ�.	
				srt = new StringTokenizer(br.readLine());
				
				int N = Integer.parseInt(srt.nextToken());
				
				for(int i = 0; i < N; i++)
				{
					CanAction = true;
					
					srt = new StringTokenizer(br.readLine());
					
					st = new Stack<>();
					
					arrTmp = new ArrayList<>(arr);
					
					st.add(Long.parseLong(srt.nextToken()));
					
					// ��ɾ� ������� ����
					for(String cmd : cmdList)
					{
						action_(cmd);
						
//						System.out.println("check_ : " + CanAction);
						
						// ��ɾ�� ������ �߻��� ���
						if(!CanAction)
							break;
					}
					
//					System.out.println();
					
					// �������� ����� ���� 2���̻��̰ų� ������ �߻��Ѱ�� 
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
		// ��ɾ� ����
		// ���ڰ� �����ؼ� ���� x or 0���� ������, ������ 10^9�� �Ѿ�� ��� ����
		
		// Num x -> x�� ���� ���� �ø�
		if(cmd.equals("NUM"))
		{
			if(arrTmp.size() > 0)
			{
				st.add((long)arrTmp.get(0));				
				arrTmp.remove(0);
//				System.out.println("peek" + st.peek());
			}
			
		}
		
		// pop -> ���� ���� ����
		else if(cmd.equals("POP"))
		{
			if(st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			st.pop();
		}
		
		// INV -> ù���� ���� ��ȣ �ٲ� ( 42 -> -42) ���� : �̺κ��� x * 2 + x -> ��ȯ�̵ȴ�.
		else if(cmd.equals("INV"))
		{
			if(st.isEmpty())
			{
				CanAction = false;
				return;
			}
			
			long num = st.pop();
			
			// ����϶�
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
		
		// DUP -> ù���� ���� �����ؼ� ���οø�
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
		
		// swp -> ù���� ���ڿ� �ι�° ���� �ٲ�
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
		
		// ADD -> ù��° �ι��� ���� ����
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
		
		// SUB -> �ι�° - ù��°
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
		
		// MUL -> ù���� * �ι���
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
		
		// �ι��� ���� / ù��° ����
		// DIV -> ù���� ���ڷ� �ι��� ���ڸ� ������. // ������ �ϳ��϶� ��ȣ ���� ex) 13 div -4 = -3
		else if(cmd.equals("DIV"))
		{
			boolean flag1 = true, flag2 = true;
			// �ǿ����� �� �ϳ��� �����ϰ�� ������ ��ȯ�� �ʿ��ϴ�.
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
			
			// ������ ������ ���
			if((flag1 && !flag2) || (!flag1 && flag2) )
			{
				// ��� -> ������ ��ȯ ������ �ʿ��ϴ�
				result = result - (result * 2);
				
				st.add(result);
			}
			
			// �ƴ� ���
			else {
				st.add(result);
			}
 		}
		
		// �������� �ι�° ���ڸ� �ǹ��Ѵ�.
		// MOD -> ù���� ���ڷ� �ι��� ���� ���� ������  // �������� ��ȣ�� ���� ex) -13 mod -4 = -1 -13 mod 4 = -1
		else if(cmd.equals("MOD"))
		{
			// �ǿ����� �� �ϳ��� �����ϰ�� ������ ��ȯ�� �ʿ��ϴ�.
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
			
			// �������� ������ ���
			if(!flag1)
			{
				// ��� -> ������ ���� �ʿ�
				result = result - (result * 2);
				st.add(result);
			}
			// �ƴ� ���
			else {
				st.add(result);
			}
		}
	}

}
