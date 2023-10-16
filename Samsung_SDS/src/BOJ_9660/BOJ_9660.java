package BOJ_9660;

import java.util.*;
import java.awt.print.PrinterIOException;
import java.io.*;

public class BOJ_9660 {

	static long N;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Long.parseLong(st.nextToken());
		
		bw.write((N % 7 == 0) || (N % 7 == 2) ? "CY" : "SK");
		bw.flush();
		bw.close();

	}

}
