package org.jsoup.parser;

import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;

public class TokenQueue
{
  private static final char ESC = '\\';
  private int pos = 0;
  private String queue;
  
  public TokenQueue(String paramString)
  {
    Validate.notNull(paramString);
    this.queue = paramString;
  }
  
  private int remainingLength()
  {
    return this.queue.length() - this.pos;
  }
  
  public static String unescape(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = 0;
    paramString = paramString.toCharArray();
    int k = paramString.length;
    int i = 0;
    if (i < k)
    {
      char c = paramString[i];
      if (c == '\\') {
        if ((j != 0) && (j == 92)) {
          localStringBuilder.append(c);
        }
      }
      for (;;)
      {
        j = c;
        i += 1;
        break;
        localStringBuilder.append(c);
      }
    }
    return localStringBuilder.toString();
  }
  
  public void addFirst(Character paramCharacter)
  {
    addFirst(paramCharacter.toString());
  }
  
  public void addFirst(String paramString)
  {
    this.queue = (paramString + this.queue.substring(this.pos));
    this.pos = 0;
  }
  
  public void advance()
  {
    if (!isEmpty()) {
      this.pos += 1;
    }
  }
  
  public String chompBalanced(char paramChar1, char paramChar2)
  {
    int j = -1;
    int i = -1;
    int i2 = 0;
    int i1 = 0;
    if (isEmpty())
    {
      label18:
      if (i >= 0) {
        return this.queue.substring(j, i);
      }
    }
    else
    {
      Character localCharacter = Character.valueOf(consume());
      int m;
      int k;
      int n;
      if (i1 != 0)
      {
        m = i2;
        k = j;
        if (i1 == 92) {}
      }
      else
      {
        if (!localCharacter.equals(Character.valueOf(paramChar1))) {
          break label159;
        }
        n = i2 + 1;
        m = n;
        k = j;
        if (j == -1)
        {
          k = this.pos;
          m = n;
        }
      }
      for (;;)
      {
        n = i;
        if (m > 0)
        {
          n = i;
          if (i1 != 0) {
            n = this.pos;
          }
        }
        i1 = localCharacter.charValue();
        i2 = m;
        i = n;
        j = k;
        if (m > 0) {
          break;
        }
        i = n;
        j = k;
        break label18;
        label159:
        m = i2;
        k = j;
        if (localCharacter.equals(Character.valueOf(paramChar2)))
        {
          m = i2 - 1;
          k = j;
        }
      }
    }
    return "";
  }
  
  public String chompTo(String paramString)
  {
    String str = consumeTo(paramString);
    matchChomp(paramString);
    return str;
  }
  
  public String chompToIgnoreCase(String paramString)
  {
    String str = consumeToIgnoreCase(paramString);
    matchChomp(paramString);
    return str;
  }
  
  public char consume()
  {
    String str = this.queue;
    int i = this.pos;
    this.pos = (i + 1);
    return str.charAt(i);
  }
  
  public void consume(String paramString)
  {
    if (!matches(paramString)) {
      throw new IllegalStateException("Queue did not match expected sequence");
    }
    int i = paramString.length();
    if (i > remainingLength()) {
      throw new IllegalStateException("Queue not long enough to consume sequence");
    }
    this.pos += i;
  }
  
  public String consumeAttributeKey()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      if (!matchesWord()) {
        if (!matchesAny(new char[] { 45, 95, 58 })) {
          break;
        }
      }
      this.pos += 1;
    }
    return this.queue.substring(i, this.pos);
  }
  
  public String consumeCssIdentifier()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      if (!matchesWord()) {
        if (!matchesAny(new char[] { 45, 95 })) {
          break;
        }
      }
      this.pos += 1;
    }
    return this.queue.substring(i, this.pos);
  }
  
  public String consumeElementSelector()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      if (!matchesWord()) {
        if (!matchesAny(new char[] { 124, 95, 45 })) {
          break;
        }
      }
      this.pos += 1;
    }
    return this.queue.substring(i, this.pos);
  }
  
  public String consumeTagName()
  {
    int i = this.pos;
    while (!isEmpty())
    {
      if (!matchesWord()) {
        if (!matchesAny(new char[] { 58, 95, 45 })) {
          break;
        }
      }
      this.pos += 1;
    }
    return this.queue.substring(i, this.pos);
  }
  
  public String consumeTo(String paramString)
  {
    int i = this.queue.indexOf(paramString, this.pos);
    if (i != -1)
    {
      paramString = this.queue.substring(this.pos, i);
      this.pos += paramString.length();
      return paramString;
    }
    return remainder();
  }
  
  public String consumeToAny(String... paramVarArgs)
  {
    int i = this.pos;
    while ((!isEmpty()) && (!matchesAny(paramVarArgs))) {
      this.pos += 1;
    }
    return this.queue.substring(i, this.pos);
  }
  
  public String consumeToIgnoreCase(String paramString)
  {
    int i = this.pos;
    String str = paramString.substring(0, 1);
    boolean bool = str.toLowerCase().equals(str.toUpperCase());
    for (;;)
    {
      if ((isEmpty()) || (matches(paramString))) {
        return this.queue.substring(i, this.pos);
      }
      if (bool)
      {
        int j = this.queue.indexOf(str, this.pos) - this.pos;
        if (j == 0) {
          this.pos += 1;
        } else if (j < 0) {
          this.pos = this.queue.length();
        } else {
          this.pos += j;
        }
      }
      else
      {
        this.pos += 1;
      }
    }
  }
  
  public boolean consumeWhitespace()
  {
    for (boolean bool = false; matchesWhitespace(); bool = true) {
      this.pos += 1;
    }
    return bool;
  }
  
  public String consumeWord()
  {
    int i = this.pos;
    while (matchesWord()) {
      this.pos += 1;
    }
    return this.queue.substring(i, this.pos);
  }
  
  public boolean isEmpty()
  {
    return remainingLength() == 0;
  }
  
  public boolean matchChomp(String paramString)
  {
    if (matches(paramString))
    {
      this.pos += paramString.length();
      return true;
    }
    return false;
  }
  
  public boolean matches(String paramString)
  {
    return this.queue.regionMatches(true, this.pos, paramString, 0, paramString.length());
  }
  
  public boolean matchesAny(char... paramVarArgs)
  {
    if (isEmpty()) {}
    for (;;)
    {
      return false;
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        int k = paramVarArgs[i];
        if (this.queue.charAt(this.pos) == k) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public boolean matchesAny(String... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      if (matches(paramVarArgs[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public boolean matchesCS(String paramString)
  {
    return this.queue.startsWith(paramString, this.pos);
  }
  
  public boolean matchesStartTag()
  {
    return (remainingLength() >= 2) && (this.queue.charAt(this.pos) == '<') && (Character.isLetter(this.queue.charAt(this.pos + 1)));
  }
  
  public boolean matchesWhitespace()
  {
    return (!isEmpty()) && (StringUtil.isWhitespace(this.queue.charAt(this.pos)));
  }
  
  public boolean matchesWord()
  {
    return (!isEmpty()) && (Character.isLetterOrDigit(this.queue.charAt(this.pos)));
  }
  
  public char peek()
  {
    if (isEmpty()) {
      return '\000';
    }
    return this.queue.charAt(this.pos);
  }
  
  public String remainder()
  {
    String str = this.queue.substring(this.pos, this.queue.length());
    this.pos = this.queue.length();
    return str;
  }
  
  public String toString()
  {
    return this.queue.substring(this.pos);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/org/jsoup/parser/TokenQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */