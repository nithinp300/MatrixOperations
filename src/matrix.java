import java.util.Arrays;
import java.util.Scanner;

public class matrix {

	public static void main(String[] args) {
		int[] arr = {10, 20, 30, 40, 30};
		int size = arr.length;
		int[][] mem = new int[size][size];
		System.out.println("MMult solution: " + MMult(1, size-1, arr, mem));
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Matrix Calculator");
		System.out.println("Types of opeations: add(a), subtract(s), negative(n), scalar multiplication(sc), multiplication(m), inverse(i), transpose(t)");
		System.out.print("Type of operation:");
		String type = sc.next();
		
		System.out.print("Enter num Rows: ");
		int numRows = sc.nextInt();
		System.out.print("Enter num Columms: ");
		int numCols = sc.nextInt();
		
		int[][] a = new int[numRows][numCols];
		int numElements = numRows * numCols;
		
		System.out.println("Enter "+ numElements +" element Matrix A:");
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numCols; c++)
			{
				a[r][c] = sc.nextInt();
			}
		}
		System.out.println("Matrix A is:");
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numCols; c++)
			{
				System.out.print(a[r][c] + "  ");
			}
			System.out.println();
		}
		
		int[][] b = new int[numRows][numCols];
		if(type.equals("a") || type.equals("s") || type.equals("m") )
		{
			if(type.equals("m"))
			{
				System.out.print("Enter Matrix B num Rows: ");
				numRows = sc.nextInt();
				System.out.print("Enter Matrix B num Columms: ");
				numCols = sc.nextInt();
				b = new int[numRows][numCols];
				numElements = numRows * numCols;
			}
			System.out.println("Enter "+ numElements +" element Matrix B:");
			for(int r = 0; r < numRows; r++)
			{
				for(int c = 0; c < numCols; c++)
				{
					b[r][c] = sc.nextInt();
				}
			}
			System.out.println("Matrix B is:");
			for(int r = 0; r < numRows; r++)
			{
				for(int c = 0; c < numCols; c++)
				{
					System.out.print(b[r][c] + "  ");
				}
				System.out.println();
			}
		}
		int scalar = 0;
		if(type.equals("sc"))
		{
			System.out.print("Enter scalar number: ");
			scalar = sc.nextInt();
		}
		switch(type) {
        case "a" :
        	additionSubtraction(a, b, numRows, numCols, type);
        	break;
        case "s" :
        	additionSubtraction(a, b, numRows, numCols, type);
        	break;
        case "n" :
        	negative(a, numRows, numCols);
        	break;
        case "sc" :
        	scalarMultiplication(a, numRows, numCols, scalar);
        	break;
        case "m" :
        	multiplication(a, b);
        	break;
        case "t":
        	transpose(a);
        	break;
        default :
           System.out.println("Invalid input");
     }
		sc.close();
	}

	private static void additionSubtraction(int[][] a, int[][] b, int numRows, int numCols, String type) {
		if(type.equals("a"))
		{
			System.out.println("The addition result is:");
		}
		else
		{
			System.out.println("The subtraction result is:");
		}
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numCols; c++)
			{
				int result = 0;
				if(type.equals("a"))
				{
					result = a[r][c] + b[r][c];
				}
				else
				{
					result = a[r][c] - b[r][c];
				}
				System.out.print(result + "  ");
			}
			System.out.println();
		}
	}
	
	private static void negative(int[][] a, int numRows, int numCols) {
		System.out.println("The negative result is:");
		int result = 0;
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numCols; c++)
			{
				result = -1 * a[r][c];
				System.out.print(result + "  ");
			}
			System.out.println();
		}
	}
	
	private static void scalarMultiplication(int[][] a, int numRows, int numCols, int scalar) {
		System.out.println("The scalar multiplication result is:");
		int result = 0;
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numCols; c++)
			{
				result = scalar * a[r][c];
				System.out.print(result + "  ");
			}
			System.out.println();
		}
	}
	
	private static void multiplication(int[][] a, int[][] b) {
		System.out.println("The multiplication result is:");
		int numRowsA = a.length;
		int numColsA = a[0].length;
		int numRowsB = b.length;
		int numColsB = b[0].length;
		for(int i = 0; i < numRowsA; i++)
		{
			for(int j = 0; j < numColsB; j++)
			{
				int result = 0;
				for(int k = 0; k < numColsA; k++)
				{
					result += a[i][k]*b[k][j];
				}
				System.out.print(result + "  ");
			}
			System.out.println();
		}
		
	}
	
	public static int MMult(int i, int j, int[] mlist, int[][] mem)
	{
		int[] sol = new int[j];
		if(i == j)
		{
			return 0;
		}
		else if(mem[i][j] > 0) //memoization of min count
		{
			return mem[i][j];
		}
		else
		{
			int minSplit = Integer.MAX_VALUE;
			for(int k = i; k < j; k++)
			{
				int trySplitAtK = mlist[i-1] * mlist[k] * mlist[j] + MMult(i, k, mlist, mem) + MMult(k+1, j, mlist, mem);
				//minSplit = Math.min(minSplit, trySplitAtK);
				if(trySplitAtK < minSplit)
				{
					minSplit = trySplitAtK;
				}
			}
			mem[i][j] = minSplit;
			return minSplit;
		}
	}
	
	private static void transpose(int[][] a)
	{
		System.out.println("The transpose is:");
		for(int c = 0; c < a[0].length; c++)
		{
			for(int r = 0; r < a.length; r++)
			{
				System.out.print(a[r][c] + "  ");
			}
			System.out.println();
		}
	}

}
