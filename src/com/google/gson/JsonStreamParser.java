package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser
  implements Iterator<JsonElement>
{
  private final Object lock;
  private final JsonReader parser;
  
  public JsonStreamParser(Reader paramReader)
  {
    this.parser = new JsonReader(paramReader);
    this.parser.setLenient(true);
    this.lock = new Object();
  }
  
  public JsonStreamParser(String paramString)
  {
    this(new StringReader(paramString));
  }
  
  public boolean hasNext()
  {
    for (;;)
    {
      try
      {
        synchronized (this.lock)
        {
          try
          {
            JsonToken localJsonToken1 = this.parser.peek();
            JsonToken localJsonToken2 = JsonToken.END_DOCUMENT;
            if (localJsonToken1 != localJsonToken2)
            {
              bool = true;
              return bool;
            }
          }
          catch (MalformedJsonException localMalformedJsonException)
          {
            throw new JsonSyntaxException(localMalformedJsonException);
          }
        }
        boolean bool = false;
      }
      catch (IOException localIOException)
      {
        throw new JsonIOException(localIOException);
      }
    }
  }
  
  public JsonElement next()
    throws JsonParseException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    try
    {
      JsonElement localJsonElement = Streams.parse(this.parser);
      return localJsonElement;
    }
    catch (StackOverflowError localStackOverflowError)
    {
      throw new JsonParseException("Failed parsing JSON source to Json", localStackOverflowError);
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      throw new JsonParseException("Failed parsing JSON source to Json", localOutOfMemoryError);
    }
    catch (JsonParseException localJsonParseException)
    {
      Object localObject = localJsonParseException;
      if ((localJsonParseException.getCause() instanceof EOFException)) {
        localObject = new NoSuchElementException();
      }
      throw ((Throwable)localObject);
    }
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/gson/JsonStreamParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */