package Day02_BOJ_2143;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2143 {

	static int T, lenA, lenB;
	static long ans;
	
	static int[] A, B;
	
	static List<Integer> A_sub, B_sub;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\\\Users\\\\KIUK\\\\eclipse-workspace\\\\Samsung_SDS\\\\src\\\\Day02_BOJ_2143\\\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		lenA = Integer.parseInt(st.nextToken());
		
		A = new int[lenA];
		
		A_sub = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < lenA; i++)
		{
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		lenB = Integer.parseInt(st.nextToken());
		
		B = new int[lenB];
		
		B_sub = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < lenB; i++)
		{
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// �ι迭 ���� ������ֱ� (O(N^2))
		make_sub_arr();
		
		// A �ι迭 �������ֱ� (O (N^2 log N ) ) // ���� ���� ����
		Collections.sort(A_sub, (o1, o2) -> o1 - o2);
		
		// B �ι迭 �������ֱ� // �������� ����
		Collections.sort(B_sub, (o1, o2) -> o2 - o1);
		
		int pointA = 0;
		int pointB = 0;
		long sum = 0;
		while(true)
		{
			sum = A_sub.get(pointA) + B_sub.get(pointB);
			
			// ���� �� ���� ���Ѱ� �۴ٸ�
			// A�ι迭 ������ ���� (���� ��������)
			if(sum < T)
			{
				pointA++;
			}
			
			// ���� �� ���� ���Ѱ� ũ�ٸ�
			// B�ι迭 ������ ���� (���� �������)
			else if(sum > T)
			{
				pointB++;
			}
			
			// ���� ���� T�� ���ٸ� ���� �����ش�.
			else {
				
				// ���� ����ƮA�� ���� ���� ������ ã������Ѵ�.
				int cnt_pa = 0;
				
				for(int i = pointA; i < A_sub.size(); i++)
				{
					int A_value = A_sub.get(i);
					
					if(A_sub.get(pointA) == A_value)
					{
						cnt_pa++;
					}
					
					else {
						break;
					}
				}
				
				int cnt_pb = 0;
				
				for(int i = pointB; i < B_sub.size(); i++)
				{
					int B_value = B_sub.get(i);
					
					if(B_sub.get(pointB) == B_value)
						cnt_pb++;
					
					else {
						break;
					}
				}
				
				ans += ((long)cnt_pa * (long)cnt_pb);
				
				pointA += cnt_pa;
				pointB += cnt_pb;
			}
			
			// ��������
			if(pointA >= A_sub.size() || pointB >= B_sub.size())
				break;
		}
		
		System.out.println(ans);
	}
	
	static void make_sub_arr()
	{
		// A �ι迭 �����
		for(int i = 0; i < A.length; i++)
		{
			int sum = 0;
			for(int j = i; j < A.length; j++)
			{
				sum += A[j];
				
				A_sub.add(sum);
			}
		}
		
		// B �ι迭 �����
		for(int i = 0; i < B.length; i++)
		{
			int sum = 0;
			for(int j = i; j < B.length; j++)
			{
				sum += B[j];
				
				B_sub.add(sum);
			}
		}
	}

}
