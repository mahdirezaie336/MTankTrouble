package utils;

public class ArrayUtils
{
	public static String[] combineStrings(String[] arr1, String[] arr2)
	{
		int length = arr1.length + arr2.length;
		String[] newArr = new String[length];
		
		int i = 0;
		for(String str : arr1)
			newArr[i++] = str;
		
		for(String str : arr2)
			newArr[i++] = str;
		
		return newArr;
	}
}
