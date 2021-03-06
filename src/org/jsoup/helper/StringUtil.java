package org.jsoup.helper;

import java.util.Collection;
import java.util.Iterator;

public final class StringUtil
{
  private static final String[] padding = { "", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          " };
  
  public static boolean in(String paramString, String... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      if (paramVarArgs[i].equals(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isBlank(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    for (;;)
    {
      return true;
      int j = paramString.length();
      int i = 0;
      while (i < j)
      {
        if (!isWhitespace(paramString.codePointAt(i))) {
          return false;
        }
        i += 1;
      }
    }
  }
  
  public static boolean isNumeric(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return false;
    }
    int j = paramString.length();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label43;
      }
      if (!Character.isDigit(paramString.codePointAt(i))) {
        break;
      }
      i += 1;
    }
    label43:
    return true;
  }
  
  public static boolean isWhitespace(int paramInt)
  {
    return (paramInt == 32) || (paramInt == 9) || (paramInt == 10) || (paramInt == 12) || (paramInt == 13);
  }
  
  public static String join(Collection paramCollection, String paramString)
  {
    return join(paramCollection.iterator(), paramString);
  }
  
  public static String join(Iterator paramIterator, String paramString)
  {
    if (!paramIterator.hasNext()) {
      localObject = "";
    }
    String str;
    do
    {
      return (String)localObject;
      str = paramIterator.next().toString();
      localObject = str;
    } while (!paramIterator.hasNext());
    Object localObject = new StringBuilder(64).append(str);
    while (paramIterator.hasNext())
    {
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(paramIterator.next());
    }
    return ((StringBuilder)localObject).toString();
  }
  
  public static String normaliseWhitespace(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    int j = 0;
    int i = 0;
    int m = paramString.length();
    int k = 0;
    if (k < m)
    {
      int n = paramString.codePointAt(k);
      if (isWhitespace(n)) {
        if (j != 0) {
          i = 1;
        }
      }
      for (;;)
      {
        k += Character.charCount(n);
        break;
        if (n != 32) {
          i = 1;
        }
        localStringBuilder.append(' ');
        j = 1;
        continue;
        localStringBuilder.appendCodePoint(n);
        j = 0;
      }
    }
    if (i != 0) {
      paramString = localStringBuilder.toString();
    }
    return paramString;
  }
  
  public static String padding(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("width must be > 0");
    }
    if (paramInt < padding.length) {
      return padding[paramInt];
    }
    char[] arrayOfChar = new char[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      arrayOfChar[i] = ' ';
      i += 1;
    }
    return String.valueOf(arrayOfChar);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/org/jsoup/helper/StringUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */