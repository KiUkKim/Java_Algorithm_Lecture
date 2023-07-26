package Day2.BOJ_1713;

import java.util.*;
import java.io.*;

public class BOJ_1713 {

	static final int max = 1001;
	static int N, S;
	
	static Student[] students;
	static List<Student> frame_;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());	
		
		students = new Student[101];
		
		frame_ = new ArrayList<>();
		
		for(int i = 0; i < S; i++)
		{
			int num = Integer.parseInt(st.nextToken());
			
			// 만약 아직 추천받지 않은 사람일 경우
			if(students[num] == null)
			{
				students[num] = new Student(num, 0, 0);
			}
			
			
			// 만약 현재 게시된 경우라면
			if(students[num].cnt != 0 || students[num].turn != 0)
			{
				students[num].cnt++;
			}
			
			// 게시되지 않았다면
			else {
				
				// 만약 해당 frame의 크기가 다 찼을 경우 
				if(frame_.size() == N)
				{
					Collections.sort(frame_, (o1, o2) -> {
						if(o1.cnt == o2.cnt)
							return o1.turn - o2.turn;
						
						return o1.cnt - o2.cnt;
					});
					
					int tmp_num = frame_.get(0).num;
					
					students[tmp_num].cnt = 0;
					students[tmp_num].turn = 0;
					
					frame_.remove(0);
				}
				
				students[num].cnt++;
				students[num].turn = i;
				frame_.add(students[num]);
			}
		}
		
		Collections.sort(frame_, (o1, o2) -> o1.num - o2.num);
		
		for(Student s : frame_)
		{
			System.out.print(s.num + " ");
		}
	}
}

class Student
{
	int num;
	int turn;
	int cnt;
	
	Student(int num, int turn, int cnt)
	{
		super();
		this.num = num;
		this.turn = turn;
		this.cnt = cnt;
	}
}