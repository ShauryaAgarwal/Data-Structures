import java.util.*;
import java.io.*;

public class TheMatrix
{
	public static void main(String[]args)
	{
		File matrixText = new File("C:\\Users\\10015327\\Downloads\\MatrixInput.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(matrixText));
			String contentLine = br.readLine();
			while(contentLine != null)
			{
				String[] matrices = contentLine.split("\t");
				String firstMatrix = matrices[0];
				String secondMatrix = matrices[1];

				int[][] firstMatrixArray = stringTo2DArray(firstMatrix);
				int[][] secondMatrixArray = stringTo2DArray(secondMatrix);

				//Printing matrices
				System.out.println("Matrices: " + firstMatrix + " & " + secondMatrix);
				System.out.println("Matrix 1:");
				for(int i = 0; i < firstMatrixArray.length; i++)
				{
					for(int j = 0; j < firstMatrixArray[0].length; j++)
					{
						System.out.print(firstMatrixArray[i][j] + "\t");
					}
					System.out.println();
				}
				System.out.println("Matrix 2:");
				for(int i = 0; i < secondMatrixArray.length; i++)
				{
					for(int j = 0; j < secondMatrixArray[0].length; j++)
					{
						System.out.print(secondMatrixArray[i][j] + "\t");
					}
					System.out.println();
				}

				//Matrix addition and subtraction
				if((firstMatrixArray.length == secondMatrixArray.length) && (firstMatrixArray[0].length == secondMatrixArray[0].length))
				{
					int[][] matrixSum = new int[firstMatrixArray.length][firstMatrixArray[0].length];
					int[][] matrixDiff = new int[firstMatrixArray.length][firstMatrixArray[0].length];
					for(int i = 0; i < firstMatrixArray.length; i++)
					{
						for(int j = 0; j < firstMatrixArray[0].length; j++)
						{
							matrixSum[i][j] = firstMatrixArray[i][j] + secondMatrixArray[i][j];
							matrixDiff[i][j] = firstMatrixArray[i][j] - secondMatrixArray[i][j];
						}
					}
					System.out.println("Matrix Sum:");
					for(int i = 0; i < firstMatrixArray.length; i++)
					{
						for(int j = 0; j < firstMatrixArray[0].length; j++)
						{
							System.out.print(matrixSum[i][j] + "\t");
						}
						System.out.println();
					}
					System.out.println("Matrix Difference:");
					for(int i = 0; i < firstMatrixArray.length; i++)
					{
						for(int j = 0; j < firstMatrixArray[0].length; j++)
						{
							System.out.print(matrixDiff[i][j] + "\t");
						}
						System.out.println();
					}
				}
				else
				{
					System.out.println("Matrix addition not possible");
					System.out.println("Matrix subtraction not possible");
				}

				//Matrix multiplication
				if(firstMatrixArray.length == secondMatrixArray[0].length)
				{
					int[][] matrixProduct = new int[firstMatrixArray.length][secondMatrixArray[0].length];
					for(int i = 0; i < matrixProduct.length; i++)
					{
						for(int j = 0; j < matrixProduct[0].length; j++)
						{
							for(int k = 0; k < firstMatrixArray[0].length; k++)
							{
								matrixProduct[i][j] +=  firstMatrixArray[i][k] * secondMatrixArray[k][j];
							}
						}
					}
					System.out.println("Matrix Product:");
					for(int i = 0; i < matrixProduct.length; i++)
					{
						for(int j = 0; j < matrixProduct[0].length; j++)
						{
							System.out.print(matrixProduct[i][j] + "\t");
						}
						System.out.println();
					}
				}
				else
				{
					System.out.println("Matrix multiplication not possible");
				}

				System.out.println();
				contentLine = br.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println("File not found");
			e.printStackTrace();
		}
	}

	public static int[][] stringTo2DArray(String s)
	{
		int[][] arr = new int[0][0];
		String[] rows = s.split("},\\{");
		rows[0] = rows[0].substring(2, rows[0].length());
		rows[rows.length-1] = rows[rows.length-1].substring(0, rows[rows.length-1].length()-2);
		for(int i = 0; i < rows.length; i++)
		{
			String[] cols = rows[i].split(",");
			if(i == 0)
				arr = new int[rows.length][cols.length];
			for(int j = 0; j < cols.length; j++)
			{
				arr[i][j] = Integer.valueOf(cols[j]);
			}
		}
		return arr;
	}
}